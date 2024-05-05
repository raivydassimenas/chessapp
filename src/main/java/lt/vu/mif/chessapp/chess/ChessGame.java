package lt.vu.mif.chessapp.chess;

import com.fasterxml.jackson.annotation.JsonTypeId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Table("games")
public record ChessGame(
        @Id UUID id,
        String whitePlayer,
        String blackPlayer,
        Result result,
        @CreatedDate Instant createdDate,
        @LastModifiedDate Instant lastModified,
        String gameFile
) {}