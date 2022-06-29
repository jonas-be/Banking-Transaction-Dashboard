import BalanceHistoryStats from "./BalanceHistoryStats"
import Transactions from "./Transactions"

function DashboardOne({ transactions, balanceHistoryStats }) {
    return (
        <div className="p-3 lg:flex justify-center">
            <div className="flex sm:flex-col-reverse md:flex-row  md:h-[85vh]  lg:w-90-screen   border-solid border-2 border-gray-200 rounded-3xl   bg-white drop-shadow-xl  overflow-hidden">

                <Transactions transactions={transactions}  />

                <div className="sm:w-full md:w-1 sm:h-0.5 md:h-auto bg-gray-200"></div>

                <BalanceHistoryStats balanceHistoryStats={balanceHistoryStats} />

            </div>
        </div>
    )
}

export default DashboardOne
