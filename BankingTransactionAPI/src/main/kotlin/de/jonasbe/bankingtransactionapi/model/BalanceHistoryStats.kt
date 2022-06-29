package de.jonasbe.bankingtransactionapi.model

data class BalanceHistoryStats(
    var currentBalance: TransactionDay?,
    var balanceBeforeDay: TransactionDay?,
    var balanceBeforeWeek: TransactionDay?,
    var balanceBeforeMonth: TransactionDay?,
    var balanceBeforeThreeMonth: TransactionDay?,
    var balanceBeforeSixMonth: TransactionDay?
) {
    constructor(dailyTransactions: List<TransactionDay>) : this(
        currentBalance = if (dailyTransactions.size > 0) dailyTransactions[0] else TransactionDay(null, null),
        balanceBeforeDay = if (dailyTransactions.size > 1) dailyTransactions[1] else TransactionDay(null, null),
        balanceBeforeWeek = if (dailyTransactions.size > 7) dailyTransactions[7] else TransactionDay(null, null),
        balanceBeforeMonth = if (dailyTransactions.size > 30) dailyTransactions[30] else TransactionDay(null, null),
        balanceBeforeThreeMonth = if (dailyTransactions.size > 70) dailyTransactions[70] else TransactionDay(null, null), //TODO: 90!!!!
        balanceBeforeSixMonth = if (dailyTransactions.size > 180) dailyTransactions[180] else TransactionDay(null, null)

    )

}