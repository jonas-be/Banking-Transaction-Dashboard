package de.jonasbe.bankingtransactionapi.database

import de.jonasbe.bankingtransactionapi.model.Transaction
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
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
    @Autowired
    val applicationArguments: ApplicationArguments
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
        val basePath = File(applicationArguments.sourceArgs[0]).path
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
        val sql = "CREATE TABLE\n" +
                "    IF NOT EXISTS\n" +
                "    main.transactions(\n" +
                "                       id    SERIAL PRIMARY KEY,\n" +
                "                       descriptionOrderAccount VARCHAR,\n" +
                "                       ibanOrderAccount VARCHAR,\n" +
                "                       bicOrderAccount VARCHAR,\n" +
                "                       bankNameOrderAccount VARCHAR,\n" +
                "                       bookingDay DATE,\n" +
                "                       valueDate DATE,\n" +
                "                       paymentPartyName VARCHAR,\n" +
                "                       paymentPartyIBAN VARCHAR,\n" +
                "                       paymentPartyBIC VARCHAR,\n" +
                "                       bookingText VARCHAR,\n" +
                "                       usageText VARCHAR,\n" +
                "                       amount NUMERIC,\n" +
                "                       currency VARCHAR,\n" +
                "                       creditBalanceAfterBooking NUMERIC,\n" +
                "                       notice VARCHAR,\n" +
                "                       category VARCHAR,\n" +
                "                       taxRelevant VARCHAR,\n" +
                "                       creditorID VARCHAR,\n" +
                "                       mandateReference VARCHAR\n" +
                ");"
        val result = jdbc.update(sql)
        logger.info("Updated database -> Code: $result")
    }

    fun getAllTransactions(): List<Transaction> {
        return jdbc.query("SELECT * FROM main.transactions;", Mapper())
    }

    fun getAllTransactionsByDate(date: LocalDate): List<Transaction> {
        return jdbc.query(
            "SELECT * FROM main.transactions " +
                    "WHERE bookingday = '${date}';",
            Mapper()
        )
    }

    fun deleteWhereBookingdate(date: LocalDate) {
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
}