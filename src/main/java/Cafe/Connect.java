package Cafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
<<<<<<< HEAD
    private static final String URL = "";
    private static final String USER = "";
    private static final String PASS = "";
=======
    private static final String URL = "DB_URL";
    private static final String USER = "DB_USERNAME";
    private static final String PASS = "DB_PASSWORD";
>>>>>>> 5802e278c7dd8859a3975d4d65621f37f6aa7f0b

    public  Connection createConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connection Successful");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        return conn;
    }
}
