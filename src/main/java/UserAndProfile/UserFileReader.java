package UserAndProfile;

import java.io.*;

public class UserFileReader {

    public static void readUsersFromFile(String fileName, UserDatabase userDatabase) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String username = data[0];
                    String email = data[1];
                    String password = data[2];

                    User user = new User(username, email, password);
                    userDatabase.addUser(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users from file: " + e.getMessage());
        }
    }
}
