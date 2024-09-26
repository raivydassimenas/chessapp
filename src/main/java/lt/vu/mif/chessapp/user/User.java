package lt.vu.mif.chessapp.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("user")
public record User(
    @Id Long id,
    String username,
    String password,
    Set<String> roles
) {}
