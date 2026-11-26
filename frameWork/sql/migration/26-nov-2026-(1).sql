CREATE TABLE utilisateur(
    id int primary key auto_increment,
    nom  varchar(50),
    prenom  varchar(90),
    email varchar(150),
    telephone varchar(20),
    mdp VARCHAR(150)
);

CREATE TABLE role(
    id int primary key auto_increment,
    libelle VARCHAR(50)
);

CREATE TABLE utilisateur_role(
    id int primary key auto_increment,
    id_role INT,
    id_user INT
);