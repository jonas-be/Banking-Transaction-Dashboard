import { FaArrowDown, FaArrowUp, FaMinus } from "react-icons/fa"

function BalanceHistoryStats({ balanceHistoryStats }) {

    if (balanceHistoryStats.length !== 0)
        return (

            <div className="py-16 sm:px-2 md:px-12 w-full overflow-y-auto">
                <h2 className="text-left text-xl font-semibold">Current</h2>
                <h1 className="text-left text-5xl font-bold py-2">{balanceHistoryStats.currentBalance.balance} €</h1>
                <h3 className="text-left text-lg font-semibold text-zinc-400">{balanceHistoryStats.currentBalance.date}</h3>

                <br />
                <br />

                <HistroyElement text="Yesterday" data={balanceHistoryStats.balanceBeforeDay} current={balanceHistoryStats.currentBalance.balance} />
                <HistroyElement text="A Week ago" data={balanceHistoryStats.balanceBeforeWeek} current={balanceHistoryStats.currentBalance.balance} />
                <HistroyElement text="A Month ago" data={balanceHistoryStats.balanceBeforeMonth} current={balanceHistoryStats.currentBalance.balance} />
                <HistroyElement text="3 Months ago" data={balanceHistoryStats.balanceBeforeThreeMonth} current={balanceHistoryStats.currentBalance.balance} />
                <HistroyElement text="6 Months ago" data={balanceHistoryStats.balanceBeforeSixMonth} current={balanceHistoryStats.currentBalance.balance} />

            </div>
        )
    else
        return (
            <div>Loading...</div>
        )
}

function HistroyElement({ text, data, current }) {

    const percantage = getPercantage(data.balance, current)

    function getPercantage(before, current) {

        const differnce = current - before
        const percent = differnce / current

        var num = Number(percent * 100)

        return num.toFixed(2)
    }

    function getSortSign() {
        if (percantage < 0) {
            return (
                <FaArrowDown style={{ padding: "0 0 .2rem", textAlign: "center", fontSize: "1.8em", color: "rgb(239, 68, 68)", cursor: 'pointer' }} />
            )
        }
        if (percantage == 0) {
            return (
                <FaMinus style={{ padding: ".3rem", textAlign: "center", fontSize: "1.8em", color: "rgb(34, 197, 94)", cursor: 'pointer' }} />
            )
        }
        return (
            <FaArrowUp style={{ padding: ".3rem", textAlign: "center", fontSize: "1.8em", color: "rgb(34, 197, 94)", cursor: 'pointer' }} />

        )
    }



    return (
        <>
            <div className="flex justify-items-start py-8">

                <div className="flex items-center align-middle w-32 px-1.5 rounded-md bg-blue-200">
                    {getSortSign()}
                    <h2 className="text-sm font-semibold text-blue-600 ">{percantage} %</h2>
                </div>

                <h2 className="text-left text-xl font-medium text-zinc-400 px-4">{text}</h2>


                <h2 className="text-right text-xl font-semibold text-zinc-400 self-stretch grow">{data.balance !== null ? data.balance : "---"} €</h2>

            </div>

            <div className="h-0.5 w-full bg-gray-100 rounded-full"></div>
        </>
    )
}



export default BalanceHistoryStats