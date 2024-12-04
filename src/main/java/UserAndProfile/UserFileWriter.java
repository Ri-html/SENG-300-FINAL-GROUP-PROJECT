package UserAndProfile;

import java.io.*;

public class UserFileWriter {

    public static void writeUsersToFile(UserDatabase userDatabase, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (User user : userDatabase.getAllUsers()) {
                // format: username,email,password,profileModule1,profileModule2,...
                StringBuilder userData = new StringBuilder();
                userData.append(user.getUsername()).append(",");
                userData.append(user.getEmail()).append(",");
                userData.append(user.getPassword());


                PlayerProfile profile = user.getPlayerProfile();
                if (profile != null) {
                    for (String module : profile.getSelectedModules()) {
                        userData.append(",").append(module);
                    }
                }

                writer.write(userData.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
