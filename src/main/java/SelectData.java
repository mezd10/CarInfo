import java.sql.*;

public class SelectData {
    private DatabaseProvider databaseProvider = new DatabaseProvider();


    public void showAll() {
        try {


            PreparedStatement ps = databaseProvider.getConnection().prepareStatement(
                    "select characteristic.id,marc, m2.model, name_body, year_issue, color, volume, price\n" +
                            "from characteristic\n" +
                            "join body b on characteristic.id_body = b.id\n" +
                            "join marc m on characteristic.id_marc = m.id\n" +
                            "join model_car m2 on characteristic.id_model = m2.id;");


            ResultSet chars = ps.executeQuery();

            returnResult(chars);

            chars.close();


            ps.close();
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Incorrect request");
        }
    }

    public void show(String marc, String model) {
        try {

            PreparedStatement ps = databaseProvider.getConnection().prepareStatement(
                    "select characteristic.id, m.marc, m2.model, name_body, year_issue, color, volume, price\n" +
                            "from characteristic\n" +
                            "join body b on characteristic.id_body = b.id\n" +
                            "join marc m on characteristic.id_marc = m.id\n" +
                            "join model_car m2 on characteristic.id_model = m2.id\n" +
                            "where m.marc = ? \n" +
                            "and m2.model = ?;");
            ps.setString(1, marc);
            ps.setString(2, model);

            ResultSet chars = ps.executeQuery();
            returnResult(chars);
            chars.close();
            ps.close();

            System.out.println();
        } catch (SQLException e) {
            System.out.println("Incorrect request");
        }
    }

    private void returnResult(ResultSet chars) throws SQLException{
        System.out.println("Car:");
        if (!chars.isBeforeFirst()) {
            System.out.println("Empty");

        }
        else {
            ResultSetMetaData rmChar = chars.getMetaData();
            System.out.printf("%-10s %-15s %-15s %-15s %-15s %-15s %-15s %-10s", rmChar.getColumnName(1),
                    rmChar.getColumnName(2), rmChar.getColumnName(3), rmChar.getColumnName(4), rmChar.getColumnName(5),
                    rmChar.getColumnName(6), rmChar.getColumnName(7), rmChar.getColumnName(8));
            System.out.println();

            while (chars.next()) {
                System.out.format("%-10s %-15s %-15s %-15s %-15s %-15s %-15s %-10s",chars.getInt(1),
                        chars.getString(2),
                        chars.getString(3), chars.getString(4), chars.getString(5),
                        chars.getString(6), chars.getFloat(7), chars.getInt(8));
                System.out.println();
            }
        }
    }
}
