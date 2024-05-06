package lt.vu.mif.chessapp.chess;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.Instant;
import java.util.UUID;

@Table("games")
public record ChessGame(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) UUID id,
        String whitePlayer,
        String blackPlayer,
        String result,
        @CreatedDate Instant createdDate,
        @LastModifiedDate Instant lastModified,
        String gameFile
) {}