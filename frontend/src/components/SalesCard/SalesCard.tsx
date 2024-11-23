import axios from 'axios';

//hooks
import { useEffect, useState } from 'react';

// components
import DatePicker from 'react-datepicker';
import NotificationButton from '../NotificationButton/NotificationButton'

//css
import './SalesCard.css'
import "react-datepicker/dist/react-datepicker.css"

// url
import { BASE_URL } from '../../utils/request.ts';

//models
import { Sale } from '../../models/sale.ts';


const SalesCard = () => {

    const url = BASE_URL;

    const max = new Date()
    const min = new Date(new Date().setDate(max.getDate() - 365))

    const [minDate, setMinDate] = useState(min);
    const [maxDate, setMaxDate] = useState(max);

    const [sales, setSales] = useState<Sale[]>([]);

    useEffect(() => {

        const dmin = minDate.toISOString().slice(0,10);
        const dmax = maxDate.toISOString().slice(0,10);

        axios.get(`${url}/sales?minDate=${dmin}&maxDate=${dmax}`)
            .then(response => {
                setSales(response.data.content);
            })
    }, [minDate,maxDate])

    return (
        <div className="dsmeta-container">
            <div className="dsmeta-card">
                <h2 className="dsmeta-sales-title">Vendas</h2>
                <div>
                    <div className="dsmeta-form-control-container">
                        <DatePicker
                            selected={minDate}
                            onChange={(date: Date) => setMinDate(date)}
                            className='dsmeta-form-control'
                            dateFormat="dd/MM/yyyy"
                        />
                    </div>
                    <div className="dsmeta-form-control-container">
                        <DatePicker
                            selected={maxDate}
                            onChange={(date: Date) => setMaxDate(date)}
                            className='dsmeta-form-control'
                            dateFormat="dd/MM/yyyy"
                        />
                    </div>
                </div>

                <div>
                    <table className="dsmeta-sales-table">
                        <thead>
                            <tr>
                                <th className="show992">ID</th>
                                <th className="show576">Data</th>
                                <th>Vendedor</th>
                                <th className="show992">Visitas</th>
                                <th className="show992">Vendas</th>
                                <th>Total</th>
                                <th>Notificar</th>
                            </tr>
                        </thead>
                        <tbody>
                            {sales?.map(sale => (
                                <tr key={sale.id}>
                                    <td className='show992'>{sale.id}</td>
                                    <td className='show576'>{new Date(sale.date).toLocaleDateString()}</td>
                                    <td>{sale.sellerName}</td>
                                    <td className='show992'>{sale.visited}</td>
                                    <td className='show992'>{sale.deals}</td>
                                    <td>{sale.amount.toFixed(2)}</td>
                                    <td>
                                        <div className="dsmeta-red-btn-container">
                                            <NotificationButton saleId={sale.id} />
                                        </div>
                                    </td>
                                </tr>
                            ))}
                        </tbody>

                    </table>
                </div>

            </div>
        </div>
    )
}

export default SalesCard