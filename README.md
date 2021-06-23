# Hero Squad

This is the Hero Squad web application that allowgit s users to create superheros and squads and add the heroes to specific squads.

 #Author
Michael Oduor

## Technologies Used
* [Oracle Java 8](https://www.oracle.com/java/)
* [Gradle 6.5](https://gradle.org/)
* [Spark 2.6.0](http://sparkjava.com/)
* [Postgres 42.2.5](https://www.postgresql.org/)
* [Handlebars 2.5.5](https://handlebarsjs.com/)
* [Apache Maven 2.3.2](https://maven.apache.org/)
* [Heroku Plugin 3.0.2](https://elements.heroku.com/addons)
* [JUnit4](https://junit.org/junit5/)

## Setup Instructions
- Install PostgreSql on your computer for the backend 
- Run the following command in psql to setup the database

```
CREATE DATABASE hero_squad
\c hero_squad
CREATE TABLE heroes (id int PRIMARY KEY, squadid int, name VARCHAR, age int, power VARCHAR, weakness VARCHAR)
CREATE TABLE squads (id int PRIMARY KEY, name VARCHAR)
CREATE DATABASE hero_squad_test WITH TEMPLATE hero_squad;
```

## Support and contact details
If you come across any issues, reach me at mikmondi@gmail.com or comment on the issue via GitHub. 

### License
*This project is licensed under the terms of the MIT license.*
Copyright (c) 2020 **Michael Oduor**


