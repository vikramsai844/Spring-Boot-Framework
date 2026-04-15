import { useState } from 'react';
import { data } from '../data/dataSet.js';
import '../index.css';

export default function Cart() {

    const [products, setProducts] = useState(data);
    const [totalNoOfProducts, setTotalNoOfProducts] = useState(data.length);


 

    const handleDeleteAll = () => {
        setProducts([]);
        setTotalNoOfProducts(0);
    }
    const handleReset = () => {
        setProducts(data);
        setTotalNoOfProducts(data.length);
    }

      function handleDelete() {
        const updateProduct = products.filter((x) => (x !== x));
        setProducts(updateProduct);
        setTotalNoOfProducts(updateProduct.length);
    }

    return (
        <div className="card">
            <h1>Cart Items:{totalNoOfProducts}</h1>
            <p>
                {
                    products.map((product) => {
                        return (
                            <div>
                                <div key={product.id}>
                                    {product.name}, Rs: {product.price}


                                </div>
                                <button onClick={() => handleDelete(product.id)}>Delete</button>
                            </div>

                        )
                    })
                }
            </p>
            <button onClick={handleDeleteAll}>Delete All</button>
            <button onClick={handleReset}>Reset</button>
        </div>
    )

}