package lt.vu.mif.chessapp.chess;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chessgame")
public class ChessController {

  private final ChessGameRepository chessGameRepository;

  public ChessController(ChessGameRepository chessGameRepository) {
    this.chessGameRepository = chessGameRepository;
  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<ChessGame>> getChessGames() throws SQLException {
    List<ChessGame> chessGames = (List<ChessGame>) chessGameRepository.findAll();
    return new ResponseEntity<>(chessGames, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ChessGame> getChessGameById(@PathVariable Long id) throws SQLException {
    Optional<ChessGame> chessGame = chessGameRepository.findById(id);
    return chessGame.map(game -> new ResponseEntity<>(game, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> createChessGame(@RequestBody ChessGame chessGame) {
    try {
      chessGameRepository.save(chessGame);
      return new ResponseEntity<>("Chess game created successfully", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> updateChessGame(@PathVariable Long id, @RequestBody ChessGame chessGame)
      throws SQLException {
    Optional<ChessGame> chessGameOptional = chessGameRepository.findById(id);

    if (chessGameOptional.isPresent()) {
      chessGameRepository.save(chessGameOptional.get());
      return new ResponseEntity<>("Chess game updated", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Chess game not found", HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<String> deleteChessGame(@PathVariable Long id) throws SQLException {
    Optional<ChessGame> chessGameOptional = chessGameRepository.findById(id);
    if (chessGameOptional.isPresent()) {
      chessGameRepository.deleteById(id);
      return new ResponseEntity<>("Chess game deleted", HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>("Chess game not found", HttpStatus.NOT_FOUND);
    }
  }
}
