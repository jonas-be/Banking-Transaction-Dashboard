package de.jonasbe.bankingtransactionapi.database

import lombok.Getter
import lombok.ToString
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

@Getter
@ToString
data class CSVDataObject(
    val descriptionOrderAccount: String,
    val ibanOrderAccount: String,
    val bicOrderAccount: String,
    val bankNameOrderAccount: String,
    val bookingDay: Date,
    val valueDate: Date,
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

    companion object {
        fun getByCSV(fileContent: String): List<CSVDataObject> {

            val list: MutableList<CSVDataObject> = mutableListOf()
            for (line in fileContent.split("\n")) {
                if (line.startsWith("Bezeichnung Auftragskonto")) {
                    continue
                }
                val lineData = line.split(";")
                if (lineData.size < 18) continue
                list.add(
                    CSVDataObject(
                        lineData[0],
                        lineData[1],
                        lineData[2],
                        lineData[3],
                        lineData[4],
                        lineData[5],
                        lineData[6],
                        lineData[7],
                        lineData[8],
                        lineData[9],
                        lineData[10],
                        lineData[11],
                        lineData[12],
                        lineData[13],
                        lineData[14],
                        lineData[15],
                        lineData[16],
                        lineData[17],
                        lineData[18],
                    )
                )
            }
            return list
        }
    }


}