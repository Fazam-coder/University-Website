package university.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnectionUtil {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                String dbHost = System.getenv("DB_HOST");
                if (dbHost == null || dbHost.isEmpty()) {
                    dbHost = "localhost";
                }
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://" + dbHost + ":5432/university",
                        "postgres",
                        System.getenv("DB_PASSWORD")
                );
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
