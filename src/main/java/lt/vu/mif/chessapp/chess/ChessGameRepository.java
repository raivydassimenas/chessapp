package lt.vu.mif.chessapp.chess;

import org.springframework.data.repository.ListCrudRepository;

public interface ChessGameRepository extends ListCrudRepository<ChessGame, Long> {
}
