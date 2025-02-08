CREATE TABLE roles ( 
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role TEXT
);

CREATE TABLE users ( 
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT ,  
    password TEXT ,  
    role INTEGER ,
    FOREIGN KEY (role) REFERENCES roles(id) ON DELETE SET NULL  
);

CREATE TABLE characters (  
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT ,  
    surname TEXT ,  
    description TEXT
);
CREATE TABLE monsters ( 
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT ,  
    difficulty TEXT
);

CREATE TABLE weapons ( 
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT,
    rarity TEXT
);
CREATE TABLE alchemys ( 
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT ,  
    material TEXT
);
CREATE TABLE locations ( 
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT ,  
    kingdom TEXT
);


INSERT INTO roles (id, role) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

INSERT INTO users (id, name, password, role) VALUES
    (1, 'Manuel', '$2a$10$Y4DFcKGpcGwLsKMT4EjMeOO7uhiMfAC/Swev3pU4UkG6ZSSG6bgYW', 2),  
    (2, 'Pedro', '$2a$10$Y4DFcKGpcGwLsKMT4EjMeOO7uhiMfAC/Swev3pU4UkG6ZSSG6bgYW', 1);

INSERT INTO characters (id, name, surname, description) VALUES
(1, 'Geralt', 'of Rivia', 'A witcher known for his skills with a sword'), 
(2, 'Ciri', 'of Cintra', 'A princess with extraordinary powers');

INSERT INTO monsters (id, name, difficulty) VALUES
    (1, 'Drowned', 'easy'), (2, 'Kikimora', 'mid'), (3, 'Queen Kikimora', 'impossible');

INSERT INTO weapons (id, name, rarity) VALUES
    (1, 'Silver Sword', 'uncommon'), (2, 'Witchers Iron Sword', 'uncommon');

INSERT INTO alchemys (id, name, material) VALUES
    (1, 'Alp Fangs', 'Eter'), (2, 'Cadaverine', 'Rebis');
    
INSERT INTO locations (id, name, kingdom) VALUES
    (1, 'Wyzima', 'Temeria'), (2, 'Blaviken', 'Redania');