import axios from 'axios';
import icon from '../../assets/img/notification-icon.svg';

import './NotificationButton.css';
import { BASE_URL } from '../../utils/request';
import { toast } from 'react-toastify';

type Props = {
    saleId: number;
}

const NotificationButton = ({saleId}: Props) => {

    const handleClick = (id : number) => {
        axios(`${BASE_URL}/sales/${id}/notification`)
        .then(res => {
            console.log(res);
            toast.info("SMS enviado com sucesso!");
        })
    }

    return (
        <div className="dsmeta-red-btn" onClick={() => handleClick(saleId)}>
            <img src={icon} alt="Notificar" />
        </div>
    )
}

export default NotificationButton