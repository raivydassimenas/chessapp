package lt.vu.mif.chessapp.chess;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("CHESS_FILE")
public record ChessFile(
    @Id Long id,
    String name,
    byte[] file,
    LocalDateTime created,
    LocalDateTime updated) {
}
