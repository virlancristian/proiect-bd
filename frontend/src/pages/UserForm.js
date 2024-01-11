import React from 'react';
import { useState, useEffect } from 'react';

const inputNames = {
    First_name: 'Nume',
    Last_name: 'Prenume',
    email: 'E-mail',
    phone: 'Telefon',
    address: 'Strada',
    number1: 'Nr. strada',
    number2: 'Bloc(optional)',
    apartment: 'Apartament(optional)',
    city: 'Oras'
}

const inputNames1 = {
    brand: 'Marca',
    model: 'Model',
    year: 'An fabricatie',
    engine: 'Marime motor',
    fuel: 'Combustibil',
    hp: 'Cai putere'
}

const UserForm = () => {
    const [malfunctions, setMalfunctions] = useState([]);
    const [client, setClient] = useState({
        First_name: '',
        Last_name: '',
        email: '',
        phone: '',
        address: '',
        number1: '',
        number2: '',
        apartment: '',
        city: ''
    });
    const [car, setCar] = useState({
        brand: '',
        model: '',
        year: '',
        engine: '',
        fuel: '',
        hp: ''
    })

    const changeClient = (event) => {
        const {name, value} = event.target;

        setClient((prevClient) => ({
            ...prevClient,
            [name]: value
        }))
    }

    const changeCar = (event) => {
        const {name, value} = event.target;

        setCar((prevCar) => ({
            ...prevCar,
            [name]: value
        }))
    }

    const insertClient = async () => {
        const response = await fetch(`http://localhost:8080/api/clients/add?firstName=${client.First_name}&lastName=${client.Last_name}&email=${client.email}&phone=${client.phone}&address=${client.address}&number1=${client.number1}&number2=${client.number2}&apartment=${client.apartment}&city=${client.city}`,
        {
            method: 'POST'
        });
        
        if(response.ok) {
            const response1 = await fetch(`http://localhost:8080/api/clients/id/get?email=${client.email}`);
            const data = await response1.json();
            insertCar(data);
        }
    }

    const insertCar = async (carOwner) => {
        const response = await fetch(`http://localhost:8080/api/cars/add?carOwner=${carOwner}&brand=${car.brand}&model=${car.model}&year=${car.year}&engine=${car.engine}&fuel=${car.fuel}&hp=${car.hp}`,
        {
            method: 'POST'
        })

        if(response.ok) {
            window.alert('Inregistrare facuta cu success!');
        }
    }

    useEffect(() => {
        const getMalfunctions = async () => {
            const response = await fetch(`http://localhost:8080/api/malfunctions/get`);
            const data= await response.json();

            setMalfunctions(data);
        }

        getMalfunctions();
    }, [])

    return (
        <>
        <h2>Completati urmatorul formular pentru a putea va putea programa masina pentru reparare</h2>
        <div className='client-register-form'>
            {
                Object.keys(client).map((key) =>
                    <>
                    <p>{inputNames[key]}</p>
                    <input type="text" name={key} value={key.value} onChange={changeClient}></input>
                    </>
                )
            }
            {
                Object.keys(car).map((key) => 
                    <>
                    <p>{inputNames1[key]}</p>
                    <input type="text" name={key} value={key.value} onChange={changeCar}></input>
                    </>
                )
            }
            <p onClick={insertClient}>Trimite</p>
        </div>
        </>
    );
};

export default UserForm;