package de.jonasbe.diaryapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DiaryApiApplication

fun main(args: Array<String>) {
    runApplication<DiaryApiApplication>(*args)
}
