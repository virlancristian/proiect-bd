SELECT *
FROM Cars_In_Service
RIGHT JOIN Service_Locations ON Service_Locations.ServiceLocationID = Cars_In_Service.Assigned_Service_Location
WHERE Service_Locations.ServiceLocationID = {{id}};