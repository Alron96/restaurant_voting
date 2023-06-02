[![Codacy Badge](https://app.codacy.com/project/badge/Grade/71a96aa3f16a4437a698c90460bce938)](https://app.codacy.com/gh/Alron96/restaurant_voting/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

# Voting for restaurants

###### This system voting is for deciding where to dine.

-------------------------------------------------------------
- Stack: JDK 17, Spring Boot 3.0.6, Lombok, H2, Caffeine Cache, Swagger/OpenAPI 3.0
- Run: `mvn spring-boot:run` in root directory.
-----------------------------------------------------
[REST API documentation](http://localhost:8080/)  

##  Technical requirement:

Design and implement a REST API using Hibernate/Spring/SpringMVC (Spring-Boot preferred!) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant, and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote for a restaurant they want to have lunch at today
* Only one vote counted per user
* If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

As a result, provide a link to GitHub repository. It should contain the code, README.md with API documentation and couple curl commands to test it (**better - link to Swagger**).
