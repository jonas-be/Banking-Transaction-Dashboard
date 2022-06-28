import React, { PureComponent } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';


export default function ChartComponent({ transactions }) {

    const data = transactions.reverse()

    console.log(data);


    return (

        <ResponsiveContainer width="100%" aspect={3} className="bg-">
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
                <XAxis dataKey="date" style={{fontWeight: 600, fill: 'black'}}/>
                <YAxis style={{fontWeight: 600, fill: 'black'}} />

                <Tooltip />
                <Line type="monotone" dataKey="balance" stroke="rgb(125, 211, 252)" strokeWidth={6} activeDot={{ r: 8 }} />
            </LineChart>
        </ResponsiveContainer>
    );

}

