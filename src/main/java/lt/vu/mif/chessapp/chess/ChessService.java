package lt.vu.mif.chessapp.chess;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChessService {

  ChessGameRepository chessGameRepository;

  ChessFileRepository chessFileRepository;

  public ChessService(ChessGameRepository chessGameRepository, ChessFileRepository chessFileRepository) {
    this.chessGameRepository = chessGameRepository;
    this.chessFileRepository = chessFileRepository;
  }

  public List<ChessGame> getGameData() {
    return (List<ChessGame>) chessGameRepository.findAll();
  }

  public Optional<ChessGame> getGameData(Long id) {
    return chessGameRepository.findById(id);
  }

  public ChessGame storeGameData(String whitePlayer, String blackPlayer, String result, Long gameFileId) {
    ChessGame chessGame = new ChessGame(null, whitePlayer, blackPlayer, result, LocalDateTime.now(),
        LocalDateTime.now(), gameFileId);
    return chessGameRepository.save(chessGame);
  }

  public ChessGame updateGameData(Long id, String whitePlayer, String blackPlayer, String result, Long gameFileId) {
    ChessGame chessGame = chessGameRepository.findById(id).get();
    chessGame = new ChessGame(id, whitePlayer, blackPlayer, result, chessGame.created(), LocalDateTime.now(),
        gameFileId);
    return chessGameRepository.save(chessGame);
  }

  public void deleteGameData(Long id) {
    chessGameRepository.deleteById(id);
  }

  public Optional<ChessFile> getGameFile(Long id) {
    return chessFileRepository.findById(id);
  }

  public ChessFile storeGameFile(MultipartFile file) throws IOException {
    String fileName = file.getOriginalFilename();
    byte[] fileBytes = file.getBytes();

    ChessFile chessFile = new ChessFile(null, fileName, fileBytes, LocalDateTime.now(), LocalDateTime.now());
    return chessFileRepository.save(chessFile);
  }

  public ChessFile updateGameFile(Long id, MultipartFile file) throws IOException {
    ChessFile chessFile = chessFileRepository.findById(id).get();
    String fileName = file.getOriginalFilename();
    byte[] fileBytes = file.getBytes();

    chessFile = new ChessFile(id, fileName, fileBytes, chessFile.created(), LocalDateTime.now());
    return chessFileRepository.save(chessFile);
  }

    public void deleteGameFile(Long id) {
        chessFileRepository.deleteById(id);
    }
}
