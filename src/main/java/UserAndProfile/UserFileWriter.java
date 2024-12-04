package UserAndProfile;

import java.io.*;

public class UserFileWriter {

    public static void writeUsersToFile(UserDatabase userDatabase, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (User user : userDatabase.getAllUsers()) {
                // Format: username,email,password,profileModule1,profileModule2,...
                StringBuilder userData = new StringBuilder();
                userData.append(user.getUsername()).append(",");
                userData.append(user.getEmail()).append(",");
                userData.append(user.getPassword());

                // Get the user's PlayerProfile
                PlayerProfile profile = user.getPlayerProfile();
                if (profile != null) {
                    // Append all selected modules to the user data
                    for (String module : profile.getSelectedModules()) {
                        userData.append(",").append(module);
                    }
                }

                // Write user data to the file
                writer.write(userData.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing users to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
