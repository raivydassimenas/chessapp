package lt.vu.mif.chessapp.chess;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/chessgame")
public class ChessController {

    private final ChessGameRepository chessGameRepository;

    public ChessController(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<ChessGame>> getChessGames() {
        List<ChessGame> chessGames = (List<ChessGame>) chessGameRepository.findAll();
        return new ResponseEntity<>(chessGames, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ChessGame> getChessGameById(@PathVariable UUID id) {
        Optional<ChessGame> chessGame = chessGameRepository.findById(id);
        return chessGame.map(game -> new ResponseEntity<>(game, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String createChessGame(@RequestBody ChessGame chessGame) {
        Optional<ChessGame> chessGameOptional = Optional.of(chessGameRepository.save(chessGame));
        return "Chess game created";
    }

    @PutMapping("/{id}")
    public String updateChessGame(@PathVariable UUID id, @RequestBody ChessGame chessGame) {
        Optional<ChessGame> chessGameOptional = chessGameRepository.findById(id);

        if (chessGameOptional.isPresent()) {
            chessGameRepository.save(chessGameOptional.get());
            return "Chess game updated";
        } else {
            return "Chess game not found";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteChessGame(@PathVariable UUID id) {
        Optional<ChessGame> chessGameOptional = chessGameRepository.findById(id);
        if (chessGameOptional.isPresent()) {
            chessGameRepository.delete(chessGameOptional.get());
            return "Chess game deleted";
        } else {
            return "Chess game not found";
        }
    }
}
