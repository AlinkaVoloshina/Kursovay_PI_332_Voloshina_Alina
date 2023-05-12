package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProperty {
    private String jdbcURL = "jdbc:postgresql://localhost:5433/StudentGroup";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "12345678";
    
    public ConnectionProperty() {

    }

    public  Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        }
        return connection;
    }
}


