# FaceIT Tech Task
## Table of Contents
1. [Introduction](#Introduction)
2. [Tasks](#Tasks)
3. [Project Technologies](#Project-Technologies)
4. [Installation](#Installation)
5. [API Documentation](#API-Documentation)

## Introduction
Imagine you have to design an application for a restaurant ordering system
(no GUI, only back-end (REST APIs)). 


## Tasks

### Technologies:
- Use Spring Boot for server-side development.
- Use any H2 database

### Functional requirements:

Please follow the requirements listed below:

- It’s possible to order lunch and/or drink.
- Lunch consists of a main course and dessert.
- Each meal and drink must have a name and price.
- There are three cuisines available to choose from (Polish, Mexican, Italian).
- When ordering a drink, the customer can additionally ask for ice cubes or/and lemon.
- The solution you come up with should be extendable (it should be possible to add new
cuisines/dishes in the future).
- It should contain unit tests.

## Project Technologies
- Java version 17.0.7
- Spring Boot 3.2.5
- Maven 4.0.0
- H2
- Lombok 
- Swagger 

## Installation
To install and run the application locally, follow these steps:

1. Clone this repository to your local machine.
2. Open cmd in project root directory.
3. Provide env variables as:
    - DB_USER=your db user (default: postgres)
    - DB_PASSWORD=your db password (default: postgres)
4. Navigate to the project directory.
5. Run the Maven command to build the project. (`mvn clean install`)
6. Run the application using Maven.(`java -jar target/faceit-test-task-0.0.1-SNAPSHOT.jar` or `mvn spring-boot:run`)
7. The application will start running on `http://localhost:8080`

## API Documentation:
### Swagger
Swagger is integrated into this project to provide interactive API documentation. With Swagger, you can easily explore and test the available endpoints without leaving your browser.

To access the Swagger documentation, follow these steps:

1. Ensure the application is running locally (`http://localhost:8080/test`).
2. Open a web browser and navigate to `http://localhost:8080/test/swagger-ui/index.html#/`.
3. You will see the Swagger UI interface, which displays a list of available endpoints along with detailed information about each endpoint, including request and response formats.
4. Explore the available endpoints by expanding the sections and clicking on individual endpoints to view their details.
5. You can also test the endpoints directly from the Swagger UI interface by providing input parameters and executing requests.

### API Requests

| HTTP METHOD | Endpoint                       | Description                                 |
|-------------|--------------------------------|---------------------------------------------|
| GET         | /cuisines                      | Retrieve all cuisines.                      |
| GET         | /cuisines/{id}                 | Retrieve a specific cuisines by ID.         |
| GET         | /cuisines/{id}                 | Retrieve a specific cuisines by ID.         |
| POST        | /cuisines                      | Create a new cuisines.                      |
| PUT         | /cuisines/{id}                 | Update an existing cuisines by ID.          |
| DELETE      | /cuisines/{id}                 | Delete an cuisines by ID.                   |
|             |                                |                                             |
| GET         | /maincourses                   | Retrieve all maincourses.                   |
| GET         | /maincourses/{id}              | Retrieve a specific maincourses by ID.      |
| GET         | /maincourses/cuisine/{cuisine} | Retrieve a specific maincourses by cuisine. |
| POST        | /maincourses                   | Create a new maincourses.                   |
| PUT         | /maincourses/{id}              | Update an existing maincourses by ID.       |
| DELETE      | /maincourses/{id}              | Delete an maincourses by ID.                |
|             |                                |                                             |
| GET         | /desserts                      | Retrieve all desserts.                      |
| GET         | /desserts/{id}                 | Retrieve a specific desserts by ID.         |
| GET         | /desserts/cuisine/{cuisine}    | Retrieve a specific desserts by ID.         |
| POST        | /desserts                      | Create a new order.                         |
| PUT         | /desserts/{id}                 | Update an existing desserts by ID.          |
| DELETE      | /desserts/{id}                 | Delete an desserts by ID.                   |
|             |                                |                                             |
| GET         | /drinks                        | Retrieve all drinks.                        |
| GET         | /drinks/{id}                   | Retrieve a specific drinks by ID.           |
| POST        | /drinks                        | Create a new drinks.                        |
| PUT         | /drinks/{id}                   | Update an existing drinks by ID.            |
| DELETE      | /drinks/{id}                   | Delete an drinks by ID.                     |
|             |                                |                                             |
| GET         | /orders                        | Retrieve all orders.                        |
| GET         | /orders/{id}                   | Retrieve a specific order by ID.            |
| POST        | /orders                        | Create a new order.                         |
| PUT         | /orders/{id}                   | Update an existing order by ID.             |
| DELETE      | /orders/{id}                   | Delete an order by ID.                      |
