package de.jonasbe.bankingtransactionapi.api

import de.jonasbe.bankingtransactionapi.database.Data
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController()
@RequestMapping
class API {

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
    fun getTest(@RequestBody fileContent: String) {
        println("uploaded: \n $fileContent")
    }


}