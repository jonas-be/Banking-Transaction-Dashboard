package de.jonasbe.bankingtransactionapi.api

import de.jonasbe.bankingtransactionapi.database.DatabaseProcessor
import de.jonasbe.bankingtransactionapi.model.Transaction
import de.jonasbe.bankingtransactionapi.model.TransactionDay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDate
import java.util.stream.Collectors

@RestController
@RequestMapping
class TransactionsDaily(
    @Autowired
    val databaseProcessor: DatabaseProcessor
) {

    @CrossOrigin(origins = ["*"])
    @GetMapping("/dailyCreditBalance")
    fun getDailyCreditBalance(): List<TransactionDay> {
        val allTransactions = databaseProcessor.getAllTransactions()
        val dailyCreditBalance: MutableList<TransactionDay> = mutableListOf()

        val transactionDates = getTransactionDates(allTransactions)

        transactionDates[0].datesUntil(transactionDates.last())
            .collect(Collectors.toList())
            .forEach {
                dailyCreditBalance.add(
                    TransactionDay(it, BigDecimal.ZERO)
                )
            }

        val changeDays = getChangeDays(allTransactions)

        var lastCreditBalance = BigDecimal.ZERO
        for (transactionDay in dailyCreditBalance) {
            val changeDay = changeDays.filter { it.date == transactionDay.date }
            if (changeDay.size == 1) {
                lastCreditBalance = changeDay[0].balance
            }

            transactionDay.balance = lastCreditBalance
        }

        return dailyCreditBalance
    }


    @CrossOrigin(origins = ["*"])
    @GetMapping("/changeDays")
    fun getChangeDays(): List<TransactionDay> {

        val transactions = databaseProcessor.getAllTransactions()
        val dates = getTransactionDates(transactions)

        return getChangeDays(transactions)
    }

    fun getChangeDays(transactions: List<Transaction>): List<TransactionDay> {
        val toReturn: MutableList<TransactionDay> = mutableListOf()
        val dates = getTransactionDates(transactions)

        for (i in dates.indices) {
            if (i < dates.size - 1) {
                val currentDay = getCreditBalanceAtDayBefore(dates[i + 1], transactions)
                toReturn.add(
                    TransactionDay(dates[i], currentDay)
                )
            } else {
                val lastDay = getCreditBalanceAtDayBefore(dates[i], transactions)
                toReturn.add(
                    TransactionDay(dates[i], lastDay.add(getChangeAmountOfDay(dates[i], transactions)))
                )
            }
        }
        return toReturn
    }

    fun getChangeAmountOfDay(date: LocalDate, transactions: List<Transaction>): BigDecimal {
        val transactionsOnThisDay = getTransactionsByDate(date, transactions)
        val changeAmount = BigDecimal.ZERO
        for (transaction in transactionsOnThisDay) {
            changeAmount.add(transaction.amount)
        }
        return changeAmount
    }

    /**
    Get Credit Balance at day start {

    beforeBooking = after booking with amount calculate to before booking

    for (lastMonth)
    if (beforeBooking == lastMonth.afterBooking)
    return lastMonth.afterBooking
    }
     */
    fun getCreditBalanceAtDayBefore(date: LocalDate, transactions: List<Transaction>): BigDecimal {
        val transactionsOnThisDay = getTransactionsByDate(date, transactions)
        val dates = getTransactionDates(transactions)
        val dayBefore = dates[dates.indexOf(date) - 1]

        for (transaction in transactionsOnThisDay) {
            val creditBalanceAfterBooking = transaction.creditBalanceAfterBooking
            val amount = transaction.amount
            val beforeTransaction = creditBalanceAfterBooking.subtract(amount)

            for (transactionDayBefore in getTransactionsByDate(dayBefore, transactions)) {
                val creditBalanceDayBefore = transactionDayBefore.creditBalanceAfterBooking
                if (creditBalanceDayBefore.compareTo(beforeTransaction) == 0) {
                    return creditBalanceDayBefore
                }
            }
        }

        throw java.lang.IllegalStateException("Some data is wrong. Can't calculate creditBalance of the day before.")
    }

    fun getTransactionDates(transactions: List<Transaction>): List<LocalDate> {
        val toReturn: MutableList<LocalDate> = mutableListOf()
        for (transaction in transactions) {
            if (!toReturn.contains(transaction.bookingDay)) {
                toReturn.add(transaction.bookingDay)
            }
        }
        return toReturn.sortedBy { it }
    }

    fun getTransactionsByDate(
        date: LocalDate,
        transactions: List<Transaction>
    ): MutableList<Transaction> {
        val toReturn: MutableList<Transaction> = mutableListOf()
        for (transaction in transactions) {
            if (transaction.bookingDay == date) {
                toReturn.add(transaction)
            }
        }
        return toReturn
    }

}