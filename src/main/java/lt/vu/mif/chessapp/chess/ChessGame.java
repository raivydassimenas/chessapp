package lt.vu.mif.chessapp.chess;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;


@Table("CHESS_GAME")
public record ChessGame(
        @Id Long id,
        String whitePlayer,
        String blackPlayer,
        String result,
        LocalDateTime created,
        LocalDateTime updated,
        String gameFile
) {}