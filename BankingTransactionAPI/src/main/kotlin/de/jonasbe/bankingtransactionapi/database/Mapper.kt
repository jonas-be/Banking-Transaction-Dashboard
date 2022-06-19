package de.jonasbe.bankingtransactionapi.database

import de.jonasbe.bankingtransactionapi.model.Transaction
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class Mapper : RowMapper<Transaction> {
    override
    fun mapRow(resultSet: ResultSet, rowNum: Int): Transaction {
        return Transaction(
            resultSet.getInt("id"),
            resultSet.getString("descriptionOrderAccount"),
            resultSet.getString("ibanOrderAccount"),
            resultSet.getString("bicOrderAccount"),
            resultSet.getString("bankNameOrderAccount"),
            resultSet.getDate("bookingDay").toLocalDate(),
            resultSet.getDate("valueDate").toLocalDate(),
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