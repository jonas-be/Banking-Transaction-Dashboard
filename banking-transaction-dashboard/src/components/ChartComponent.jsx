import React, { PureComponent } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';


export default function ChartComponent({ transactions }) {

    const data = transactions.reverse()

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
                <Line type="monotone" dataKey="balance" stroke="rgb(59, 130, 246)" strokeWidth={5} activeDot={{ r: 8 }} isAnimationActive={true} />
            </LineChart>
        </ResponsiveContainer>
    );

}

