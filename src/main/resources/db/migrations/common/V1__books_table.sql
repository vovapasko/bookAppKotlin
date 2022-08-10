CREATE TABLE BOOKS
(
    id              SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL ,
    author VARCHAR(255) NOT NULL ,
    is_read bool
)