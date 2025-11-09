DROP TABLE IF EXISTS deck_cards;
DROP TABLE IF EXISTS decks;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS users;

-- Usuários do sistema
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cartas Pokémon
CREATE TABLE cards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    rarity VARCHAR(50),
    hp INT,
    expansion VARCHAR(100),
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Decks criados pelos usuários
CREATE TABLE decks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Relação entre decks e cartas (N:N)
CREATE TABLE deck_cards (
    deck_id BIGINT NOT NULL,
    card_id BIGINT NOT NULL,
    PRIMARY KEY (deck_id, card_id),
    FOREIGN KEY (deck_id) REFERENCES decks(id),
    FOREIGN KEY (card_id) REFERENCES cards(id)
);

-- Dados iniciais
INSERT INTO users (username, email, password, role)
VALUES
('bruno', 'bruno@example.com', '123456', 'ADMIN'),
('laura', 'laura@example.com', '123456', 'USER');

INSERT INTO cards (name, type, rarity, hp, expansion, image_url) VALUES
('Pikachu', 'Electric', 'Common', 60, 'Base Set', 'https://images.pokemontcg.io/base1/58.png'),
('Charizard', 'Fire', 'Rare', 120, 'Base Set', 'https://images.pokemontcg.io/base1/4.png'),
('Bulbasaur', 'Grass', 'Common', 50, 'Base Set', 'https://images.pokemontcg.io/base1/44.png');

INSERT INTO decks (name, user_id) VALUES
('Starter Deck - Bruno', 1),
('Laura Collection', 2);

INSERT INTO deck_cards (deck_id, card_id) VALUES
(1, 1),
(1, 2),
(2, 3);
