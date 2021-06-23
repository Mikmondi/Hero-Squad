# Hero Squad

This is the Hero Squad web application that allows users to create superheros and squads and add the heroes to specific squads.

 #Author
Michael Oduor

## Technologies Used
- Java JDK 8
- Gradle
- JUnit4
- Spark
- Maven
- PostgreSql

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


