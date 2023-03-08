![github](https://user-images.githubusercontent.com/40741056/74937413-4304d980-53ec-11ea-8010-58655042feb5.jpg)

# About

##### Backend and REST application uses Database(MySQL) and Spring Boot. An application that combines my two passions: coding and mountains. Still working on it.

* search for mountains by name, height, country or continent
* mountains rating
* adding, removing and updating mountains, users and their ratings
* calculating average mountain rating
* Spring Boot Security: register, login, JWT
* application tests

## Technologies

* Java8
* Spring Boot
* Hibernate
* REST API
* Lombok
* Gradle
* MySQL
* Mockito

____________________________________________________________________________________________________________
####  Register
##### Collecting user data, assigning the default "User" role and encoding the password
* POST
* Example Request:
````
http://localhost:8080/mountainApp/auth/register
````           
* Example JSON Body:
````	
{
    "full_name": "bednarekdomi/mountainCrudApp",
    "description": null,
    "clone_url": "https://github.com/bednarekdomi/mountainCrudApp.git",
    "fork": false,
    "forks_count": 0,
    "created_at": "2023-01-09T18:23:21Z"
}]
````
* Succes Response: Code 200

#### Login
##### After successful registration, a user can log in and receives a token
* POST
* Example Request
````
http://localhost:8080/mountainApp/auth/login
````
** Example JSON Body:
````	
[
    {
        "full_name": "bednarekdomi/mountainCrudApp",
        "description": null,
        "clone_url": "https://github.com/bednarekdomi/mountainCrudApp.git",
        "fork": false,
        "forks_count": 0,
        "created_at": "2023-01-09T18:23:21Z"
    },
    {
        "full_name": "bednarekdomi/WindsurfersForecast",
        "description": "Worldwide weather forecast Service for windsurfer's ",
        "clone_url": "https://github.com/bednarekdomi/WindsurfersForecast.git",
        "fork": false,
        "forks_count": 0,
        "created_at": "2023-02-21T09:27:01Z"
    }
]
````
* Succes Response: Code 200

After successful authorization with the token, the user has access to information:

___________________________________________________________________________________________________________________________________________

````
