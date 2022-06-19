import Transactions from "./Transactions"

function DashboardOne({ transactions }) {
    return (
        <div className="p-3">
            <div className="flex flex-row  border-solid border-2 border-gray-200 rounded-xl bg-white h-3/4-screen drop-shadow-xl	">
                <Transactions transactions={transactions} />

                <div className="w-1 bg-gray-200"></div>

                <div className="py-16 px-12 w-1/2">
                    <h2 className="text-left text-xl font-semibold">Current</h2>
                    <h1 className="text-left text-6xl font-bold py-2">8,000 €</h1>
                    <h3 className="text-left text-lg font-semibold text-zinc-400">02.05.2022</h3>
                    
                    
                    <br />
                    <br />
                    





                    <div className="flex justify-between py-8">
                        <h2 className="text-left text-xl font-medium text-zinc-400">Yesterday</h2>
                        <h2 className="text-right text-xl font-semibold text-zinc-400">7,500 €</h2>
                    </div>

                    <div className="h-0.5 w-full bg-gray-100 rounded-full "></div>

                    <div className="flex justify-between py-8">
                        <h2 className="text-left text-xl font-medium text-zinc-400">Last Month</h2>
                        <h2 className="text-right text-xl font-semibold text-zinc-400">3,500 €</h2>
                    </div>

                    <div className="h-0.5 w-full bg-gray-100 rounded-full "></div>

                    <div className="flex justify-between py-8">
                        <h2 className="text-left text-xl font-medium text-zinc-400">For 3 Month</h2>
                        <h2 className="text-right text-xl font-semibold text-zinc-400">2,500 €</h2>
                    </div>

                    <div className="h-0.5 w-full bg-gray-100 rounded-full "></div>

                    <div className="flex justify-between py-8">
                        <h2 className="text-left text-xl font-medium text-zinc-400">For 6 Month</h2>
                        <h2 className="text-right text-xl font-semibold text-zinc-400">1,500 €</h2>
                    </div>

                    <div className="h-0.5 w-full bg-gray-100 rounded-full "></div>

                    <div className="flex justify-between py-8">
                        <h2 className="text-left text-xl font-medium text-zinc-400">Last Year</h2>
                        <h2 className="text-right text-xl font-semibold text-zinc-400">800 €</h2>
                    </div>

                </div>

            </div>
        </div>
    )
}

export default DashboardOne
