# Assignment 2 - iTunes 2.0

Check out iTunes 2.0 [here](https://itunes-v2.herokuapp.com)

This is a repository for the second backend assignment for the Experis Accelerated Learning course.
This application makes use of Spring Boot, Sqlite, Thymeleaf and Docker. We have used a database given to us that holds
information similar to a online music store, like iTunes. You can read about the Chinook database [here](https://www.sqlitetutorial.net/sqlite-sample-database/)

## Data flow in the database
This model represents how each table is related. The model is from SqliteTutorial.
![Data flow for the Chinook database used in this project](https://www.sqlitetutorial.net/wp-content/uploads/2015/11/sqlite-sample-database-color.jpg)

## Functionality

The functionality is divided into two parts, one is accessible through running
the application and going to [localhost:8080](http://localhost:8080/) in your preferred browser. Loading this page calls our API and returns five
random tracks, artists and genres found in the database. You're also able to search for tracks, and the results
will give you any track matching with your search string. The result data contains the track name, artist, album title and
genre related to the track.

There is also an API accessible through Postman that contains customer information. This will let you get information
customers, add or update new customers and search. The full API and the available calls are present in the root folder named _ItunesAPI.postman_collection.json_.

## Structure

The project is separated into several folders, in the src we have:
- Controller
    - Contains Customer and Music controller, which decides what type of request and what function to retrieve data from
      on each url.
- Model
    - Contains all the models needed for this project. They are plain old Java objects with getters and setter.
- Repository
    - Contains an Interface for each implementation for customer and music. Also contains a connection helper.

There is also a resource-folder containing the database and templates used for Thymeleaf to show our data in a browser.

## Endpoints
Listed are all the API endpoints for this project, sorted into customer and music.
### Customer
- **GET** all customers: `/api/customers`
  - Returns all customers in an ArrayList of type Customer.
- **GET** customer by ID: `/api/customer/{id}`
  - Has an ID variable, returns a single customer with the corresponding ID.
- **GET** customer by name: `/api/customer/`
  - Has a name parameter, returns all customers with a corresponding first or last name in an ArrayList of type 
  - Customer.
- **GET** a page with customers: `/api/customers`
  - Has two parameters, limiter and offset. Limiter decides how many customers are to be returned, and offset decides at
  what index to start at. Returns all customers in given interval as an ArrayList of type Customer.
- **POST** a new customer: `/api/customers`
  - Requires a customer object sent in as JSON with the following fields:
```
{
    "firstName": "FirstName",
    "lastName": "LastName",
    "country": "Norway",
    "postalCode": 1234,
    "phone": "123 45 678",
    "email": "FirstName.LastName@itunes.com"
}
```
- **PUT** to update an existing customer: `/api/customer/{id]`
  - Has an ID variable for the specific customer to update. Requires a customer object sent in as JSON with the fields given above, and the edited value(s).
- **GET** all registered countries ordered by how many customers from a given country: `/api/customers/countries`
  - Returns all the registered countries as an ArrayList of type CustomerCountry, ordered by higest number of customers 
  first.
- **GET** the highest spending customers: `/api/customers/highSpenders`
  - Returns all customers with an invoice, ordered by the highest spender coming first. Returns as an ArrayList of type CustomerSpender.
- **GET** the most popular genre for a given customer: `/api/customers/{id}/popular/genre`
  - Returns the most popular genre for a specific customer based off their most common genre from track purchases. Returns an ArrayList of type Genre.

### Music
- **GET** five random tracks, artist and genres: `/`
  - Returns three independent lists of 5 random values found in each of their tables.
- **GET** tracks by name: `/tracks`
  - Has a parameter trackName, returns all tracks where the trackName value can be found in the tracks' title. Returns 
  the corresponding album, artist and genre to the tracks that match.
