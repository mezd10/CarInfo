import java.sql.*;
// выявлкние есть ли такая машина!!!
public class InsertData {

    private DatabaseProvider databaseProvider = new DatabaseProvider();
    private String marc, model, body, color;
    private Float volume;
    private int price;
    private int date;

    public InsertData(String marc, String model, String body, String date, String color, Float volume, int price ) {
        this.marc = marc;
        this.body = body;
        this.color = color;
        this.date = Integer.parseInt(date);
        this.price = price;
        this.model = model;
        this.volume = volume;
    }

    public void insert() throws SQLException {

        PreparedStatement insertMod = null;
        PreparedStatement insertMar = null;
        PreparedStatement insertBod = null;
        PreparedStatement insertChar = null;

        String insertModel = "insert into model_car(model) values (?)";
        String insertMarc = "insert into marc(marc) values (?)";
        String insertBody = "insert into body(name_body) values (?)";
        String insertCharac = "insert into characteristic(id_marc,id_model,id_body,year_issue,color,volume,price)"+
        " values (?, ?, ?, ?, ?,?, ?)";

        try {

            int idMarc = getTableId("marc", "marc", marc) == -1 ?
                    getMaxId("marc") + 1 : getTableId("marc", "marc", marc);
            int idModel = getTableId("model_car", "model_car.model", model) == -1 ?
                    getMaxId("model_car") + 1 : getTableId("model_car", "model_car.model", model);
            int idBody = getTableId("body", "name_body", body) == -1 ?
                    getMaxId("body") + 1 : getTableId("body", "name_body", body);

            databaseProvider.getConnection().setAutoCommit(false);


            if (getTableId("marc", "marc", marc) == -1) {
                insertMar = databaseProvider.getConnection().prepareStatement(insertMarc);
                insertMar.setString(1, marc);
                insertMar.executeUpdate();

            }

            if (getTableId("model_car", "model", model) == -1) {
                insertMod = databaseProvider.getConnection().prepareStatement(insertModel);
                insertMod.setString(1, model);
                insertMod.executeUpdate();
            }

            if (getTableId("body", "name_body", body) == -1) {
                insertBod = databaseProvider.getConnection().prepareStatement(insertBody);
                insertBod.setString(1, body);
                insertBod.executeUpdate();
            }


            insertChar = databaseProvider.getConnection().prepareStatement(insertCharac);
            insertChar.setInt(1, idMarc);
            insertChar.setInt(2, idModel);
            insertChar.setInt(3, idBody);
            insertChar.setInt(4, date);
            insertChar.setString(5, color);
            insertChar.setFloat(6, volume);
            insertChar.setInt(7, price);
            insertChar.executeUpdate();

            System.out.println("Record added successfully");

        } catch (SQLException e) {

            if (databaseProvider.getConnection() != null) {

                System.err.print("Transaction is being rolled back\n");
                databaseProvider.getConnection().rollback();
            }
        }

        finally {
            if (insertChar != null) {
                insertChar.close();
            }

            if (insertMar != null) {
                insertMar.close();
            }

            if (insertBod != null) {
                insertBod.close();
            }

            if (insertMod != null) {
                insertMod.close();
            }
            databaseProvider.getConnection().setAutoCommit(true);
        }
    }

    //для выявления есть ли такие записи в таблице(Marc, Model_car, Body)
    private int getTableId(String table, String fieldName, String field) throws SQLException {
        int result = -1;
        String request = "select id from " + table + " where " + fieldName + " = ?";
        PreparedStatement ps = databaseProvider.getConnection().prepareStatement(
                request);

        ps.setString(1,field);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next())
            result = resultSet.getInt(1);

        ps.close();
        return result;
    }

    private int getMaxId(String table) throws SQLException {
        int result = 0;
        String request = "select id from " + table;
        PreparedStatement ps = databaseProvider.getConnection().prepareStatement(
                request
        );
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next())
             result = resultSet.getInt(1);
        ps.close();
        return result;
    }
}
