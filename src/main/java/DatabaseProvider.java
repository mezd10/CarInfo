import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseProvider {

    private static Connection connection;

    public void connect(String host, String port, String database, String login, String password)
            throws ClassNotFoundException, SQLException, IllegalArgumentException {

        if (host == null || port == null || database == null || login == null || password == null) {
            throw new IllegalArgumentException();
        }

        Class.forName("org.postgresql.Driver");

        connection = DriverManager
                .getConnection("jdbc:postgresql://" + host + ":" + port + "/" + database, login, password);
    }


    public void disconnect() throws SQLException {
        connection.close();
    }

    public Connection getConnection(){
        return connection;
    }


}
