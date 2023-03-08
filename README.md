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
![register](https://user-images.githubusercontent.com/115692643/223806166-5122e86e-3704-45ac-b921-bb786234524f.PNG)

If the username is taken, the registration will not be successful:

![image](https://user-images.githubusercontent.com/115692643/223805685-bed74601-7613-4127-a979-fded67a482b5.png)

After successful registration user data is collected, default role is assigned and password is encoded:

![image](https://user-images.githubusercontent.com/115692643/223806594-e7f6cfe2-61db-461c-a527-8728f309ee2b.png)

````
* Succes Response: Code 200

#### Login
##### After successful registration, a user can log in using user name and password and receives a token
* POST
* Example Request
````
http://localhost:8080/mountainApp/auth/login
````
** Example JSON Body:
````	
![image](https://user-images.githubusercontent.com/115692643/223805782-5c8f7ff4-05a7-4e3a-b1ce-b7cb2578e618.png)

````
* Succes Response: Code 200

After successful authorization with the token, the user has access to information:

![image](https://user-images.githubusercontent.com/115692643/223805913-4d3461a1-15f2-4dc3-b580-ca80f76386d1.png)


___________________________________________________________________________________________________________________________________________

````
