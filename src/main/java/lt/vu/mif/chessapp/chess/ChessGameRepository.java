package lt.vu.mif.chessapp.chess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface ChessGameRepository extends ListCrudRepository<ChessGame, Long> {}