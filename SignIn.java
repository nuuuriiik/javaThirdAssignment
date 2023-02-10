import java.sql.*;

public class SignIn extends Shape{
    private String loginOrId;
    public void setLoginOrId(String loginOrId) {
        this.loginOrId = loginOrId;
    }
    public String getLoginOrId() {
        return loginOrId;
    }

    private boolean checkLoginOrId(){
        //if it's login it returns true, otherwise it's false
        if(loginOrId.length() == 12){
            for(int i = 0; i < 12; i++){
                if (!Character.isDigit(loginOrId.charAt(i))){
                    return true;
                }
            }
        }else{
            return true;
        }
        return false;
    }
    @Override
    protected boolean doesUserExist(String individualNumber, String login, String password){
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, userName, MasterPassword);
            Statement statement = con.createStatement();
            if(checkLoginOrId()){
                ResultSet checkExistence = statement.executeQuery("SELECT * FROM users WHERE login = '" +
                        login + "' AND pass = '" + password + "';");
                if(checkExistence.next()){
                    return true;
                }else{
                    con.close();
                    return false;
                }
            }else{
                ResultSet checkExistence = statement.executeQuery("SELECT * FROM users WHERE iin = '" +
                        individualNumber + "' AND pass = '" + password + "';");
                if(checkExistence.next()){
                    return true;
                }else{
                    con.close();
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in connecting to postgres server");
            throw new RuntimeException(e);
        }
    }
    public boolean Authorization(){
        return doesUserExist(loginOrId, loginOrId, getEnteredPassword());
    }
}
