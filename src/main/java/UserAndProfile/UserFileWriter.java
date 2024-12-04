package UserAndProfile;

import java.io.*;

public class UserFileWriter {

    // Save all users to the file
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


    // Append a single user to the file
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
