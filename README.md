# able-laborer-3546

# REST API for Bus Reservation System Portal 

* We have developed this REST API for Online Cab Booking Application. This API performs all the fundamental CRUD operations of any Cab Booking Application platform with user validation at every step.
* This project is developed by team of 5 Back-end Developers during project week in Masai School. 

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Spring Security

## Modules

* Login Module
* Admin Module
* Customer Module
* Driver Management Module
* Cab Management Module
* Booking Management Module

## Features

* Admin, Customer and Driver authentication JWT Token.
* Admin Features:
    * Administrator Role of the entire application
* Customer Features:
    * Registering themselves with application, and logging in to get the valid JWT token

* Driver Features:
    * Registering themselves with application, and logging in to get the valid JWT token

## Contributors

* [@Abhishek](https://github.com/abhishekyadav0888)
* [@Abhijeet Hiwale](https://github.com/)
* [@Lokesh Gola](https://github.com/)
* [@Roopa Ram](https://github.com/rooparam01)
* [@Ashish Kaushik](https://github.com/)

## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](https://github.com/abhishekyadav0888/able-laborer-3546/blob/main/RideEasy/src/main/resources/application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8088

    spring.datasource.url=jdbc:mysql://localhost:3306/rideeasy
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root

```

## API Root Endpoint

`https://localhost:8088/`

`http://localhost:8088/swagger-ui.html/`


## API Module Endpoints

### Login Module

* `POST //login/admin` : Admin can login with username and password provided at the time of registation
<!--

### Customer Module

* `POST /customers` : Register a new customer with proper data validation
  


### Admin Module

* `POST /admin` : Register a new admin with proper data validation

### Driver Module

* `POST /driver` : Register a new driver with proper data validation



### Sample API Response for Admin Register

`POST   localhost:8088/admin`

* Request Body

```
    {
        "userName": "xyz123",
        "password": "abcde",
        "name" : "dummy",
        "email" : "dummy@gmail.com",
        "mobileNumber" : "9555555555"
    }
```

* Response

```
   { adminId=1, userName=xyz123, name=dummy, email=dummy@gmail.com, mobileNumber=9555555555 }
   
```
 
### E-R Diagram Of Bus Application
---



---

### Swagger UI

---



---

### Customer and Customer Login Controller

---



---

### Admin and Admin Login Controller

---



---

### Driver and Driver Login Controller

---



---


### Trip Controller

---



---




