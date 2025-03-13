package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private static Connection con;

    private DBConn() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConn() {
        if (con == null) { 
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Use the correct driver
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbb6_todo", "root", "");
                System.out.println("✅ Database connected successfully!");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ JDBC Driver not found!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("❌ Database connection failed!");
                e.printStackTrace();
            }
        }
        return con;
    }
}
