import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    private static FileProvider fileProvider;
    private static DatabaseProvider databaseProvider = new DatabaseProvider();
    private static Menu menu = new Menu();

    public static void main(String[] args)  {

        createFileProvider();
        connectToDatabase();
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                menu.mainMenu(scanner);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Server Error");
            System.exit(1);
        }
    }

    private static void createFileProvider() {
        try {
            fileProvider = new FileProvider(readFileName());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing configuration file.\n Error message: " + e.getMessage() );
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error reading file.\n Error message: " + e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong number of arguments.\n Error message: " + e.getMessage());
            System.exit(1);
        } catch (NoSuchElementException e) {
            System.out.println("Do not write configuration file");
        }
    }


    private static String readFileName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter configuration file name:");
        return scanner.nextLine();
    }

    private static void connectToDatabase() {
        try {
            databaseProvider.connect(fileProvider.getHost(), fileProvider.getPort(),
                    fileProvider.getDatabase(), fileProvider.getLogin(), fileProvider.getPassword());
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver.\n" + "Error message: " + e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: One of the parameters is not specified.\n" + "Error message: " + e.getMessage());
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("Connection error.\n" + "Error message: " + e.getMessage());
            System.exit(1);
        } catch (NullPointerException e) {
            System.out.println("Do not connect");
            System.exit(1);
        }
        System.out.println("-- connection success");
    }
}
