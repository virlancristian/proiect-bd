import React, { useState, useEffect } from 'react';
import '../css/service-location-list.css';

function ServiceLocationList({toggleVisbility}) {
    const [serviceLocationList, setServiceLocationList] = useState([]);

    const fetchServiceLocations = async () => {
        const response = await fetch('http://localhost:8080/api/service_locations');
        const data = await response.json();

        setServiceLocationList(data);
    };

    useEffect(() => {
       fetchServiceLocations();

        const intervalID = setInterval(fetchServiceLocations, 3000);

        return () => clearInterval(intervalID);
    }, []);

    return (
        <>
        <div className="select-service-location">
            <h2>Select a service</h2>
        </div>
        <div className="service-location-list">
            {serviceLocationList.map(item => (
                <div className="service-location" key={item.id} onClick={() => toggleVisbility(item.id)}>{item.name}</div>
            ))}
        </div>
        </>
    );
}

export default ServiceLocationList;