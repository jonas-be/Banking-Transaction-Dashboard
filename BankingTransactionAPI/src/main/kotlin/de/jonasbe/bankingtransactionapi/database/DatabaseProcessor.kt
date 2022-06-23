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
import java.time.LocalDate
import javax.sql.DataSource
import kotlin.io.path.Path

private const val sqlDirectory = "sql"

@Component
class DatabaseProcessor(
    @Value("\${spring.datasource.url}") val dbUrl: String,
    @Value("\${spring.datasource.driverClassName}") val driverClassName: String,
    @Value("\${spring.sql.files.setup}") val setupFile: String
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    lateinit var dataSource: DataSource
    lateinit var jdbc: JdbcTemplate
    private lateinit var dbFilePath: String

    init {
        createDataBaseFile()
        setUpDataSource()
        setUpSchemasAndTables()
    }

    fun createDataBaseFile() {
        val basePath = File(this::class.java.protectionDomain.codeSource.location.toURI()).path
        val dbFile = File(basePath, "transactions.db")
        dbFilePath = dbFile.toString()
        if (dbFile.exists()) {
            logger.info("Database file already exists")
            return
        }
        if (dbFile.createNewFile()) {
            logger.info("Database file successfully created")
            return
        }
        logger.error("Database file could not be created!")
    }

    fun setUpDataSource() {
        dataSource = DataSourceBuilder.create()
            .driverClassName(driverClassName)
            .url("$dbUrl:$dbFilePath")
            .build()
        jdbc = JdbcTemplate(dataSource)
    }

    fun setUpSchemasAndTables() {
        val sql = getSqlFromResources(setupFile)
        val result = jdbc.update(sql)
        logger.info("Updated database -> Code: $result")
    }

    fun getAllTransactions(): List<Transaction> {
        return jdbc.query("SELECT * FROM main.transactions;", Mapper())
    }

    fun getAllTransactionsByDate(date: LocalDate): List<Transaction> {
        println(date)
        return jdbc.query(
            "SELECT * FROM main.transactions " +
                    "WHERE bookingday = '${date}';",
            Mapper()
        )
    }

    fun deleteWhereBookingdate(date: LocalDate) {
        println(date)
        jdbc.update(
            "DELETE FROM main.transactions " +
                    "WHERE bookingday = '${date}';"
        )
    }

    fun addTransactions(transactions: List<Transaction>) {
        for (transaction in transactions) {
            addTransaction(transaction)
        }
    }

    fun addTransaction(transaction: Transaction) {
        jdbc.update(
            "INSERT INTO main.transactions" +
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