CREATE TABLE  users(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    roles ENUM('tecnico', 'adm', 'atleta') NOT NULL
);

CREATE TABLE  coach(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    users_id BIGINT UNIQUE NOT NULL,
    team VARCHAR(255),
    FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE  athlete(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(100),
    coach_id BIGINT,
    users_id BIGINT,
    FOREIGN KEY (coach_id) REFERENCES coach(id) ON DELETE SET NULL,
    FOREIGN KEY (users_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE  competition(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    name VARCHAR(100) NOT NULL,
    pool_type INT
);

CREATE TABLE  proof(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    competition_id BIGINT NOT NULL,
    distance INT,
    style_type ENUM('CRAWL', 'BORBOLETA', 'PEITO', 'COSTAS', 'MEDLEY') NOT NULL,
    FOREIGN KEY (competition_id) REFERENCES competition(id) ON DELETE CASCADE
);

CREATE TABLE  partial(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    athlete_id BIGINT,
    proof_id BIGINT,
    partial_number INT,
    time FLOAT,
    frequency FLOAT,
    serie INT,
    FOREIGN KEY (athlete_id) REFERENCES athlete(id) ON DELETE CASCADE,
    FOREIGN KEY (proof_id) REFERENCES proof(id) ON DELETE CASCADE
)


