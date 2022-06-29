package de.jonasbe.bankingtransactionapi.model

import java.time.LocalDate

data class DataState(
    val datesFrom: LocalDate,
    val datesTo: LocalDate,
    val statusMessage: String,
) {
    constructor(dates: List<LocalDate>) : this(
        dates[0],
        dates.last(),
        if (dates.last() == LocalDate.now())
            "You are up to date!"
        else
            "You missing transactions since ${dates.last()}"
    )
}
