package de.jonasbe.bankingtransactionapi.database

import de.jonasbe.bankingtransactionapi.model.Transaction
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Mapper : RowMapper<Transaction> {
    override
    fun mapRow(resultSet: ResultSet, rowNum: Int): Transaction {
        return Transaction(
            resultSet.getInt("id"),
            resultSet.getString("descriptionOrderAccount"),
            resultSet.getString("ibanOrderAccount"),
            resultSet.getString("bicOrderAccount"),
            resultSet.getString("bankNameOrderAccount"),
            LocalDate.parse(resultSet.getString("bookingDay"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            LocalDate.parse(resultSet.getString("valueDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            resultSet.getString("paymentPartyName"),
            resultSet.getString("paymentPartyIBAN"),
            resultSet.getString("paymentPartyBIC"),
            resultSet.getString("bookingText"),
            resultSet.getString("usageText"),
            resultSet.getBigDecimal("amount"),
            resultSet.getString("currency"),
            resultSet.getBigDecimal("creditBalanceAfterBooking"),
            resultSet.getString("notice"),
            resultSet.getString("category"),
            resultSet.getString("taxRelevant"),
            resultSet.getString("creditorID"),
            resultSet.getString("mandateReference")
        )
    }
}