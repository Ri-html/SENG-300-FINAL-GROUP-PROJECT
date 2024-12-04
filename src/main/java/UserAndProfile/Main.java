package UserAndProfile;

public class Main {
    public static void main(String[] args) {
        // Initialize the user database (automatically loads users from the file)
        UserDatabase userDatabase = UserDatabase.getInstance();

        // Test adding a user
        User newUser = new User("4", "NewUser", "newuser@example.com");
        newUser.setPassword("newpassword");
        userDatabase.addUser(newUser);

        // Display all users
        for (User user : userDatabase.getAllUsers()) {
            System.out.println("User: " + user.getUsername() + ", Email: " + user.getEmail());
        }
    }
}
