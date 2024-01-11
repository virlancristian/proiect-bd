import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './css/index.css';
import reportWebVitals from './reportWebVitals';
import UserForm from './pages/UserForm';
import DbCarList from './pages/DbCarList';
import DbMechanicList from './pages/DbMechanicList';
import DbClientList from './pages/DbClientList';

const router = createBrowserRouter([
  {
    path: "/",
    element: <UserForm />
  },
  {
    path: "/database/cars",
    element: <DbCarList />
  },
  {
    path: "/database/mechanics",
    element: <DbMechanicList />
  },
  {
    path: "database/clients",
    element: <DbClientList />
  }
])

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

reportWebVitals();
