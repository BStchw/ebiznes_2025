import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Products from "./components/Products";
import Payment from "./components/Payment";
import Cart from "./components/Cart";
import { CartProvider } from './context/CartContext';


function App() {
    return (
        <CartProvider>
            <div className="app-container">
                <Router>
                    <nav>
                        <Link to="/">Produkty</Link> |{" "}
                        <Link to="/cart">Koszyk</Link> |{" "}
                        <Link to="/payment">Płatność</Link>
                    </nav>
                    <Routes>
                        <Route path="/" element={<Products />} />
                        <Route path="/cart" element={<Cart />} />
                        <Route path="/payment" element={<Payment />} />
                    </Routes>
                </Router>
            </div>
        </CartProvider>
    );
}



export default App;
