SET MODE PostgresQl;

CREATE DATABASE hero_squad;
\c hero_squad;
CREATE TABLE heroes (id SERIAL PRIMARY KEY, name VARCHAR, age INTEGER,  power VARCHAR,  weakness VARCHAR,  squadId INTEGER);

CREATE TABLE squads ( id SERIAL PRIMARY KEY, name VARCHAR);
CREATE DATABASE hero_squad_test WITH TEMPLATE hero_squad;
