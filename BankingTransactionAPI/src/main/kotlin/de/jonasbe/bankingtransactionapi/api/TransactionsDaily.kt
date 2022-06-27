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

@RestController
@RequestMapping
class TransactionsDaily(
    @Autowired
    val databaseProcessor: DatabaseProcessor
) {

    @CrossOrigin(origins = ["*"])
    @GetMapping("/dailyCreditBalance")
    fun getDailyCreditBalance(): List<TransactionDay> {
        /**
        Get Credit Balance at day start {

        beforeBooking = after booking with amount calculate to before booking

        for (lastMonth)
        if (beforeBooking == lastMonth.afterBooking)
        return lastMonth.afterBooking
        }
         */
        //TODO: implement the logic from th comment
        val allTransactions = databaseProcessor.getAllTransactions()

        val dailyCreditBalance: MutableList<TransactionDay> = mutableListOf()


        println("/dailyCreditBalance")
        println(allTransactions)

        getCreditBalanceAtDayBefore(allTransactions[0].bookingDay, allTransactions)

        return dailyCreditBalance
    }


    @CrossOrigin(origins = ["*"])
    @GetMapping("/test")
    fun getTest() : BigDecimal {

        val var1 = databaseProcessor.getAllTransactions()

        return getCreditBalanceAtDayBefore(var1[0].bookingDay, var1)
    }

//    fun getChangeDays(transactions: List<Transaction>): List<TransactionDay> {
//        val toReturn : MutableList<TransactionDay> = mutableListOf()
//        val dates = getTransactionDates(transactions)
//
//        for (i in 0..dates.size) {
//            if (!(i < dates.size -1))
//            val currentDay = getCreditBalanceAtDayBefore(dates[i +1], transactions)
//        }
//    }

    fun getChangeAmountOfDay(date: LocalDate, transactions: List<Transaction>) : BigDecimal {
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
                if (creditBalanceDayBefore == beforeTransaction) {
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