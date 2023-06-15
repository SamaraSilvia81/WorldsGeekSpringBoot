create table users
(
    PRIMARY KEY (userid),
    name         VARCHAR not null,
    email        VARCHAR not null,
    password     VARCHAR not null
);

INSERT INTO users (name, email, password) VALUES ('Eudes', 'eudes@gmail.com', 'bts');
INSERT INTO users (name, email, password) VALUES ('Samara', 'samara@gmail.com', 'loki');

create table characters
(
    PRIMARY KEY (characterId),
    name         VARCHAR not null,
    photo        VARCHAR not null,
    about        VARCHAR not null
);

INSERT INTO characters (name, about, photo) VALUES ('Groot', 'arvore alienigena', 'https://uploads.jovemnerd.com.br/wp-content/uploads/2022/10/guardioes_da_galaxia_filme_solo_groot__l8b0oz-1210x544.jpg');
INSERT INTO characters (name, about, photo) VALUES ('Loki', 'deus da mentira', 'https://img.olhardigital.com.br/wp-content/uploads/2021/02/Loki.jpg');

CREATE TABLE lists (
    listId       INT PRIMARY KEY,
    name         VARCHAR NOT NULL,
    userId       INT,
    FOREIGN KEY (userId) REFERENCES users(userId)
);

CREATE TABLE listCharacter (
    listId       INT,
    characterId  INT,
    PRIMARY KEY (listId, characterId),
    FOREIGN KEY (listId) REFERENCES lists(listId),
    FOREIGN KEY (characterId) REFERENCES characters(characterId)
);

-- Inserir lista 'listadeeudes' para o usuário com id 1 e os personagens com id 1 e 2
INSERT INTO lists (name, userId) VALUES ('listadeeudes', 1);
INSERT INTO listCharacter (listId, characterId) VALUES (1, 1);
INSERT INTO listCharacter (listId, characterId) VALUES (1, 2);

-- Inserir lista 'listadesamara' para o usuário com id 2 e o personagem com id 2
INSERT INTO lists (name, userId) VALUES ('listadesamara', 2);
INSERT INTO listCharacter (listId, characterId) VALUES (2, 2);

