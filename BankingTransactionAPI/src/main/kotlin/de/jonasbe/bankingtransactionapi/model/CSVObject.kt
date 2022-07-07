package de.jonasbe.bankingtransactionapi.model

import de.jonasbe.bankingtransactionapi.database.DatabaseProcessor
import java.time.LocalDate

class CSVObject(fileContent: String) {
    val transactions: List<Transaction> = getTransactionsByCSV(fileContent)

    private fun getTransactionsByCSV(fileContent: String): List<Transaction> {
        val list: MutableList<Transaction> = mutableListOf()
        for (line in fileContent.split("\n")) {
            if (line.startsWith("Bezeichnung Auftragskonto")) { //TODO: implement better header match
                continue
            }
            val lineData = line.split(";")
            if (lineData.size < 18) continue
            list.add(
                Transaction(
                    lineData[0],
                    lineData[1],
                    lineData[2],
                    lineData[3],
                    lineData[4],
                    lineData[5],
                    lineData[6],
                    lineData[7],
                    lineData[8],
                    lineData[9],
                    lineData[10],
                    lineData[11],
                    lineData[12],
                    lineData[13],
                    lineData[14],
                    lineData[15],
                    lineData[16],
                    lineData[17],
                    lineData[18],
                )
            )
        }
        return list
    }

    fun importToDataBase(dbProcessor: DatabaseProcessor) {
        for (date in getDates()) {
            val dbTransactionsWhereBookingdate = dbProcessor.getAllTransactionsByDate(date)
            val csvTransactionsByDate = getAllTransactionsByDate(date)
            if (dbTransactionsWhereBookingdate.size != csvTransactionsByDate.size) { // Only if db count is lower
                dbProcessor.deleteWhereBookingdate(date)
                dbProcessor.addTransactions(csvTransactionsByDate)
            }
        }
    }

    private fun getDates(): List<LocalDate> {
        val list: MutableList<LocalDate> = mutableListOf()
        for (transaction in transactions) {
            if (!list.contains(transaction.bookingDay)) {
                list.add(transaction.bookingDay)
            }
        }
        return list
    }

    private fun getAllTransactionsByDate(date: LocalDate): List<Transaction> {
        val list: MutableList<Transaction> = mutableListOf()
        for (transaction in transactions) {
            if (transaction.bookingDay == date) {
                list.add(transaction)
            }
        }
        return list
    }
}
