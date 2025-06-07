import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useCart } from '../context/CartContext.jsx';

function Products() {
    const [products, setProducts] = useState([]);
    const { addToCart } = useCart();

    useEffect(() => {
        axios.get("http://localhost:8080/products")
            .then(res => setProducts(res.data))
            .catch(err => console.error("Błąd pobierania produktów:", err));
    }, []);

    return (
        <div className="content">
            <h2>Produkty</h2>
            <ul>
                {products.map(p => (
                    <li key={p.ID}>
                        {p.name} - {p.price} zł
                        <button onClick={() => addToCart(p)}>Dodaj do koszyka</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Products;
