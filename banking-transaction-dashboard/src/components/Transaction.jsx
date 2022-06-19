function Transaction({ transaction }) {

  return (
    <>
      <div className="flex justify-between border-none bg-white p-4">
        <div className="pr-10	">
          <h1 className='text-left text-xl font-semibold '>{transaction.paymentPartyName}</h1>
          <h2 className='text-left text-lg font-semibold text-zinc-400'>{transaction.bookingDay}</h2>
        </div>

        <div>
          <h1 className='text-right text-xl font-semibold text-zinc-400 '>{transaction.creditBalanceAfterBooking} €</h1>
          <h2 className={`text-right text-lg font-medium
        ${transaction.amount < 0 ? "text-red-500" : "text-green-500"}`}>{transaction.amount} €</h2>
        </div>

      </div>


        <div className="flex justify-center w-full">
          <div className="h-0.5 w-11/12 bg-gray-100 rounded-full "></div>
        </div>
    </>
  )
}

export default Transaction
