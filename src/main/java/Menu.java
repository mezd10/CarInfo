import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.in;


public class Menu {

    private DatabaseProvider databaseProvider = new DatabaseProvider();

    void mainMenu(Scanner scanner) {
        System.out.println("_______________________");
        System.out.println();
        System.out.println("Enter code:" +
                " \n 1. Search. " +
                " \n 2. Insert. " +
                " \n 3. Update. " +
                " \n 4. Delete. "+
                " \n 5. Exit.\n  ");

        int result = checkValidation(scanner);
        try {
            Info info = new Info();
            switch (result) {

                case 1:
                    while (info.getStatus().equals("ok") || info.getStatus().equals("fail")) {
                        info.infoSearch(scanner);
                    }
                    break;
                case 2:
                    while (info.getStatus().equals("ok") || info.getStatus().equals("fail")) {
                        info.infoInsert(scanner);
                    }
                    break;
                case 3:
                    while (info.getStatus().equals("ok") || info.getStatus().equals("fail")) {
                        info.infoUpdate(scanner);
                    }
                    break;
                case 4:
                    while (info.getStatus().equals("ok") || info.getStatus().equals("fail")) {
                        info.infoDelete(scanner);
                    }
                    break;
                case 5:
                    databaseProvider.disconnect();
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid number");

            }
        } catch (NoSuchElementException e) {
            System.out.println("\n Server Error");
        } catch (SQLException e) {
            System.out.println("\n Incorrect request");
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


}
