package lt.vu.mif.chessapp.chess;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("chess_game")
public record ChessGame(
        @Id Long id,
        String whitePlayer,
        String blackPlayer,
        String result,
//        @CreatedDate Instant createdDate,
//        @LastModifiedDate Instant lastModified,
        String gameFile
) {}