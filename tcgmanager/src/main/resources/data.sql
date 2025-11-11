DROP TABLE IF EXISTS official_deck_cards;
DROP TABLE IF EXISTS official_decks;
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

-- Sets oficiais da API Pokémon TCG
CREATE TABLE sets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    api_id VARCHAR(50) UNIQUE,        -- ID na API (ex: base1, sv3pt5, etc)
    name VARCHAR(100) NOT NULL,       -- Nome do set
    release_date DATE,
    series VARCHAR(100),              -- Série (ex: Scarlet & Violet)
    logo_url VARCHAR(255),
    symbol_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Cartas Pokémon
CREATE TABLE cards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    api_id VARCHAR(50) UNIQUE,
    set_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    rarity VARCHAR(50),
    hp INT,
    image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (set_id) REFERENCES sets(id)
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

-- Decks oficiais criados pelo admin com base na API
CREATE TABLE official_decks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    api_id VARCHAR(50),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    set_id BIGINT,                              -- set de origem do deck
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (set_id) REFERENCES sets(id)
);

-- Dados iniciais
INSERT INTO users (username, email, password, role)
VALUES
('bruno', 'bruno@example.com', '123456', 'ADMIN'),
('laura', 'laura@example.com', '123456', 'USER');

INSERT INTO cards (api_id, name, type, rarity, hp, set_name, image_url) VALUES
('base1-58', 'Pikachu', 'Electric', 'Common', 60, 'Base Set', 'https://images.pokemontcg.io/base1/58.png'),
('base1-4', 'Charizard', 'Fire', 'Rare', 120, 'Base Set', 'https://images.pokemontcg.io/base1/4.png'),
('base1-44', 'Bulbasaur', 'Grass', 'Common', 50, 'Base Set', 'https://images.pokemontcg.io/base1/44.png');

INSERT INTO cards (api_id, set_id, name, type, rarity, hp, image_url)
VALUES
('base1-58', 1, 'Pikachu', 'Electric', 'Common', 60, 'https://images.pokemontcg.io/base1/58.png'),
('base1-4', 1, 'Charizard', 'Fire', 'Rare', 120, 'https://images.pokemontcg.io/base1/4.png'),
('base1-44', 1, 'Bulbasaur', 'Grass', 'Common', 50, 'https://images.pokemontcg.io/base1/44.png');

INSERT INTO decks (name, user_id) VALUES
('Starter Deck - Bruno', 1),
('Laura Collection', 2);

INSERT INTO deck_cards (deck_id, card_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3);

-- decks oficiais que vao ser adicionados pelo ADM
INSERT INTO official_decks (api_id, name, description, set_id)
VALUES
('base1-deck1', 'Base Set Starter Deck', 'Deck oficial da coleção Base Set', 1);

INSERT INTO official_deck_cards (official_deck_id, card_id)
VALUES
(1, 1),
(1, 2),
(1, 3);
