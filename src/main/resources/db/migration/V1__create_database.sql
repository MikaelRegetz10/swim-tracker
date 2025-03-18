CREATE TABLE IF NOT EXISTS users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    roles ENUM('tecnico', 'adm', 'atleta') NOT NULL
);

CREATE TABLE IF NOT EXISTS coach(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    users_id INT UNIQUE NOT NULL,
    team VARCHAR(255),
    FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS atlhete(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(100),
    coach_id INT,
    users_id INT,
    FOREIGN KEY (coach_id) REFERENCES coach(id) ON DELETE SET NULL,
    FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS competition(
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    name VARCHAR(100) NOT NULL,
    poolType INT
);

CREATE TABLE IF NOT EXISTS proof(
    id INT AUTO_INCREMENT PRIMARY KEY,
    competition_id INT NOT NULL,
    distance INT,
    styleType VARCHAR(255) CHECK (styleType IN ('CRAWL', 'BORBOLETA', 'PEITO', 'COSTAS')) NOT NULL,
    FOREIGN KEY (competition_id) REFERENCES competition(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS partial(
    id INT AUTO_INCREMENT PRIMARY KEY,
    atlhete_id INT,
    proof_id INT,
    partialNumber INT,
    time FLOAT,
    frequency FLOAT,
    FOREIGN KEY (atlhete_id) REFERENCES atlhete(id) ON DELETE CASCADE,
    FOREIGN KEY (proof_id) REFERENCES proof(id) ON DELETE CASCADE
)