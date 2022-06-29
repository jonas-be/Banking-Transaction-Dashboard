package de.jonasbe.bankingtransactionapi.model

import java.time.LocalDate

data class DataState(
    val datesFrom: LocalDate?,
    val datesTo: LocalDate?,
    val statusMessage: String,
    val isUptoDate: Boolean,
) {
    constructor(dates: List<LocalDate>) : this(
        if (dates.isNotEmpty()) dates[0] else null,
        if (dates.isNotEmpty()) dates.last() else null,
        if (dates.isEmpty())
            "Nothing uploaded until yet!"
        else if (dates.last() == LocalDate.now())
            "You are up to date!"
        else
            "Missing transactions since ${dates.last()}",
        if (dates.isNotEmpty()) dates.last() == LocalDate.now() else false
    )
}
