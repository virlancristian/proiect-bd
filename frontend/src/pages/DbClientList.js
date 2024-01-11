import React from 'react';
import { useState, useEffect } from 'react';

const DbClientList = () => {
    const [clients, setClients] = useState([]);
    const [clientCars, setClientCars] = useState([]);
    const [visible1, setVisible1] = useState(false);

    const getClients = async () => {
        const response = await fetch(`http://localhost:8080/api/clients/get`);
        const data = await response.json();

        setClients(data);
    }

    useEffect(() => {
        getClients()
    }, [])

    const applyFilter = async () =>{
        const date = document.querySelector('.date-picker').value;

        if(date !== '') {
            const response = await fetch(`http://localhost:8080/api/clients/get?date=${date}`);
            const data = await response.json();
            setClients(data);
        } else {
            getClients();
        }
    }

    const getClientCars = async (client) => {
        console.log(client.first_Name + ' ' + client.last_Name)
        const response = await fetch(`http://localhost:8080/api/cars/cost/get?firstName=${client.first_Name}&lastName=${client.last_Name}`)
        const data = await response.json();
        setClientCars(data);
        setVisible1(!visible1);
    }

    return (
        <>
            <div className='filters'>
                <p>Programat pe data</p>
                <input type='date' className='date-picker'></input>
                <p onClick={applyFilter}>Aplica filtrul</p>
            </div>
            <table>
                <tr>
                    <th>Nume client</th>
                    <th>Email</th>
                    <th>Telefon</th>
                    <th>Adresa</th>
                </tr>
                {
                    clients.map((client) => 
                        <tr>
                            <td>{client.first_Name} {client.last_Name}</td>
                            <td>{client.email}</td>
                            <td>{client.phone_Number}</td>
                            <td>{client.street_Address}, nr. {client.street_Number}, bloc {client.flat_Number}, apartament {client.apartment}, {client.city}</td>
                            <td onClick={() => getClientCars(client)}>Costurile clientului</td>
                        </tr>
                    )
                }
            </table>
            {
                visible1 ? <>
                    <p>Masinile clientului si costul de reparatii</p>
                    <table>
                        <tr>
                            <th>Masina</th>
                            <th>Cost reparatii</th>
                        </tr>
                        {
                            clientCars.map((car) => 
                                <tr>
                                    <td>{car.brand_Name} {car.model_Name}</td>
                                    <td>{car.repairCost}</td>
                                </tr>
                            )
                        }
                    </table>
                </> : <></>
            }
        </>
    )
};

export default DbClientList;