package lt.vu.mif.chessapp.chess;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<List<ChessGame>> getChessGames() throws SQLException {
        List<ChessGame> chessGames = (List<ChessGame>) chessGameRepository.findAll();
        return new ResponseEntity<>(chessGames, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ChessGame> getChessGameById(@PathVariable Long id) throws SQLException {
        Optional<ChessGame> chessGame = chessGameRepository.findById(id);
        return chessGame.map(game -> new ResponseEntity<>(game, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

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
    public String updateChessGame(@PathVariable Long id, @RequestBody ChessGame chessGame) throws SQLException {
        Optional<ChessGame> chessGameOptional = chessGameRepository.findById(id);

        if (chessGameOptional.isPresent()) {
            chessGameRepository.update(chessGameOptional.get());
            return "Chess game updated";
        } else {
            return "Chess game not found";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteChessGame(@PathVariable Long id) throws SQLException {
        Optional<ChessGame> chessGameOptional = chessGameRepository.findById(id);
        if (chessGameOptional.isPresent()) {
            chessGameRepository.delete(id);
            return "Chess game deleted";
        } else {
            return "Chess game not found";
        }
    }
}
