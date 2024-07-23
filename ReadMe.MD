## Steps to run code

1. Run DemoApplication.java
2. Open http://localhost:8080/api/v1/student on your web browser
3. Use PostMan to test API calls
    - To create a student: `POST http://localhost:8080/api/v1/student`
      - with a request body containing the name, email, dob of a student in JSON format
    - To delete a student: `DELETE http://localhost:8080/api/v1/student/{studentId}`
      - where `studentId` is the student you want to delete
    - To update the name and/or email of a student: `PUT http://localhost:8080/api/v1/student/{studentId}?name={newname}&email={newemail}`
      - where `studentId` is the student you want to update
      - where `newname (optional)` and `newemail (optional)` are the new values of that student

## Definitions


- API layer - e.g. when client clicks on a button, which sends an API call to service layer

- Service layer - processes the API call

- Data access layer - performs crud operations on the database through the API call from service layer

![](images/spring_overview.png)

- Gradle - for android projects
- Maven - a build automation tool that helps you consolidate all dependencies in one place
  - dependencies are the technologies/software you work with in your project
  - maven downloads and stores all the dependencies you need in your local repo, so you don't need internet access (apart from the first time you access the dependency

## More definitions

- *Spring Beans*
    - In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container are called beans.


## What is **MVC**?

- `View`: displays information to user
- `Controller`: receives request from user, processes the request, gets data from Model, and updates the View
- `Model`: communicates with db


## What's so good about Spring framework?

- Dependency Injection 
  - Typically, objects control the instantiation and location of its dependencies by calling constructor methods
  - With the IoC principle, objects define their dependencies without creating them. The IoC container then injects those dependencies when it creates them
  - What's the advantage? Less coupling


## Spring Boot MVC: how it works

- Controller: handles HTTP requests (returns JSON response)
- Service: performs business logic (including parsing of requests and error handling)
- Repository: interacts with db


# How does Service class handle errors?

- Create a custom exception class, e.g. `ApiRequestException` that handles exceptions when client makes a HTTP request. This exception is thrown when the query on the db fails
  - `ApiRequestException` extends `RuntimeException`
- Create a general custom exception class, e.g. `ApiException` that defines the fields you want the client to see when the JSON error response is returned. This `ApiException` is wrapped in a `ResponseEntity`, to be returned to client 


## Spring Annotations

- `@Component`
  - Annotates a class. Tells Spring that this class is a bean, and Spring manages all beans. A bean has to be instantiated by Spring, and made available for dependency injection
  - Usually an app uses this `@Component` annotation. If it doesn't it probably uses a combination of `@Service`, `@Repository`, and `@Controller`, which are beans with more specific roles


- `@Service`
  - Annotates a service-layer class. Handles business logic


- `@Repository`
  - Annotates a data-access class. Interacts directly with database
  - Every entity needs its own `@Repository` class, which extends `JpaRepository`


- `@Controller`
  - Annotates an API-layer class. Receives HTTP requests and returns responses


- `@ControllerAdvice`
  - Annotates a class that handles ALL exceptions thrown


- `@ExceptionHandler`
  - Annotates a method in a `@ControllerAdvice` class. Each method can handle 1 or more different types of exceptions thrown
  - e.g. `@ExceptionHandler(value = {MethodArgumentNotValidException .class})`


- `@Autowired`
  - Can be annotated on a constructor, field, or a setter method. Tells Spring to wire up the dependency for you. Spring finds a bean of that data type, and injects it for you. If there are multiple, then have to use more specific annotations
  - **ONLY** needed if your class has multiple constructors. If there is only 1, Spring already knows to inject the dependency for you


- `@Value`
  - Annotates a string e.g. `@Value("${database.url}")`
  - Spring will look for `database.url` from the property file and inject that value into the string 
  - Promotes loose coupling by injecting values instead of hardcoding 
  - Only works for a class with `@Component` annotation


- `@Valid` or `@Validated`
  - Annotates a function parameter. Spring checks that the constraints set on the fields of the validated object is valid
  - If the checks fail, then you can capture that exception and tell the client what fields failed the validation

- `@Bean`
  - Annotates a factory method (a method that returns an object). This method now instantiaties a Bean


- `@RequestMapping`
  - Annotates a controller class or method
    Maps all HTTP requests to that URL
  - Within that class, can map methods to more specific URLs based on `path`


- `@RestController`
  - A controller class that returns JSON response


- `@SpringBootApplication`
  - Initializes all beans, `@Configuration` files, etc upon launching the application. Is the gateway to running your app


## Spring Annotations #2

- `@Nullable`
  - Annotation to indicate that a specific parameter, return value, or field can be null.


- `@NonNull`
  - Annotation to indicate that a specific parameter, return value, or field cannot be null (not needed on parameters, return values, and fields where @NonNullApi and @NonNullFields apply, respectively)


- `@NonNullApi`
  - Annotation at the package level that declares non-null as the default semantics for **parameters** and **return values**.


- `@NonNullFields`
  - Annotation at the package level that declares non-null as the default semantics for **fields**


## Data Transfer Objects (DTOs)
- Used for PUT requests. Spring analyses the JSON request and maps it to the DTO. Fields can be extracted from the DTO and used to update the db
- Does not have constructors



## Difference between **POST** and **PUT** http requests
- POST is used to create a resource
  - POST updates a resource, adds a subsidiary resource, or causes a change. A POST is not idempotent, in the way that x++ is not idempotent
use this when you need the server to be in control of URL generation of your resources, e.g. asking server to create a user


- PUT is used to insert, or replace if already exists, a resource
  - PUT implies putting a resource - creating or completely replacing whatever is available at the given URL with a different thing. By definition, a PUT is idempotent. PUT replaces the resource at the known url if it already exists, so sending the same request twice has no effect.
however, idempotent property is assumed. You have to implement this property correctly in the server
use PUT when you know the URL of thing you will create, e.g. updating a specific user



## Steps I did to create this 'application'
1. go to spring initializr.com and download a spring mvc dependencies file
2. go to intellij and open the file

<!-- -->

1. in postgresql, run code `CREATE DATABASE student`
2. then run `GRANT ALL PRIVILEGES ON DATABASE "student" to postgres`
3. when you run `\l`, you can now see student as a database which you can connect to using `\c student`

<!-- -->

1. to connect intellij to postgre database 
   - install DB Browser plugin (already did)
   - go to view -> tool windows -> DB Browser
   - add new connection (+ icon) -> postgresql, then under database field put in the name 'student' (test connection to check)

2. to test if api calls work correctly
   - open postman (website)
   - by calling the correct api url with the appropriate parameters/request body, the data in localhost as well as postgresql database should update accordingly