DROP TABLE IF EXISTS CHESS_GAME;

-- Create a table to store user information
CREATE TABLE IF NOT EXISTS CHESS_GAME (
    id INT AUTO_INCREMENT PRIMARY KEY,
    white_player VARCHAR(20) NOT NULL,
    black_player VARCHAR(20) NOT NULL,
    result VARCHAR(5) NOT NULL,
    created TIMESTAMP WITH TIME ZONE NOT NULL,
    updated TIMESTAMP WITH TIME ZONE NOT NULL,
    game_file VARCHAR(50) NOT NULL
);