package lt.vu.mif.chessapp.chess;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chessgame")
public class ChessController {

  private final ChessService chessService;

  public ChessController(ChessService chessService) {
    this.chessService = chessService;
  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<ChessGame>> getChessGames() throws SQLException {
    List<ChessGame> chessGames = chessService.getGameData();
    return new ResponseEntity<>(chessGames, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ChessGame> getChessGameById(@PathVariable Long id) throws SQLException {
    Optional<ChessGame> chessGame = chessService.getGameData(id);
    return chessGame.map(game -> new ResponseEntity<>(game, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> createChessGame(@RequestBody ChessGame chessGame) {
    try {
      chessService.storeGameData(chessGame.whitePlayer(), chessGame.blackPlayer(), chessGame.result(),
          chessGame.gameFileId());
      return new ResponseEntity<>("Chess game created successfully", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> updateChessGame(@PathVariable Long id, @RequestBody ChessGame chessGame)
      throws SQLException {
    Optional<ChessGame> chessGameOptional = chessService.getGameData(id);

    if (chessGameOptional.isPresent()) {
      chessService.updateGameData(id, chessGame.whitePlayer(), chessGame.blackPlayer(), chessGame.result(),
          chessGame.gameFileId());
      return new ResponseEntity<>("Chess game updated", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Chess game not found", HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<String> deleteChessGame(@PathVariable Long id) throws SQLException {
    Optional<ChessGame> chessGameOptional = chessService.getGameData(id);

    if (chessGameOptional.isPresent()) {
      chessService.deleteGameData(id);
      return new ResponseEntity<>("Chess game deleted", HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>("Chess game not found", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/file/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<ChessFile> getChessFile(@PathVariable Long id) throws SQLException {
    Optional<ChessFile> chessFile = chessService.getGameFile(id);
    return chessFile.map(file -> new ResponseEntity<>(file, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/file")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> createChessFile(@RequestParam("file") MultipartFile chessFile) {
    try {
      chessService.storeGameFile(chessFile);
      return new ResponseEntity<>("Chess file created successfully", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/file/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> updateChessFile(@PathVariable Long id, @RequestParam("file") MultipartFile chessFile)
          throws SQLException, IOException {
    Optional<ChessFile> chessFileOptional = chessService.getGameFile(id);
    if (chessFileOptional.isPresent()) {
      chessService.updateGameFile(id, chessFile);
      return new ResponseEntity<>("Chess file updated", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Chess file not found", HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/file/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<String> deleteChessFile(@PathVariable Long id) throws SQLException {
    Optional<ChessFile> chessFileOptional = chessService.getGameFile(id);
    if (chessFileOptional.isPresent()) {
      chessService.deleteGameFile(id);
      return new ResponseEntity<>("Chess file deleted", HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>("Chess file not found", HttpStatus.NOT_FOUND);
    }
  }
}
