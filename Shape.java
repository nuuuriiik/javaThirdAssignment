import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Shape {
    private String enteredId;
    private String enteredLogin;
    private String enteredPassword;
    //getters and setters
    public String getEnteredId() {
        return enteredId;
    }
    public void setEnteredId(String enteredId) {
        this.enteredId = enteredId;
    }
    public String getEnteredLogin() {
        return enteredLogin;
    }
    public void setEnteredLogin(String enteredLogin) {
        this.enteredLogin = enteredLogin;
    }
    public String getEnteredPassword() {
        return enteredPassword;
    }
    public void setEnteredPassword(String getEnteredPassword) {
        this.enteredPassword = getEnteredPassword;
    }
    String jdbcUrl = "jdbc:postgresql://localhost:5432/aitu";
    String userName = "postgres";
    String MasterPassword = "1111";
    protected boolean doesUserExist(String individualNumber, String login, String password){
        return true;
    }

}
