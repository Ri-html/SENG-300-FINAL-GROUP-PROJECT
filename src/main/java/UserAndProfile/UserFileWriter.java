package UserAndProfile;

import java.io.*;

/**
 * Utility class for handling file operations related to user data.
 * <p>
 * This class provides methods to save all users to a file and append a single user to a file.
 * User data is stored in a comma-separated format (username, email, password) for easy parsing.
 * </p>
 */
public class UserFileWriter {

    /**
     * Writes all users from the provided {@link UserDatabase} to the specified file.
     * <p>
     * This method overwrites the contents of the file with all users in the database,
     * ensuring the file contains the latest user data. Each user is stored on a new line
     * in the format: username,email,password.
     * </p>
     *
     * @param userDatabase the {@link UserDatabase} containing all users to be written to the file
     * @param fileName     the name of the file to which user data will be written
     */
    public static void writeUsersToFile(UserDatabase userDatabase, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (User user : userDatabase.getAllUsers()) {
                // Write username, email, and password in the correct order
                String userData = user.getUsername() + "," + user.getEmail() + "," + user.getPassword();
                writer.write(userData);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends a single user's data to the specified file.
     * <p>
     * This method adds the user's information to the end of the file without overwriting
     * existing data. The user's data is stored in the format: username,email,password.
     * </p>
     *
     * @param user     the {@link User} object containing the user's information to be appended
     * @param fileName the name of the file to which the user's data will be appended
     */
    public static void appendUserToFile(User user, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            // Ensure proper format: username,email,password
            String userData = user.getUsername().trim() + "," + user.getEmail().trim() + "," + user.getPassword().trim();
            writer.write(userData);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error appending user to file: " + e.getMessage());
        }
    }
}
