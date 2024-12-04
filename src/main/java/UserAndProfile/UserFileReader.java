package UserAndProfile;

import java.io.*;
import java.util.*;

public class UserFileReader {

    public static UserDatabase readUsersFromFile(String fileName) {
        UserDatabase userDatabase = new UserDatabase();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");
                if (data.length >= 3) {
                    String username = data[0];
                    String email = data[1];
                    String password = data[2];


                    User user = new User(username, email, password);

                    List<String> modules = Arrays.asList(Arrays.copyOfRange(data, 3, data.length));
                    PlayerProfile profile = new PlayerProfile();
                    user.setPlayerProfile(profile);

                    userDatabase.addUser(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userDatabase;
    }
}

