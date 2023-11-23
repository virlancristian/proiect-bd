import React, { useState, useEffect } from 'react';
import '../css/car-list.css';

function CarsInServiceList({serviceID, toggleVisbility}) {
    const [carList, setCarList] = useState([]);

    const fetchCars = async () => {
        const response = await fetch(`http://localhost:8080/api/cars_in_service?id=${serviceID}`);
        const data = await response.json();

        console.log(data);
        setCarList(data);
    };

    useEffect(() => {
        fetchCars();

        const intervalID = setInterval(fetchCars, 3000);
        
        return () => clearInterval(intervalID);
    }, []);

    return (
        <>
        <div className="car-list">
            <div className="car-specs">
                <div>Brand</div>
                <div>Model</div>
                <div>YOF</div>
            </div>
            {carList.map(item => (
                <div className="car" key={item.id}>
                    <div>{item.brand}</div>
                    <div>{item.model}</div>
                    <div>{item.fabricationYear}</div>
                </div>
            ))}
        </div>
        <h3 onClick={toggleVisbility}>Back to service list</h3>
        </>
    );
}

export default CarsInServiceList;