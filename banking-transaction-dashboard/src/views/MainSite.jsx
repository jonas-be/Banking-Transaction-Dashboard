import { useState, useEffect } from 'react'
import Header from "../components/Header"
import Footer from "../components/Footer"
import DashboardOne from "../components/DashboardOne"
import ChartComponent from "../components/ChartComponent"

function MainSite() {

    const [transactions, setTransactions] = useState([])

    useEffect(() => {
      async function getTransactions() {
        const data = await fetchTransactions()
        setTransactions(data)
      }
      getTransactions()
    }, [])
  
    async function fetchTransactions() {
      const res = await fetch('http://localhost:8080/all')
      const data = await res.json()
  
      return data;
    }

    return (
        <>
            <Header />
            <DashboardOne transactions={transactions}/>
            <Footer />


            


        </>
    )
}

export default MainSite
