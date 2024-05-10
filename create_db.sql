-- Connect to the newly created database
\c chessappdb;

-- Create a table to store user information
CREATE TABLE IF NOT EXISTS chess_game (
    id uuid PRIMARY KEY,
    white_player VARCHAR(20) NOT NULL,
    black_player VARCHAR(20) NOT NULL,
    result VARCHAR(5) NOT NULL,
    created_date TIMESTAMP WITH TIME ZONE NOT NULL,
    last_modified TIMESTAMP WITH TIME ZONE NOT NULL,
    game_file VARCHAR(50) NOT NULL
);
