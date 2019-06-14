package dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;


public class ConnectionManager {

    private static volatile boolean isDriverLoaded = false;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        ResourceBundle connection = ResourceBundle.getBundle("connection", Locale.getDefault());
        if (connection == null) {
            throw new ConnectionManagerException("Бандл для db не был инициализирован");
        } else {
            URL = connection.getString("url");
            USER = connection.getString("user");
            PASSWORD = connection.getString("password");
            try {
                Class.forName(connection.getString("driver"));
                isDriverLoaded = true;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws ConnectionManagerException {
        if (!isDriverLoaded) {
            throw new ConnectionManagerException("Драйвер не был загружен");
        }

        try {
            final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new ConnectionManagerException("Ошибка получения соединения " +  e.getMessage());
        }
    }
}
