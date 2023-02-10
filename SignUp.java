import java.sql.*;

public class SignUp extends Shape{
    //Check length of IIN and is every char number
    public boolean isValidID(String enteredId){
        boolean isDigit = true;
        if (enteredId.length() == 12){
            for(int i = 0; i < 12; i++){
                if(!Character.isDigit(enteredId.charAt(i))){
                    isDigit = false;
                    break;
                }
            }
        }else{
            return false;
        }
        return isDigit;
    }
    public boolean isValidPassword(String enteredPassword){
        boolean containsUpper = false;
        boolean containsSymbol = false;
        if (enteredPassword.length() > 7){
            for(int i = 0; i < enteredPassword.length(); i++){
                if (Character.isUpperCase(enteredPassword.charAt(i))){
                    containsUpper = true;
                }
                if(Character.isLetterOrDigit(enteredPassword.charAt(i))){
                    containsSymbol = true;
                }
            }
        }else{
            return false;
        }
        return containsSymbol && containsUpper;
    }
    @Override
    protected boolean doesUserExist(String individualNumber, String login, String password) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, MasterPassword);
            Statement statement = con.createStatement();
            ResultSet checkLogin = statement.executeQuery("SELECT * FROM users WHERE login = '" + login + "';");
            if(checkLogin.next()){
                return false;
            }else{
                con.close();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in connecting to postgres server");
            throw new RuntimeException(e);
        }
    }
    public boolean createUser(){
        if(doesUserExist(getEnteredId(), getEnteredLogin(), getEnteredPassword()) &&
                isValidID(getEnteredId()) && isValidPassword(getEnteredPassword())){
            try {
                Connection con = DriverManager.getConnection(jdbcUrl, userName, MasterPassword);
                String sql = "INSERT INTO users (iin, login, pass)"
                        + "VALUES ('" + getEnteredId() + "', '" + getEnteredLogin() +
                        "', '" + getEnteredPassword() + "');";
                Statement statement = con.createStatement();
                int rows = statement.executeUpdate(sql);
                if(rows > 0){
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Error in connecting to postgres server");
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
