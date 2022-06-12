package de.jonasbe.bankingtransactionapi.model

import lombok.Getter
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

@Getter
data class Transaction(
    val id: Int?,
    val descriptionOrderAccount: String,
    val ibanOrderAccount: String,
    val bicOrderAccount: String,
    val bankNameOrderAccount: String,
    val bookingDay: Date?,
    val valueDate: Date?,
    val paymentPartyName: String,
    val paymentPartyIBAN: String,
    val paymentPartyBIC: String,
    val bookingText: String,
    val usageText: String,
    val amount: BigDecimal?,
    val currency: String,
    val creditBalanceAfterBooking: BigDecimal?,
    val notice: String,
    val category: String,
    val taxRelevant: String,
    val creditorID: String,
    val mandateReference: String
) {

    constructor(
        descriptionOrderAccount: String,
        ibanOrderAccount: String,
        bicOrderAccount: String,
        bankNameOrderAccount: String,
        bookingDay: String,
        valueDate: String,
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
        mandateReference: String
    ) : this(
        id = null,
        descriptionOrderAccount = descriptionOrderAccount,
        ibanOrderAccount = ibanOrderAccount,
        bicOrderAccount = bicOrderAccount,
        bankNameOrderAccount = bankNameOrderAccount,
        bookingDay = SimpleDateFormat("dd.MM.yyyy").parse(bookingDay),
        valueDate = SimpleDateFormat("dd.MM.yyyy").parse(valueDate),
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
        mandateReference = mandateReference
    ) {
    }


    fun getKeyValueString(): String {
        return "'$descriptionOrderAccount', " +
                "'$ibanOrderAccount', " +
                "'$bicOrderAccount', " +
                "'$bankNameOrderAccount', " +
                "'${SimpleDateFormat("yyyy-MM-dd").format(bookingDay)}', " +
                "'${SimpleDateFormat("yyyy-MM-dd").format(valueDate)}', " +
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
                "'$mandateReference'"
    }
}