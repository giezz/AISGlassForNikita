package glass.aisglass.db;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final String URL = "jdbc:postgresql://localhost:5432/delivery";
    private static final String USER = "postgres";
    private static final String PASS = "admin";
    private static Connection connection = null;

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(URL, USER, PASS);
        }
        return connection;
    }
}
