package lotto_challenge.core.repository.jdbc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static lotto_challenge.core.repository.jdbc.database.ConnectionConst.PASSWORD;
import static lotto_challenge.core.repository.jdbc.database.ConnectionConst.URL;
import static lotto_challenge.core.repository.jdbc.database.ConnectionConst.USERNAME;

public class DBConnectionUtil {

    public static Connection getConnection() {
        try {
            final Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
