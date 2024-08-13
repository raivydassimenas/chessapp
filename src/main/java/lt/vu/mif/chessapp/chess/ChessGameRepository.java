package lt.vu.mif.chessapp.chess;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ChessGameRepository {
    private final Connection connection;

    public ChessGameRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(ChessGame chessGame) throws SQLException {
        String sql = "INSERT INTO CHESS_GAME (id, white_player, black_player, result, created, updated, game_file) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, chessGame.id());
            pstmt.setString(2, chessGame.whitePlayer());
            pstmt.setString(3, chessGame.blackPlayer());
            pstmt.setString(4, chessGame.result());
            pstmt.setTimestamp(3, Timestamp.valueOf(chessGame.created()));
            pstmt.setTimestamp(4, Timestamp.valueOf(chessGame.updated()));
            pstmt.setString(5, chessGame.gameFile());
            pstmt.executeUpdate();
        }
    }

    public List<ChessGame> findAll() throws SQLException {
        String sql = "SELECT * FROM CHESS_GAME";
        List<ChessGame> chessGames = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ChessGame chessGame = new ChessGame(
                        rs.getLong("id"),
                        rs.getString("whitePlayer"),
                        rs.getString("blackPlayer"),
                        rs.getString("result"),
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("updated").toLocalDateTime(),
                        rs.getString("game_file")
                );
                chessGames.add(chessGame);
            }
            return chessGames;
        }
    }

    public Optional<ChessGame> findById(Long id) throws SQLException {
        String sql = "SELECT id, white_player, black_player, result, created, updated, game_file FROM CHESS_GAME WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ChessGame chessGame = new ChessGame(
                        rs.getLong("id"),
                        rs.getString("whitePlayer"),
                        rs.getString("blackPlayer"),
                        rs.getString("result"),
                        rs.getTimestamp("created").toLocalDateTime(),
                        rs.getTimestamp("updated").toLocalDateTime(),
                        rs.getString("game_file")
                );
                return Optional.of(chessGame);
            }
            return Optional.empty();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM CHESS_GAME WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

//    public void update(ChessGame chessGame) throws SQLException {
//        String sql = "UPDATE CHESS_GAME SET white_player = ?, black_player = ?, result = ?, updated = ?, game_file = ? WHERE id = ?";
//        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//            pstmt.setString(1, chessGame.whitePlayer());
//            pstmt.setString(2, chessGame.blackPlayer());
//            pstmt.setString(3, chessGame.result());
//            pstmt.setTimestamp(2, Timestamp.valueOf(chessGame.updated()));
//            pstmt.setString(4, chessGame.gameFile());
//            pstmt.setLong(3, chessGame.id());
//            pstmt.executeUpdate();
//        }
//    }
}