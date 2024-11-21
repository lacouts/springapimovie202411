# Springboot project

See also:
- Reactive project: https://github.com/matthcol/movieapireactive
- Project with security chain filter: https://github.com/matthcol/spring202311, branch: mainsec

## Spring Data

### Spring Data JPA
#### Jakarta EE Specification
- https://jakarta.ee/specifications/persistence/3.2/
- https://jakarta.ee/specifications/persistence/3.2/jakarta-persistence-spec-3.2
#### Hibernate documentation
- https://hibernate.org/orm/documentation/6.6/
- https://docs.jboss.org/hibernate/orm/6.6/userguide/html_single/Hibernate_User_Guide.html
#### Spring JPA documentation
- https://spring.io/projects/spring-data-jpa
#### Inject SQL code (DDL or data) into app/test
https://howtodoinjava.com/spring-boot/execute-sql-scripts-on-startup/
#### Query by Example
https://docs.spring.io/spring-data/jpa/reference/repositories/query-by-example.html
#### Add custom repo with concrete code (extension)
https://docs.spring.io/spring-data/jpa/reference/repositories/custom-implementations.html

### Transactions
Choose @Transactional from Spring or JTA (JEE)
https://gayerie.dev/docs/spring/spring/spring_tx.html

## Error Handling
- https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
- https://www.baeldung.com/exception-handling-for-rest-with-spring

## Build project
### Maven


## Run
### CLI
```
java -jar .\target\movieapi-1.0.jar
java -jar .\target\movieapi-1.0.jar --spring.profiles.active=maria
```

### Maven
```
# with default profile(s)
mvn spring-boot:run

# with a specific pom.xml
mvn -f pom-nodev.xml spring-boot:run

# with custom profile(s) with env variable
${env:spring.profiles.active}='mariaapp'
mvn spring-boot:run

mvn spring-boot:run -Dspring-boot.run.profiles=alt
```





