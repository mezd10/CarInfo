import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    private DatabaseProvider databaseProvider = new DatabaseProvider();

    public void updateColor(int id, String color)  {
        try {
            String request = "update characteristic set color = ? where id = ?";
            PreparedStatement ps = databaseProvider.getConnection().prepareStatement(request);
            ps.setString(1,color);
            ps.setInt(2, id);
            ps.execute();
            System.out.println("Field update");
        }
        catch (SQLException e) {
            System.out.println("Incorrect request ");
        }
    }

    public void updatePrice(int id, int price)  {
        try {
            String request = "update characteristic set price = ? where id = ?";
            PreparedStatement ps = databaseProvider.getConnection().prepareStatement(request);
            ps.setInt(1,price);
            ps.setInt(2, id);
            ps.execute();
            System.out.println("Field update");
        }
        catch (SQLException e) {
            System.out.println("Incorrect request ");
        }
    }
}
