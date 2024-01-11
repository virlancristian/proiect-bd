import React from 'react';
import { useState, useEffect } from 'react';

const DbMechanicList = () => {
    const [mechanics, setMechanics] = useState([]);
    const [services, setServices] = useState([]);
    const [visible1, setVisible1] = useState(false);
    const [mechanic, setMechanic] = useState({});
    const [visible2, setVisible2] = useState(false);
    const [newMechanic, setNewMechanic] = useState({
        firstName: '',
        lastName: '',
        workingLocation: ''
    });
    const [cars, setCars] = useState([]);

    const getMechanics = async () => {
        const response = await fetch(`http://localhost:8080/api/mechanics/get`);
        const data = await response.json();
        console.log(data);
        setMechanics(data);
    }

    const getServices = async () => {
        const response = await fetch(`http://localhost:8080/api/services/get`);
        const data = await response.json();
        
        setServices(data);
    }

    const getCars = async () => {
        const response = await fetch(`http://localhost:8080/api/cars/get`);
        const data = await response.json();

        setCars(data);
    }

    useEffect (() => {
        getCars();
        getServices();
        getMechanics();
    }, []);

    const openUpdateMenu = (mechanic) => {
        setMechanic(mechanic);
        setVisible1(!visible1);
    }

    const updateMechanic = async () => {
        const serviceList = document.querySelector('.select-service');
        const selectedService = serviceList.options[serviceList.selectedIndex].value;
        const response = await fetch(`http://localhost:8080/api/mechanics/update?firstName=${mechanic.first_name}&lastName=${mechanic.last_Name}&workingLocation=${selectedService}`, {
            method: 'POST'
        })

        if(response.ok) {
            window.alert("Modificare aplicata cu success!");
            window.location.reload();
        }
    }

    const changeNewMechanic = (event) => {
        const {name, value} = event.target;

        setNewMechanic((prevMechanic) => ({
            ...prevMechanic,
            [name]: value
        }))
    }

    const addMechanic = async () => {
        const serviceList = document.querySelector('.select-service-add');
        const selectedService = serviceList.options[serviceList.selectedIndex].value;
        const response = await fetch(`http://localhost:8080/api/mechanics/add?firstName=${newMechanic.firstName}&lastName=${newMechanic.lastName}&workingLocation=${selectedService}`, {
            method: 'POST'
        })

        if(response.ok) {
            window.alert("Mecanicul a fost adaugat cu success!");
            window.location.reload();
        }
    }

    const deleteMechanic = async (mechanic) => {
        const response = await fetch(`http://localhost:8080/api/mechanics/delete?firstName=${mechanic.first_name}&lastName=${mechanic.last_Name}&workingLocation=${mechanic.working_Location}`, {
            method: 'POST'
        })

        if(response.ok) {
            window.alert("Mecanicul a fost sters cu success!");
            window.location.reload();
        }
    }

    const applyFilter = async () => {
        const serviceList = document.querySelector('.select-service-filter');
        const selectedService = serviceList.options[serviceList.selectedIndex].value;

        const carList = document.querySelector('.select-car-filter');
        const selectedCar = carList.options[carList.selectedIndex].text.split("\ ");
        console.log(selectedService + ' ' + selectedCar);
        if(selectedService !== 'default') {
            if(selectedCar[0] !== '--') {
                const response = await fetch(`http://localhost:8080/api/mechanics/get?serviceName=${selectedService}&brand=${selectedCar[0]}&model=${selectedCar[1]}`);
                const data = await response.json();
                setMechanics(data);
            } else {
                const response = await fetch(`http://localhost:8080/api/mechanics/get?serviceName=${selectedService}`);
                const data = await response.json();
                setMechanics(data);
            }
        } else {
            getMechanics();
        }
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
            <p>Lucreaza la masina</p>
            <select className='select-car-filter'>
                <option value='default'>-- Oricare --</option>
                {
                    cars.map((car) => 
                        <option value={car.carID}>{car.brand_Name} {car.model_Name}</option>
                    )
                }
            </select>
            <p onClick={applyFilter}>Aplica filtrul</p>
        </div>
        <table>
            <tr>
                <th>Nume mecanic</th>
                <th>ID Service alocat</th>
            </tr>
            {
                mechanics.map((mechanic) => 
                    <tr>
                        <td>{mechanic.first_name} {mechanic.last_Name}</td>
                        <td>{mechanic.working_Location}</td>
                        <td onClick={() => openUpdateMenu(mechanic)}>Editeaza</td>
                        <td onClick={() => deleteMechanic(mechanic)}>Sterge</td>
                    </tr>
                )
            }
        </table>
        <p onClick={() => setVisible2(!visible2)}>Adauga mecanic</p>
        {
            visible1 ? <div className='mechanic-update'>
            <p>Alege un service in care sa fie alocat mecanicul</p>
            <select className='select-service'>
                {
                    services.map((service) => 
                        <option value={service.serviceLocationID}>{service.serviceLocationID}. {service['name']}</option>
                    )
                }
            </select>
            <p onClick={updateMechanic}>Actualizeaza</p>
        </div>
         : <></>
        }
        {
            visible2 ? 
            <div className='create-mechanic-form'>
                <p>Nume</p>
                <input type="text" name='firstName' value={newMechanic.firstName} onChange={changeNewMechanic}></input>
                <p>Prenume</p>
                <input type='text' name='lastName' value={newMechanic.lastName} onChange={changeNewMechanic}></input>
                <p>Service</p>
                <select className='select-service-add'>
                {
                    services.map((service) => 
                        <option value={service.serviceLocationID}>{service.serviceLocationID}. {service['name']}</option>
                    )
                }
            </select>
            <p onClick={addMechanic}>Adauga</p>
            </div> : <></>
        }
        </>
    )
};

export default DbMechanicList;