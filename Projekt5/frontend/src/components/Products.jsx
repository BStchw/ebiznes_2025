import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Products() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/products')
            .then(res => setProducts(res.data))
            .catch(err => console.error('Błąd:', err));
    }, []);

    return (
        <div>
            <h2>Produkty</h2>
            <ul>
                {products.map(p => (
                    <li key={p.ID}>{p.name} – {p.price} zł</li>
                ))}
            </ul>
        </div>
    );
}

export default Products;
