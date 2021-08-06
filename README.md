# Simple REST API with Spring Boot, Kotlin, JPA

** Build and run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080/books>.

## Rest APIs

The app defines following APIs.

    GET /books          Lists all the books
    
    POST /books         Adds a Book
    
    GET /books/{title}  Gets a Book By Title
