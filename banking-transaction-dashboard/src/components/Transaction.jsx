import { useState } from "react"
import { FaSortDown, FaSortUp } from "react-icons/fa"


function Transaction({ transaction }) {

  const [detailsShown, setDetailsShown] = useState(false)

  function toggleDetails() {
    setDetailsShown(!detailsShown)
  }


  return (
    <div >
      <div className="flex justify-between border-none p-4 pb-0 z-1">
        <div className="pr-10	">
          <h2 className='text-left text-xl font-semibold '>{transaction.paymentPartyName}</h2>
          <h3 className='text-left text-lg font-semibold text-zinc-400'>{transaction.bookingDay}</h3>
        </div>

        <div>
          <h2 className={`text-right text-xl font-semibold ${transaction.amount < 0 ? "text-red-500" : "text-green-500"}`}>
            {transaction.amount < 0 ? "" : "+"}{transaction.amount} €</h2>
          <h3 className='text-right text-lg font-medium text-zinc-400 '>{transaction.creditBalanceAfterBooking} €</h3>
        </div>

      </div>

      {detailsShown ?
        <div className="pt-4 pb-1 px-4">
          <h3 className='p-2 rounded-t-md border-none bg-gray-600  text-left text-md font-semibold text-white '>{transaction.bookingText} €</h3>
          <h3 className='p-2 rounded-b-md border-2 border-t-0 border-gray-300 text-left text-md font-medium text-gray-600 '>{transaction.usageText} €</h3>


        </div>
        : ""}


      <div onClick={toggleDetails} className="flex  items-center  w-full p-0.5 cursor-pointer">
        <div className="h-0.5 w-6/12 bg-gray-100 rounded-full "></div>
        {detailsShown ?
          <FaSortUp
            style={{ padding: ".3rem .15rem 0", textAlign: "center", fontSize: "1.8em", color: "gray", cursor: 'pointer' }} />
          :
          <FaSortDown
            style={{ padding: "0 .15rem .3rem", textAlign: "center", fontSize: "1.8em", color: "gray", cursor: 'pointer' }} />
        }
        <div className="h-0.5 w-6/12 bg-gray-100 rounded-full "></div>
      </div>

      {/* <div className="flex justify-center w-full p-0.5">
        <div className="h-0.5 w-11/12 bg-gray-100 rounded-full "></div>
      </div> */}
    </div>
  )
}

export default Transaction
