import React, { PureComponent } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';


export default function ChartComponent({ transactions }) {

    const data = transactions.reverse()

    return (

        <ResponsiveContainer width="100%" aspect={3}>
            <LineChart
                width={500}
                height={300}
                data={data}
                margin={{
                    top: 5,
                    right: 30,
                    left: 20,
                    bottom: 5,
                }}
            >
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="bookingDay"
                    style={{
                        fontWeight: 'normal',
                        color: 'black'
                    }}
                />
                <YAxis />
                <Tooltip />
                <Line type="monotone" dataKey="creditBalanceAfterBooking" stroke="#8884d8" activeDot={{ r: 8 }} />
            </LineChart>
        </ResponsiveContainer>
    );

}

