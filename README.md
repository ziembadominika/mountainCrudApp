![github](https://user-images.githubusercontent.com/40741056/74937413-4304d980-53ec-11ea-8010-58655042feb5.jpg)

# About

##### Backend and REST application uses Database(MySQL) and Spring Boot. The application that combines my two passions: coding and mountains. Still working on it.

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
####  REGISTER
##### Checking if username isn't already taken, collecting user data, assigning the default "User" role and encoding the password
* POST
* Example Request:
````
http://localhost:8080/mountainApp/auth/register
````           
* **Example JSON Body:**

![2](https://user-images.githubusercontent.com/115692643/223805685-bed74601-7613-4127-a979-fded67a482b5.png)

**After successful registration user data is collected, default role is assigned and password is encoded:**

![3](https://user-images.githubusercontent.com/115692643/223806594-e7f6cfe2-61db-461c-a527-8728f309ee2b.png)

* Succes Response: Code 200

#### LOGIN
##### After successful registration, a user can log in using user name and password and receives a token
* POST
* Example Request

http://localhost:8080/mountainApp/auth/login

** Example JSON Body:

{

"accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJEb21pOTgiLCJpYXQiOjE2NzgzNDgzMjYsImV4cCI6MTY3ODM1MzMyNn0.
YGbHQqHfsyPLRra3XBUXqsH2zQJYBRuPoYrSI4DDmiCeNJuzOl3Q7Foime2YPkky3baE_1eZxunuAi-oANzczw",

"tokenType": "Bearer"

}

* Succes Response: Code 200



___________________________________________________________________________________________________________________________________________

````
