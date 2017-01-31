package name.aknights.maint;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbMaintainer {

    public static void main(String[] args) throws URISyntaxException, SQLException {
        Statement stmt = null;
        Connection conn = getConnection();
        try {
            stmt = conn.createStatement();

            String sql = "DROP TABLE IF EXISTS EMPLOYEE; " +
                    "CREATE TABLE EMPLOYEE " +
                    "(id SERIAL not NULL, " +
                    " first_name VARCHAR(255), " +
                    " last_name VARCHAR(255), " +
                    " description VARCHAR(255), " +
                    " version INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }


    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath() + "?user=" + username + "&password=" + password + "&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        System.out.println("dbUrl = " + dbUrl);

        //jdbc:postgresql://host/database?user=user&password=password&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory

        return DriverManager.getConnection(dbUrl);
    }
}
