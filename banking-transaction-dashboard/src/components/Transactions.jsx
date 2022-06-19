import Transaction from './Transaction'

function Transactions({ transactions, style }) {

  return (
    <div className={`flex flex-col p-2 overflow-y-scroll w-full ${style}`}>
      {transactions.map((transaction) => (
        <div key={transaction.id}>
          <Transaction key={transaction.id} transaction={transaction} />
          <div className='h-2'></div>
        </div>
      ))}

    </div>
  )
}

export default Transactions
