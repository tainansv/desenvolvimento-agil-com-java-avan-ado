CREATE TABLE usuario
(
    login TEXT PRIMARY KEY NOT NULL,
    email TEXT NOT NULL,
    nome TEXT NOT NULL,
    senha TEXT NOT NULL,
    pontos INTEGER NOT NULL
);
