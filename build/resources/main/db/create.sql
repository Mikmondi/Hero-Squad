CREATE DATABASE hero_squad
\c hero_squad
CREATE TABLE heroes (id int PRIMARY KEY, squadid int, name VARCHAR, age int, power VARCHAR, weakness VARCHAR)
CREATE TABLE squads (id int PRIMARY KEY, name VARCHAR)
CREATE DATABASE hero_squad_test WITH TEMPLATE hero_squad;