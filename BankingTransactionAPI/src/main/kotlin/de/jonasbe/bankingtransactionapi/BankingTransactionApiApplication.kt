package de.jonasbe.bankingtransactionapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BankingTransactionApiApplication

fun main(args: Array<String>) {
    runApplication<BankingTransactionApiApplication>(*args)
}
