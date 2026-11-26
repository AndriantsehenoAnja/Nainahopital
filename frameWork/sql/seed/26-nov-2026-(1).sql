INSERT INTO role (libelle) VALUES
('ADMIN'),
('USER');

INSERT INTO utilisateur
(nom, prenom, email, telephone, mdp)
VALUES
('Rakoto', 'Jean', 'admin@gmail.com', '0340000000', '123456'),
('Rabe', 'Paul', 'user@gmail.com', '0341111111', '123456');

INSERT INTO utilisateur_role (id_role, id_user)
VALUES
(1, 1),
(2, 2);