import java.util.*;
import java.io.*;

public abstract class AbstractUser implements IHasMenu, Serializable {

    private String userName;
    private String PIN;

    public AbstractUser(String userName, String PIN) {
        this.userName = userName;
        this.PIN = PIN;
    }

    public AbstractUser() {
        this("NULL", "0000");
    }

    // Usernames are not case sensitive for ease of use
    public boolean canLogin(String userName, String PIN) {
        return this.userName.toLowerCase().equals(userName.toLowerCase()) && this.PIN.equals(PIN);
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPIN() {
        return this.PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public abstract String getReport();
}