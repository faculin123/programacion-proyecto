package clases;

public class User {
    private String username;    
    private String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // metodo para validar la contraseña
    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // metodo para cambiar la contraseña
    public boolean changePassword(String oldPassword, String newPassword) {
        if (validatePassword(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
