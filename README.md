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
![postman](https://user-images.githubusercontent.com/115692643/223817725-b48257a5-4019-4d7b-b4f4-29c3d1994ccc.PNG)
````

If the username is taken, the registration will not be successful:

![2](https://user-images.githubusercontent.com/115692643/223805685-bed74601-7613-4127-a979-fded67a482b5.png)

After successful registration user data is collected, default role is assigned and password is encoded:

![3](https://user-images.githubusercontent.com/115692643/223806594-e7f6cfe2-61db-461c-a527-8728f309ee2b.png)

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
![image](https://user-images.githubusercontent.com/115692643/223812880-dad34fb8-bca3-429f-bc70-2d267b5ebbd4.png)

````
* Succes Response: Code 200

After successful authorization with the token, the user has access to information:
````

![image](https://user-images.githubusercontent.com/115692643/223805913-4d3461a1-15f2-4dc3-b580-ca80f76386d1.png)




___________________________________________________________________________________________________________________________________________

````
