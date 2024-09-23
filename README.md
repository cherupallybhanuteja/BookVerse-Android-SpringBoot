# CRUD - SPRINGBOOT REST API + ANDROID UI

This repository contains a basic CRUD mobile application using Android and Spring Boot.

## TECHNOLOGY STACK
COMPONENT                           | TECHNOLOGY              	| FOR MORE INFORMATION
---                                 | ---                     	|---
Languages & Frameworks              | `Spring Boot`, `Android`  | https://spring.io/, https://developer.android.com/
Databases                           | `MySQL`                  | https://www.mysql.com/
API Tools                           | `Retrofit2`, `Picasso`    | https://square.github.io/retrofit/, https://square.github.io/picasso/
Platform                            | `Heroku`, `Docker`        | https://www.heroku.com/, https://www.docker.com/

## PREREQUISITES
- [Java (JDK)](https://openjdk.java.net/install/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)
- [Android Studio](https://developer.android.com/studio)
- [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli)

## BACKEND SETUP

1. Clone the repository and navigate to the backend directory:
   ```bash
   cd android-springboot-backend

2. Configure MySQL in application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/mangadb
spring.datasource.username=root
spring.datasource.password=yourpassword

3. Build and run the backend:
mvn clean install
mvn spring-boot:run
The API will be available at http://localhost:8080/api/v1/mangas.

## FRONTEND SETUP

1. Open the android-springboot-frontend in Android Studio.

2. Sync Gradle to install dependencies.

3. Update the Retrofit base URL to match your backend:
public class ApiClient {
public static final String BASE_URL = "http://localhost:8080/api/v1/";
}

4. Build and run the Android app on an emulator or device.

## API ENDPOINTS

GET	- /api/v1/mangas - Retrieve all mangas

GET	- /api/v1/mangas/{id} - Retrieve a manga by its ID

POST - /api/v1/mangas - Create or update a manga

DELETE - /api/v1/mangas/{id} - Delete a manga by its ID

## DEPLOYMENT

1. Deploy the backend to Heroku:

heroku login
git push heroku master
heroku addons:create jawsdb:kitefin

2. Update the base URL in the Android app to point to the Heroku deployment.
