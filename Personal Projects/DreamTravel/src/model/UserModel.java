package model;

public class UserModel {
    private String username;
    private String password;
    private String userType;
    private static UserModel currentUser;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }


    public static UserModel getCurrentUser() {
        return currentUser;
    }

    public static void setUser(String username, String password, String userType) {
        currentUser = new UserModel();
        currentUser.username = username;
        currentUser.password = password;
        currentUser.userType = userType;
    }

}
