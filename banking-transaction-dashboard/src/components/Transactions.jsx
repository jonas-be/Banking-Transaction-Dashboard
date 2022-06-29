import Transaction from './Transaction'
import Loading from './Loading'

function Transactions({ transactions, style }) {

  if (transactions.length !== 0) {

    return (
      <div className={`flex flex-col p-2 overflow-y-scroll w-full ${style}`}>
        {transactions.map((transaction) => (
          <div key={transaction.keyValueString}>
            <Transaction key={transaction.id} transaction={transaction} />
            <div className='h-2'></div>
          </div>
        ))}

      </div>
    )
  } else {
    return (
      <Loading />
    )
  }
}

export default Transactions
