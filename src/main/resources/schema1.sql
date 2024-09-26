DROP TABLE IF EXISTS chess_game;
DROP TABLE IF EXISTS chess_file;

-- Create a table to store game files
CREATE TABLE IF NOT EXISTS chess_file (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    file BYTEA NOT NULL,
    created TIMESTAMP WITH TIME ZONE NOT NULL,
    updated TIMESTAMP WITH TIME ZONE NOT NULL
  );

-- Create a table to store user information
CREATE TABLE IF NOT EXISTS chess_game (
    id SERIAL PRIMARY KEY,
    white_player VARCHAR(20) NOT NULL,
    black_player VARCHAR(20) NOT NULL,
    result VARCHAR(5) NOT NULL,
    created TIMESTAMP WITH TIME ZONE NOT NULL,
    updated TIMESTAMP WITH TIME ZONE NOT NULL,
    game_file_id INT,
    FOREIGN KEY (game_file_id) REFERENCES CHESS_FILE(id)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    roles TEXT[]
);