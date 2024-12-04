package UserAndProfile;

import java.io.*;
import java.util.*;

public class UserFileReader {

    // Modify this method to avoid calling addUser (which triggers the constructor again)
    public static void readUsersFromFileWithoutAdd(String fileName, UserDatabase userDatabase) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String username = data[0].trim();
                    String email = data[1].trim();
                    String password = data[2].trim();

                    User user = new User(username, email, password);
                    userDatabase.addUserDirectly(user); // Directly add to the list without triggering recursion
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users from file: " + e.getMessage());
        }
    }
}
