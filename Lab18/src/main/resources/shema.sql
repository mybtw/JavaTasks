CREATE TABLE IF NOT EXISTS recipes (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS ingredients (
       id INT AUTO_INCREMENT PRIMARY KEY,
       recipe_id INT,
       FOREIGN KEY (recipe_id) REFERENCES recipes(id)
       name VARCHAR(255) NOT NULL,
       quantity REAL NOT NULL,
       unit VARCHAR(100) NOT NULL,
);
