package co.com.k4soft.loginmvp.dto;

public class LoginDTO {

    private String user;
    private String password;


    public LoginDTO(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public LoginDTO() {
        super();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
