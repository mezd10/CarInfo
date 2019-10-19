import java.sql.SQLException;
import java.util.Scanner;

public class Info {

    private SelectData selectData = new SelectData();
    private String status = "ok";


    public void infoSearch(Scanner scanner) throws SQLException {
        System.out.println("Please, choice act: " +
                "\n 1.Show specific car." +
                "\n 2.Show all cars" +
                "\n 3.Exit to main menu.");

        int result = checkValidation(scanner);

        switch (result) {
            case 1:
                System.out.println("Enter marc car");
                String marc = scanner.next().toLowerCase();
                System.out.println("Enter model car");
                String model = scanner.next().toLowerCase();

                selectData.show(marc, model);
                break;
            case 2:
                selectData.showAll();
                break;
            case 3:
                status = "exit";
                break;
            default:
                System.out.println("Invalid number!");
        }
    }

    public void infoInsert(Scanner scanner) throws SQLException{
        System.out.println("Please, choice act: " +
                "\n 1. Show all cars." +
                "\n 2. Insert specific car." +
                "\n 3. Exit to main menu.");

        int result = checkValidation(scanner);

        switch (result) {
            case 1:
                selectData.showAll();
                break;

            case 2:
                try {
                    System.out.println("Enter: marc");
                    String marc = checkString(scanner.next());
                    System.out.println("Enter: model");
                    String model = checkString(scanner.next());
                    System.out.println("Enter: body");
                    String body = checkString(scanner.next());
                    System.out.println("Enter: date");
                    String date = checkString(scanner.next());
                    System.out.println("Enter: color");
                    String color = checkString(scanner.next());
                    System.out.println("Enter: volume");
                    Float volume = Float.parseFloat(checkString(scanner.next()));
                    System.out.println("Enter: price(rub)");
                    int price = Integer.parseInt(checkString(scanner.next()));
                    InsertData insertData = new InsertData(marc, model, body, date, color, volume, price);
                    insertData.insert();
                }
                catch (NumberFormatException e) {
                    System.out.println("Incorrect data");
                    status = "fail";
                }

                break;

            case 3:
                status = "exit";
                break;
            default:
                System.out.println("Invalid number!");
        }

    }

    public void infoUpdate(Scanner scanner) throws SQLException{
        System.out.println("Please, choice act: " +
                "\n 1. Show all cars." +
                "\n 2. Update color." +
                "\n 3. Update price." +
                "\n 4. Exit to main menu.");

        int result = checkValidation(scanner);
        Update update = new Update();
        switch (result) {
            case 1:
                selectData.showAll();
                break;

            case 2:
                try {
                    selectData.showAll();
                    System.out.println("Enter ID car:");
                    int id = Integer.parseInt(scanner.next());
                    System.out.println("Enter color");
                    String color = scanner.next().toLowerCase();

                    update.updateColor(id, color);
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect ID");
                    status = "fail";
                }
                break;

            case 3:
                try {
                    selectData.showAll();
                    System.out.println("Enter ID car:");
                    int id = Integer.parseInt(scanner.next());
                    System.out.println("Enter price");
                    int price = Integer.parseInt(scanner.next());
                    update.updatePrice(id, price);
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect data");
                    status = "fail";
                }
                break;
            case 4:
                status = "exit";
        }

    }



    private int checkValidation(Scanner scanner) {

        int result = -1;
        while (result < 1 ) {

            while (!scanner.hasNextInt()) {

                System.out.println("Please, you must enter an integer");

                scanner.next();

            }
            result = scanner.nextInt();
        }
        return result;
    }

    private String checkString(String string) {

        StringBuilder result = new StringBuilder();
        String[] s = string.split(" ");

        for (String value : s) {
            result.append(value);
        }
        return result.toString().toLowerCase();
    }

    public String getStatus(){
        return status;
    }


}
