import { useState, useEffect } from 'react'
import Loading from './Loading'

function UploadDataState() {

    const [dataState, setDataState] = useState([])

    useEffect(() => {
        async function setUp() {
            const transactionsData = await fetchDataState()
            setDataState(transactionsData)
        }
        setUp()
    }, [])

    async function fetchDataState() {
        const res = await fetch('http://localhost:8080/dataState')
        const data = await res.json()

        return data;
    }


    if (dataState.length !== 0) {
        return (
            <div className='w-full flex flex-col items-center justify-center '>
                <div className='flex w-full'>
                    <h2 className='p-2 px-6  rounded-l-xl bg-slate-500 text-white text-left text-lg font-semibold'>Data State</h2>
                    <div className='p-2 px-6 self-stretch grow rounded-r-xl bg-slate-100 text-slate-600 text-left text-lg font-medium'>{dataState.datesFrom} - {dataState.datesTo}</div>
                </div>

                <div className='p-4'></div>

                <div className={`w-full p-1 rounded-lg border-2 text-lg font-semibold ${dataState.isUptoDate ? "bg-green-50 border-green-500 text-green-500" : "bg-red-50 border-red-500 text-red-500"}`}>
                    {dataState.statusMessage}</div>

                <div className='p-4'></div>
            </div>
        )
    } else {
        return (
            <Loading />
        )
    }
}

export default UploadDataState
