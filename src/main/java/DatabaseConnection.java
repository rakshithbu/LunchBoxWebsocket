import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getconnection(){

        String connectionUrl = "jdbc:mysql://lunchbox.cwhnzfzzh2am.us-east-1.rds.amazonaws.com:3306/lunchbox";
        String dbUser = "admin";
        String dbPwd = "12345678";
        Connection conn = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            System.out.println("conn Available");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
