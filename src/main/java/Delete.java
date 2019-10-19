import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {

    private int id;

    private DatabaseProvider databaseProvider = new DatabaseProvider();
    public Delete( int id) {
        this.id = id;

    }

    public void delete() {
        String request = "delete from  characteristic where id = ?";
        try {
            PreparedStatement ps = databaseProvider.getConnection().prepareStatement(request);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            System.out.println("Request completed");
        }catch (SQLException e) {
            System.out.println("Incorrect request");
        }
    }
}
