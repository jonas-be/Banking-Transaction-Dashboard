import { useState, useEffect } from 'react'
import Header from "../components/Header"
import Footer from "../components/Footer"
import DashboardOne from "../components/DashboardOne"
import ChartComponent from "../components/ChartComponent"
import UploadPanel from '../components/UploadPanel'

function MainSite() {

  const [transactions, setTransactions] = useState([])
  const [dailyTransactions, setDailyTransactions] = useState([])
  const [uploadShown, setUploadShown] = useState(false)


  useEffect(() => {
    async function setUp() {
      const transactionsData = await fetchTransactions()
      setTransactions(transactionsData)

      const dailyTransactionsData = await fetchDailyTransactions()
      setDailyTransactions(dailyTransactionsData)
    }
    setUp()
  }, [])

  async function fetchTransactions() {
    const res = await fetch('http://localhost:8080/all')
    const data = await res.json()

    return data;
  }

  async function fetchDailyTransactions() {
    const res = await fetch('http://localhost:8080/dailyCreditBalance')
    const data = await res.json()

    return data;
  }

  function onButtonClick() {
    setUploadShown(!uploadShown)


  }

  return (
    <>
      <Header onButtonClick={onButtonClick} />

      {uploadShown ? <UploadPanel /> : ""}

    

      <ChartComponent transactions={dailyTransactions} />
      <DashboardOne transactions={transactions} />
      <Footer />
    </>
  )
}

export default MainSite
