package lt.vu.mif.chessapp.chess;

import org.springframework.data.repository.CrudRepository;
public interface ChessGameRepository extends CrudRepository<ChessGame, Long> {
}
