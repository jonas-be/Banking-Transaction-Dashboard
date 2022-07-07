package de.jonasbe.bankingtransactionapi.api

import de.jonasbe.bankingtransactionapi.database.DatabaseProcessor
import de.jonasbe.bankingtransactionapi.model.BalanceHistoryStats
import de.jonasbe.bankingtransactionapi.model.CSVObject
import de.jonasbe.bankingtransactionapi.model.DataState
import de.jonasbe.bankingtransactionapi.model.Transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class API(
    @Autowired
    val databaseProcessor: DatabaseProcessor,
    @Autowired
    val transactionsDaily: TransactionsDaily
) {

    @CrossOrigin(origins = ["*"])
    @GetMapping("/all")
    fun getAllTransactions(): List<Transaction> {
        val allTransactions = databaseProcessor.getAllTransactions()
        return allTransactions.sortedByDescending { it.bookingDay }
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping("/balanceHistoryStats")
    fun getBalanceHistoryStats(): BalanceHistoryStats {
        val dailyTransactions = transactionsDaily.getDailyCreditBalance().sortedByDescending { it.date }
        return BalanceHistoryStats(dailyTransactions)
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping("/dataState")
    fun getDataState(): DataState {
        return DataState(transactionsDaily.getTransactionDates(getAllTransactions()))
    }

    @CrossOrigin(origins = ["*"])
    @PostMapping("/upload")
    fun uploadCSV(@RequestBody fileContent: String) {
        val csvData = CSVObject(fileContent)
        csvData.importToDataBase(databaseProcessor)
    }
}