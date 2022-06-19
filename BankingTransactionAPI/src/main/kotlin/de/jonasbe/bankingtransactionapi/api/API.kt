package de.jonasbe.bankingtransactionapi.api

import de.jonasbe.bankingtransactionapi.database.DatabaseProcessor
import de.jonasbe.bankingtransactionapi.model.CSVObject
import de.jonasbe.bankingtransactionapi.model.Data
import de.jonasbe.bankingtransactionapi.model.Transaction
import de.jonasbe.bankingtransactionapi.model.TransactionDay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping
class API(
    @Autowired
    val databaseProcessor: DatabaseProcessor
) {

    @CrossOrigin(origins = ["*"])
    @GetMapping("/all")
    fun getAllTransactions(): List<Transaction> {
        var allTransactions = databaseProcessor.getAllTransactions()
        println("/all")
        return allTransactions.sortedByDescending { it.bookingDay }
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping("/dailyCreditBalance")
    fun getDailyCreditBalance(): List<TransactionDay> {
        /**
         * Get Credit Balance at day start {

             beforeBooking = after booking with amount calculate to before booking

             for (lastMonth)
                 if (beforeBooking == lastMonth.afterBooking)
                     return lastMonth.afterBooking
        }
         */
        //TODO: implement the logic from th comment
        var allTransactions = databaseProcessor.getAllTransactions().sortedBy { it.bookingDay }

        val dailyCreditBalance: MutableList<TransactionDay> = mutableListOf()


        println("/dailyCreditBalance")
        println(dailyCreditBalance)
        return dailyCreditBalance
    }


    @CrossOrigin(origins = ["*"])
    @GetMapping("/data")
    fun getData(): List<Data> {
        val list: List<Data> = listOf(
            Data(GregorianCalendar(2022, Calendar.JUNE, 1).time, Random().nextInt(100)),
            Data(GregorianCalendar(2022, Calendar.JUNE, 2).time, Random().nextInt(100)),
            Data(GregorianCalendar(2022, Calendar.JUNE, 3).time, Random().nextInt(100)),
            Data(GregorianCalendar(2022, Calendar.JUNE, 4).time, Random().nextInt(100)),
            Data(GregorianCalendar(2022, Calendar.JUNE, 5).time, Random().nextInt(100)),
        )

        return list
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