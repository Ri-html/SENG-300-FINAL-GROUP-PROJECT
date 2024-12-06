package UserAndProfile;

import java.io.*;
import java.util.*;

/**
 * Utility class for reading user data from a file.
 * <p>
 * This class provides methods for reading user information from a file and adding it to the
 * {@link UserDatabase}. The data is expected to be in a comma-separated format: username,email,password.
 * </p>
 */
public class UserFileReader {

    /**
     * Reads user data from the specified file and adds the users to the provided {@link UserDatabase}.
     * <p>
     * This method processes the file line by line, splits each line into components (username, email, password),
     * and creates a new {@link User} object for each line. The users are added directly to the database without
     * triggering any recursive calls to the {@link UserDatabase#addUser} method to avoid unnecessary overhead.
     * </p>
     *
     * @param fileName     the name of the file containing user data in comma-separated format
     * @param userDatabase the {@link UserDatabase} where the users will be added
     */
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
