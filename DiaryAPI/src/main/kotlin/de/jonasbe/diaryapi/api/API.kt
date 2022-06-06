package de.jonasbe.diaryapi.api

import de.jonasbe.diaryapi.database.Data
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController()
@RequestMapping
class API {

    @CrossOrigin(origins = ["*"])
    @GetMapping("/data")
    fun getData() : List<Data> {
        val list : List<Data> = listOf(
            Data(GregorianCalendar(2022, Calendar.JUNE, 1).time, Random().nextInt(100)),
            Data(GregorianCalendar(2022, Calendar.JUNE, 2).time, Random().nextInt(100)),
            Data(GregorianCalendar(2022, Calendar.JUNE, 3).time, Random().nextInt(100)),
            Data(GregorianCalendar(2022, Calendar.JUNE, 4).time, Random().nextInt(100)),
            Data(GregorianCalendar(2022, Calendar.JUNE, 5).time, Random().nextInt(100)),
        )

        return list
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping("/string")
    fun getTest() : Data {
        return Data(Date(), 2);
    }




}