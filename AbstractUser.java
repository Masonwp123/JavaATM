import java.util.*;

public abstract class AbstractUser implements IHasMenu {

    private String userName;
    private String PIN;

    public AbstractUser(String userName, String PIN) {
        this.userName = userName;
        this.PIN = PIN;
    }

    public AbstractUser() {
        this("NULL", "0000");
    }

    public boolean login(String userName, String PIN) {
        if (this.userName == userName && this.PIN == PIN) {
            System.out.println("Login Successful.");
            return true;
        }
        System.out.println("Login Failed, please try again.");
        return false;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public abstract String getReport();
}