import Transaction from './Transaction'

function Transactions({ transactions }) {

  return (
    <div className='flex flex-col p-2 overflow-y-scroll w-full  '>
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
