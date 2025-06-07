import React from 'react';
import { useCart } from '../context/CartContext';

function Cart() {
    const { cart, removeFromCart } = useCart();

    return (
        <div className="content">
            <h2>Koszyk</h2>
            {cart.length === 0 ? (
                <p>Koszyk jest pusty.</p>
            ) : (
                <ul>
                    {cart.map(item => (
                        <li key={item.ID}>
                            {item.name} - {item.price} zł
                            <button onClick={() => removeFromCart(item.ID)}>Usuń</button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default Cart;
