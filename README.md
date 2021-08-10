# Getting Started

The swagger can be used directly for tests.

http://localhost:8080/swagger-ui.html#/`

The any account should be created firstly,

`curl -X POST "http://localhost:8080/api/books" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"bookName\": \"string\", \"category\": \"string\", \"quantityInStock\": 10, \"unitPrice\": 10, \"writer\": \"string\"}"`

`curl -X POST "http://localhost:8080/api/customers" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"age\": 10, \"email\": \"bk2@gmail.com\", \"name\": \"burak\", \"surname\": \"kilinc\"}"`

`curl -X POST "http://localhost:8080/api/orders" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"bookIds\": [ { \"amount\": 1, \"bookId\": 2 } ], \"customerId\": 1, \"id\": 0, \"isValid\": true}"`

`curl -X GET "http://localhost:8080/api/statistics/all" -H "accept: */*"`

Firstly the database and table desing was configured and completed; Second basic test endpoint was coded; After then
relationship between entities were completed; Some test classes were developed; all endpoints was completed;

The tests can be run anytime.

`mvn clean install`

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

