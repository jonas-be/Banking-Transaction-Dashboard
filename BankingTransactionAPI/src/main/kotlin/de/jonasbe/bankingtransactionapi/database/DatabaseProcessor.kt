package de.jonasbe.bankingtransactionapi.database

import de.jonasbe.bankingtransactionapi.model.Transaction
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*
import javax.sql.DataSource
import kotlin.io.path.Path

private const val sqlDirectory = "sql"

@Component
class DatabaseProcessor(
    @Value("\${app.db.url}") val dbUrl: String,
    @Value("\${app.db.user}") val dbUser: String,
    @Value("\${app.db.password}") val dbPassword: String,
    @Value("\${app.db.sql.files.setup}") val setupFile: String
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    lateinit var dataSource: DataSource
    lateinit var jdbc: JdbcTemplate

    init {
        setUpDataSource()
        setUpSchemasAndTables()
    }

    fun setUpDataSource() {
        dataSource = DataSourceBuilder.create()
            .driverClassName("org.postgresql.Driver")
            .url(dbUrl)
            .username(dbUser)
            .password(dbPassword)
            .build()
        jdbc = JdbcTemplate(dataSource)
    }

    fun setUpSchemasAndTables() {
        val sql = getSqlFromResources(setupFile)
        val result = jdbc.update(sql)
        logger.info("Updated database -> Code: $result")
    }

    fun getAllTransactions(): List<Transaction> {
        return jdbc.query("SELECT * FROM banking_transaction_api.transactions;", Mapper())
    }

    fun getAllTransactionsByDate(date: Date): List<Transaction> {
        return jdbc.query(
            "SELECT * FROM banking_transaction_api.transactions " +
                    "WHERE bookingday = '${SimpleDateFormat("yyyy-MM-dd").format(date)}';",
            Mapper()
        )
    }

    fun deleteWhereBookingdate(date: Date) {
        jdbc.update(
            "DELETE FROM banking_transaction_api.transactions " +
                    "WHERE bookingday = '${SimpleDateFormat("yyyy-MM-dd").format(date)}';"
        )
    }

    fun addTransactions(transactions: List<Transaction>) {
        for (transaction in transactions) {
            addTransaction(transaction)
        }
    }

    fun addTransaction(transaction: Transaction) {
        jdbc.update(
            "INSERT INTO banking_transaction_api.transactions" +
                    "(descriptionorderaccount, " +
                    "ibanorderaccount, " +
                    "bicorderaccount, " +
                    "banknameorderaccount, " +
                    "bookingday, " +
                    "valuedate, " +
                    "paymentpartyname, " +
                    "paymentpartyiban, " +
                    "paymentpartybic, " +
                    "bookingtext, " +
                    "usagetext, " +
                    "amount, " +
                    "currency, " +
                    "creditbalanceafterbooking, " +
                    "notice, " +
                    "category, " +
                    "taxrelevant, " +
                    "creditorid, " +
                    "mandatereference) " +
                    "VALUES (${transaction.getKeyValueString()});"
        )
    }


    private fun getSqlFromResources(fileName: String): String {
        val res = javaClass.classLoader.getResource(sqlDirectory)
        val file: File = Paths.get(res.toURI()).toFile()
        val sqlFile = Path(file.absolutePath, fileName)
        return Files.readString(sqlFile)
    }
}