import { useState, useEffect } from "react"
import { Line } from 'react-chartjs-2';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
  } from 'chart.js';

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
  );

function ChartComponent({ transactions }) {

    const dates = transactions.map((transaction) => transaction.bookingDay)
    const blanaces = transactions.map((transaction) => transaction.creditBalanceAfterBooking)

    // console.log(dates);
    // console.log(blanaces);

    const [chartData, setChartData] = useState({
        datasets: []
    })

    const [chartOptions, setChartOptions] = useState({})


    useEffect(() => {
        setChartOptions({
            responsive: true,
            plugins: {
                legend: {
                  position: 'top',
                },
                title: {
                  display: true,
                  text: 'Chart.js Line Chart',
                },
              },
        })
        setChartData({
            labels: dates,
            datasets: [
                {
                    id: 1,
                    label: 'Credit Balnace',
                    data: blanaces.reverse(),
                    borderColor: "rgb(12, 45,342)",
                },
            ],
        });
    }, [])


    return (
        <div>
            <h1>⬇️ Chart below ⬇️</h1>


            <Line
                options={chartOptions}
                data={chartData}
            />

        </div>
    )
}

export default ChartComponent
