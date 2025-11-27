package org.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBUtil {

    private static String url;
    private static String username;
    private static String password;

    static {
        try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("config/db.properties")) {
            if (input == null) {
                throw new RuntimeException("db.properties not found in classpath");
            }

            Properties prop = new Properties();
            prop.load(input);

            url = prop.getProperty("jdbc.url");
            username = prop.getProperty("jdbc.username");
            password = prop.getProperty("jdbc.password");

            Class.forName(prop.getProperty("jdbc.driverClassName"));

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
