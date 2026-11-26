CREATE DATABASE nainaextrat;
use nainaextrat;
create table patient(
    id int primary key auto_increment,
    nom  varchar(50),
    prenom  varchar(90),
    datenaissance date,
    email varchar(150),
    telephone varchar(20)
);