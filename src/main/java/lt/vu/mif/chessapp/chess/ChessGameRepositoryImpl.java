package lt.vu.mif.chessapp.chess;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ChessGameRepositoryImpl {
  private final JdbcTemplate jdbcTemplate;

  public ChessGameRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Transactional
  public void save(ChessGame chessGame) {
    String sql = "INSERT INTO CHESS_GAME (white_player, black_player, result, created, updated, game_file) VALUES (?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(connection -> {
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, chessGame.white_player());
      pstmt.setString(2, chessGame.black_player());
      pstmt.setString(3, chessGame.result());
      pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
      pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
      pstmt.setString(6, chessGame.game_file());
      return pstmt;
    });
  }

  @Transactional
  public List<ChessGame> findAll() {
    String sql = "SELECT * FROM CHESS_GAME";

    return jdbcTemplate.query(connection -> connection.prepareStatement(sql), new ChessGameRowMapper());
  }

  @Transactional
  public Optional<ChessGame> findById(Long id) {
    String sql = "SELECT id, white_player, black_player, result, created, updated, game_file FROM CHESS_GAME WHERE id = ?";

    return jdbcTemplate.query(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setLong(1, id);
      return ps;
    }, rs -> {
      if (rs.next()) {
        return Optional.of(new ChessGame(rs.getLong("id"), rs.getString("white_player"), rs.getString("black_player"),
            rs.getString("result"), rs.getTimestamp("created").toLocalDateTime(),
            rs.getTimestamp("updated").toLocalDateTime(), rs.getString("game_file")));
      } else {
        return Optional.empty();
      }
    });
  }

  @Transactional
  public void delete(Long id) {
    String sql = "DELETE FROM CHESS_GAME WHERE id = ?";
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setLong(1, id);
      return ps;
    });
  }

  public void update(ChessGame chessGame) {
    String sql = "UPDATE CHESS_GAME SET white_player = ?, black_player = ?, result = ?, updated = ?, game_file = ? WHERE id = ?";
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setString(1, chessGame.white_player());
      ps.setString(2, chessGame.black_player());
      ps.setString(3, chessGame.result());
      ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
      ps.setString(5, chessGame.game_file());
      ps.setLong(6, chessGame.id());
      return ps;
    });
  }

  private static class ChessGameRowMapper implements RowMapper<ChessGame> {
    @Override
    public ChessGame mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new ChessGame(rs.getLong("id"), rs.getString("white_player"), rs.getString("black_player"),
          rs.getString("result"), rs.getTimestamp("created").toLocalDateTime(),
          rs.getTimestamp("updated").toLocalDateTime(), rs.getString("game_file"));
    }
  }
}
