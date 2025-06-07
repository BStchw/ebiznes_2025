import React, { useState } from 'react';
import axios from 'axios';

function Payment() {
    const [name, setName] = useState('');
    const [amount, setAmount] = useState('');

    const handlePay = () => {
        axios.post('http://localhost:8080/payments', {
            name,
            amount: parseFloat(amount)
        })
            .then(() => alert("Płatność wysłana!"))
            .catch(err => alert("Błąd: " + err));
    };

    return (
        <div>
            <h2>Płatność</h2>
            <input placeholder="Imię" value={name} onChange={e => setName(e.target.value)} />
            <input type="number" placeholder="Kwota" value={amount} onChange={e => setAmount(e.target.value)} />
            <button onClick={handlePay}>Zapłać</button>
        </div>
    );
}

export default Payment;
