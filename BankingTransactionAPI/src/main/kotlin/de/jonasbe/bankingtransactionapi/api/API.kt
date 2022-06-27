package de.jonasbe.bankingtransactionapi.api

import de.jonasbe.bankingtransactionapi.database.DatabaseProcessor
import de.jonasbe.bankingtransactionapi.model.CSVObject
import de.jonasbe.bankingtransactionapi.model.Data
import de.jonasbe.bankingtransactionapi.model.Transaction
import de.jonasbe.bankingtransactionapi.model.TransactionDay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDate
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