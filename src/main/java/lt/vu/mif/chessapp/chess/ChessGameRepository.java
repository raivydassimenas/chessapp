package lt.vu.mif.chessapp.chess;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ChessGameRepository extends CrudRepository<ChessGame, UUID> {}