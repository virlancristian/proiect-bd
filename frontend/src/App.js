import { useState } from 'react';

import CarsInServiceList from "./components/cars-in-service-list";
import ServiceLocationList from "./components/service-location-list";

function App() {
  const [isListVisible, setVisibility] = useState(true);
  const [serviceID, setServiceID] = useState(null);

  const toggleVisibility = (serviceId) => {
    setVisibility(!isListVisible);
    setServiceID(serviceId)
  }

  return (
    <>
      {isListVisible
        ? <ServiceLocationList toggleVisbility={toggleVisibility}/>
        :
          <CarsInServiceList serviceID={serviceID} toggleVisbility={toggleVisibility}/>}
    </>
  );
}

export default App;
