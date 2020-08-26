import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getconnection(){

        String connectionUrl = "jdbc:mysql://lunchbox.cwhnzfzzh2am.us-east-1.rds.amazonaws.com:3306/lunchbox?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
        String dbUser = "admin";
        String dbPwd = "12345678";
        Connection conn = null;

        try {
            conn = (Connection) DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            System.out.println("conn Available");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("fetch otion error"+e.getLocalizedMessage());
        }

        return conn;
    }
}
