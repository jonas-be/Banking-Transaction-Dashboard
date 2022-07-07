package de.jonasbe.bankingtransactionapi.model

import java.math.BigDecimal
import java.time.LocalDate

@lombok.Data
data class TransactionDay(val date : LocalDate?, var balance : BigDecimal?)
