
-- Tabela de usuários
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

-- Tabela de badges
CREATE TABLE badge (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    icon_url VARCHAR(500)
);

-- Tabela de perfis de usuário
CREATE TABLE user_profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    xp INT,
    level INT,
    coins INT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Tabela de relacionamento entre perfis e badges
CREATE TABLE user_badges (
    profile_id BIGINT NOT NULL,
    badge_id BIGINT NOT NULL,
    PRIMARY KEY (profile_id, badge_id),
    FOREIGN KEY (profile_id) REFERENCES user_profile(id) ON DELETE CASCADE,
    FOREIGN KEY (badge_id) REFERENCES badge(id) ON DELETE CASCADE
);

-- Tabela de categorias
CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

-- Tabela de transações
CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    value DECIMAL(19,2) NOT NULL,
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL
);

-- Índices para melhorar performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_user_profile_user_id ON user_profile(user_id);
CREATE INDEX idx_user_badges_profile_id ON user_badges(profile_id);
CREATE INDEX idx_user_badges_badge_id ON user_badges(badge_id);
CREATE INDEX idx_transactions_category_id ON transactions(category_id);
CREATE INDEX idx_transactions_value ON transactions(value);