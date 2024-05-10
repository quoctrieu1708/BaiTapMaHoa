create database btmahoa;
use btmahoa;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(64) NOT NULL
);

SELECT * FROM btmahoa.users;