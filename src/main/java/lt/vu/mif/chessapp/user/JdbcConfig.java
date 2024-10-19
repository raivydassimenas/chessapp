package lt.vu.mif.chessapp.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories(basePackages = "lt.vu.mif.chessapp.user")
public class JdbcConfig {
}