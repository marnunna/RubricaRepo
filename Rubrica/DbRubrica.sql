create database rubrica;
use rubrica;

create table contatti (
nome varchar(45),
cognome varchar(45),
telefono varchar(45),
indirizzo varchar(70),
eta int
);

GRANT ALL
ON rubrica.*
TO 'admRubrica'@'localhost'
IDENTIFIED BY 'rubricapass';