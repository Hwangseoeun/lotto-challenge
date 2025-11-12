package lotto_challenge.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static lotto_challenge.core.database.ConnectionConst.PASSWORD;
import static lotto_challenge.core.database.ConnectionConst.URL;
import static lotto_challenge.core.database.ConnectionConst.USERNAME;

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
