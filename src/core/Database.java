package core;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private Connection connection;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javaschool",
                    "root",
                    ""
            );
        } catch (Exception exception) {
            System.out.println("[Error] " + exception.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
