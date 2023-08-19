# Initial setup required to run the app:
1) Project should (ideally) be built with Java 17 (or newer) and maven 3.9

2) First create a docker container for mysql using the following command (username will be: root, password will be: YOUR_PASSWORD):
    ~~~
    docker run -p 3306:3306 --name appkademy-mysql -e MYSQL_ROOT_PASSWORD=YOUR_PASSWORD -d mysql:latest
    ~~~


3) Connect to the database via terminal or using any db tool (such as MySQLWorkbench for example) and create an empty schema called "appkademy"

4) Run AppkademyApplication class as a java application.

# Swagger documentation:
Swagger documentation can be found be accessing: localhost:8080/documentation.

# Features:
- Liquibase integration for database change control / creation/update of tables and population of initial data
- Global error handling that returns clear and concise errors to the front end
- Spring Security JWT stateless authentication
- Uses Spring Data JPA to perform database transactions
- JaCoCo integration for code coverage reporting and enforcement
- Paginated and filter friendly search endpoints
- Swagger UI documentation
- MapStruct integration for DTO <-> POJO mapping