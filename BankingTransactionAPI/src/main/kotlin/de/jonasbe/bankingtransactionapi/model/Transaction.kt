package de.jonasbe.bankingtransactionapi.model

import lombok.Getter
import lombok.ToString
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ToString
@Getter
data class Transaction(
    val id: Int?,
    val descriptionOrderAccount: String,
    val ibanOrderAccount: String,
    val bicOrderAccount: String,
    val bankNameOrderAccount: String,
    val bookingDay: LocalDate,
    val valueLocalDate: LocalDate,
    val paymentPartyName: String,
    val paymentPartyIBAN: String,
    val paymentPartyBIC: String,
    val bookingText: String,
    val usageText: String,
    val amount: BigDecimal,
    val currency: String,
    val creditBalanceAfterBooking: BigDecimal,
    val notice: String,
    val category: String,
    val taxRelevant: String,
    val creditorID: String,
    val manLocalDateReference: String
) {

    constructor(
        descriptionOrderAccount: String,
        ibanOrderAccount: String,
        bicOrderAccount: String,
        bankNameOrderAccount: String,
        bookingDay: String,
        valueLocalDate: String,
        paymentPartyName: String,
        paymentPartyIBAN: String,
        paymentPartyBIC: String,
        bookingText: String,
        usageText: String,
        amount: String,
        currency: String,
        creditBalanceAfterBooking: String,
        notice: String,
        category: String,
        taxRelevant: String,
        creditorID: String,
        manLocalDateReference: String
    ) : this(
        id = null,
        descriptionOrderAccount = descriptionOrderAccount,
        ibanOrderAccount = ibanOrderAccount,
        bicOrderAccount = bicOrderAccount,
        bankNameOrderAccount = bankNameOrderAccount,
        bookingDay = LocalDate.parse(bookingDay, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
        valueLocalDate = LocalDate.parse(valueLocalDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
        paymentPartyName = paymentPartyName,
        paymentPartyIBAN = paymentPartyIBAN,
        paymentPartyBIC = paymentPartyBIC,
        bookingText = bookingText,
        usageText = usageText,
        amount = BigDecimal(amount.replace(",", ".")),
        currency = currency,
        creditBalanceAfterBooking = BigDecimal(creditBalanceAfterBooking.replace(",", ".")),
        notice = notice,
        category = category,
        taxRelevant = taxRelevant,
        creditorID = creditorID,
        manLocalDateReference = manLocalDateReference
    ) {
    }


    fun getKeyValueString(): String {
        return "'$descriptionOrderAccount', " +
                "'$ibanOrderAccount', " +
                "'$bicOrderAccount', " +
                "'$bankNameOrderAccount', " +
                "'${DateTimeFormatter.ofPattern("yyyy-MM-dd").format(bookingDay)}', " +
                "'${DateTimeFormatter.ofPattern("yyyy-MM-dd").format(valueLocalDate)}', " +
                "'$paymentPartyName', " +
                "'$paymentPartyIBAN', " +
                "'$paymentPartyBIC', " +
                "'$bookingText', " +
                "'$usageText', " +
                "$amount, " +
                "'$currency', " +
                "$creditBalanceAfterBooking, " +
                "'$notice', " +
                "'$category', " +
                "'$taxRelevant', " +
                "'$creditorID', " +
                "'$manLocalDateReference'"
    }
}