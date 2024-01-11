import React from 'react';
import { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

import '../css/db-car-list.css';

const DbCarList = ({ service }) => {
    const [cars, setCars] = useState([]);
    const [services, setServices] = useState([]);
    const [clients, setClients] = useState([]);
    const [carMalfunctions, setCarMalfunctions] = useState([]);
    const [car, setCar] = useState({});
    const [visible1, setVisible1] = useState(false);
    const [visible2, setVisible2] = useState(false);
    const [nostock, setNostock] = useState([]);

    const getCars = async () => {
        const response = await fetch(`http://localhost:8080/api/cars/get`);
        const data = await response.json();
        setCars(data);
    }
    const getServices = async () => {
        const response = await fetch(`http://localhost:8080/api/services/get`);
        const data = await response.json();
        
        setServices(data);
    }

    const getClients = async () => {
        const response = await fetch(`http://localhost:8080/api/clients/get`);
        const data = await response.json();

        setClients(data);
    }

    const getNostock = async () => {
        const response = await fetch(`http://localhost:8080/api/cars/nostock/get`);
        const data = await response.json();

        setNostock(data);
    }

    useEffect(() => {
        getClients();
        getServices();
        getCars();
        getNostock();
    }, [])

    const openUpdateMenu = (car) => {
        setCar(car);
        setVisible1(!visible1);
    }

    const updateCar = async () => {
        const serviceList = document.querySelector('.select-service');
        const selectedService = serviceList.options[serviceList.selectedIndex].value;
        const response = await fetch(`http://localhost:8080/api/cars/update?carOwner=${car.car_Owner}&brand=${car.brand_Name}&model=${car.model_Name}&assignedService=${selectedService}`, {
            method: 'POST'
        })

        if(response.ok) {
            window.alert("Modificare aplicata cu success!");
            window.location.reload();
        }
    }

    const deleteCar = async (car) => {
        const response = await fetch(`http://localhost:8080/api/cars/delete?carOwner=${car.car_Owner}&brand=${car.brand_Name}&model=${car.model_Name}&assignedService=${car.assigned_Service_Location}`, {
            method: 'POST'
        });

        if(response.ok) {
            window.alert("Masina a fost stersa din baza de date!");
            window.location.reload();
        }
    }

    const applyFilter = async () => {
        const serviceList = document.querySelector('.select-service-filter');
        const selectedService = serviceList.options[serviceList.selectedIndex].value;

        const clientList = document.querySelector('.select-client-filter');
        const selectedClient = clientList.options[clientList.selectedIndex].text.split("\ ");

        const scheduledList = document.querySelector('.select-scheduled-filter');
        const selectedSchedule = scheduledList.options[scheduledList.selectedIndex].value;
        
        const date = document.querySelector('.date-picker').value;
        console.log(date);

        if(selectedService !== 'default' && date === '') {
            const response = await fetch(`http://localhost:8080/api/cars/get?serviceName=${selectedService}`);
            const data = await response.json();

            setCars(data);

            return;
        } else if(date !== '') {
            const response = await fetch(`http://localhost:8080/api/cars/get?serviceName=${selectedService}&date=${date}`);
            const data = await response.json();

            setCars(data);
        } else {
            getCars();
        }

        if(selectedClient[0] !== '--' && selectedClient[1] !== 'Oricare') {
            const response = await fetch(`http://localhost:8080/api/cars/get?firstName=${selectedClient[0]}&lastName=${selectedClient[1]}`);
            const data = await response.json();
            setCars(data);

            return;
        }

        if(selectedSchedule == 'yes') {
            const response = await fetch(`http://localhost:8080/api/cars/get?scheduled=true`)
            const data = await response.json();

            setCars(data);
        }
    }

    const getCarMalfunctions = async (car) => {
        const response = await fetch(`http://localhost:8080/api/malfunctions/get?brand=${car.brand_Name}&model=${car.model_Name}&serviceID=${car.assigned_Service_Location}&ownerID=${car.car_Owner}`);
        const data = await response.json();

        setCarMalfunctions(data);
        setVisible2(!visible2);
    }

    return (
        <>
        <div className='filters'>
            <p>Filtre</p>
            <p>Service</p>
            <select className='select-service-filter'>
                <option value='default'>-- Oricare --</option>
                {
                    services.map((service) => 
                        <option value={service.name}>{service.serviceLocationID}. {service['name']}</option>
                    )
                }
            </select>
            <p>Apartin clientului</p>
            <select className='select-client-filter'>
                <option value='default'>-- Oricare --</option>
                {
                    clients.map((client) => 
                        <option value={client.first_Name}>{client.first_Name} {client.last_Name}</option>
                    )
                }
            </select>
            <p>Sunt neprogramate</p>
            <select className='select-scheduled-filter'>
                <option value='no'>Nu</option>
                <option value='yes'>Da</option>
            </select>
            <p>Sunt programate pe data(trebuie sa fie ales service-ul)</p>
            <input type="date" className='date-picker'></input>
            <p onClick={applyFilter}>Aplica filtrul</p>
        </div>
        <table>
             <tr>
                <th>Masina</th>
                <th>ID Locatie Service</th>
                <th>An fabricatie</th>
                <th>Motor</th>
                <th>Combustibil</th>
                <th>Cai putere</th>
             </tr>
             {
                cars.map((car) => 
                    <tr>
                        <td>{car.brand_Name} {car.model_Name}</td>
                        <td>{car.assigned_Service_Location}</td>
                        <td>{car.year_of_fabrication}</td>
                        <td>{car.engine_Size}</td>
                        <td>{car.fuel}</td>
                        <td>{car.horse_Power}</td>
                        <td onClick={() => getCarMalfunctions(car)}>Vezi defectiunile masinii</td>
                        <td onClick={() => openUpdateMenu(car)}>Modifica</td>
                        <td onClick={() => deleteCar(car)}>Sterge</td>
                    </tr>
                )
             }
        </table>
        {
            visible1 ? <div className='car-update'>
            <p>Alege un service in care sa fie alocata masina</p>
            <select className='select-service'>
                {
                    services.map((service) => 
                        <option value={service.serviceLocationID}>{service.serviceLocationID}. {service['name']}</option>
                    )
                }
            </select>
            <p onClick={updateCar}>Actualizeaza</p>
        </div>
         : <></>
        }
        {
            visible2 ? 
            <div className='car-malfunctions'>
                {
                    carMalfunctions.map((malfunction) => 
                        <p>{malfunction.malfunction}</p>
                    )
                }
                <p onClick={() => setVisible2(!visible2)}>Inchide</p>
            </div>
            : <></>
        }
        <h2>Statistici</h2>
        <h3>Masini care au nevoie de piese care nu mai sunt in stoc</h3>
        <table>
            <tr>
                <th>Masina</th>
                <th>ID Locatie service</th>
                <th>Piesa care nu mai este in stoc</th>
            </tr>
            {
                nostock.map((car) =>
                    <tr>
                        <td>{car.brand_Name} {car.model_Name}</td>
                        <td>{car.assigned_Service_Location}</td>
                        <td>{car.piece_Name}</td>
                    </tr>
                )
            }
        </table>
        </>
    )
};

export default DbCarList;