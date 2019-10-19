import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileProvider {

    private String host;
    private String port;
    private String database;
    private String login;
    private String password;

    public FileProvider(String configFileName) throws IOException {
        readConfigFile(configFileName);
    }


    private void readConfigFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            parseLine(line);
        }

        bufferedReader.close();
        fileReader.close();
    }

    private void parseLine(String line) throws IllegalArgumentException{
        String[] lineParts = line.split(":");

        if (lineParts.length != 2) {
            throw new IllegalArgumentException("Wrong line: " + "\"" + line + "\"");
        }

        lineParts[0] = lineParts[0].trim().toLowerCase();
        lineParts[1] = lineParts[1].trim().toLowerCase();

        storeParameter(lineParts);
    }

    private void storeParameter(String[] lineParts) throws NumberFormatException {

        host = lineParts[0].equals("host") ? lineParts[1] : host;
        port = lineParts[0].equals("port") ? lineParts[1] : port;
        database = lineParts[0].equals("database") ? lineParts[1] : database;
        login = lineParts[0].equals("login") ? lineParts[1] : login;
        password = lineParts[0].equals("password") ? lineParts[1] : password;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
