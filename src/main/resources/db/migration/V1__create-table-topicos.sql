CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255)  not null unique,
    mensagem TEXT  not null,
    data_criacao TIMESTAMP,
    status VARCHAR(50),
    autor VARCHAR(100)  not null,
    curso VARCHAR(50)  not null,
    respostas DOUBLE
);