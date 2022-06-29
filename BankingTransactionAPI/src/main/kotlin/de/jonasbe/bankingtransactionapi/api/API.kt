package de.jonasbe.bankingtransactionapi.api

import de.jonasbe.bankingtransactionapi.database.DatabaseProcessor
import de.jonasbe.bankingtransactionapi.model.BalanceHistoryStats
import de.jonasbe.bankingtransactionapi.model.CSVObject
import de.jonasbe.bankingtransactionapi.model.Data
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
        var allTransactions = databaseProcessor.getAllTransactions()
        println("/all")
        return allTransactions.sortedByDescending { it.bookingDay }
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping("/balanceHistoryStats")
    fun getBalanceHistoryStats(): BalanceHistoryStats {
        val dailyTransactions = transactionsDaily.getDailyCreditBalance().sortedByDescending { it.date }

        val balanceHistoryStats: BalanceHistoryStats = BalanceHistoryStats(dailyTransactions)

        return balanceHistoryStats
    }

    @CrossOrigin(origins = ["*"])
    @PostMapping("/upload")
    fun uploadCSV(@RequestBody fileContent: String) {
        println("uploaded: \n $fileContent")
        println("Start parsing...")

        val csvData = CSVObject(fileContent)
        csvData.importToDataBase(databaseProcessor)
    }
}