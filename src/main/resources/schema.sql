CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(50) UNIQUE NOT NULL,
                                     age INT NOT NULL,
                                     password_hash VARCHAR(255) NOT NULL,
                                     role varchar(255)
);
