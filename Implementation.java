import java.util.Objects;
import java.util.Scanner;

public class Implementation {
    public void Start() throws InterruptedException {
        Scanner s = new Scanner(System.in);
        SignUp sUp = new SignUp();
        SignIn sIn = new SignIn();
        while (true){
            System.out.println("1. SignUp");
            System.out.println("2. SignIn");
            System.out.println("3. break");
            System.out.print("Choose number: ");
            String number = s.nextLine();
            if (Objects.equals(number, "1")){
                System.out.print("Individual number: ");
                sUp.setEnteredId(s.nextLine());
                System.out.print("Login: ");
                sUp.setEnteredLogin(s.nextLine());
                System.out.print("Password: ");
                sUp.setEnteredPassword(s.nextLine());
                if(sUp.createUser()){
                    System.out.println("    You have successfully registered");
                }else{
                    System.out.println("    The individual number must contain 12 digits");
                    System.out.println("    The password must be longer than 7 letters, " +
                            "contain a capital letter, and a digit");
                    System.out.println("    Try again");
                }
            }else if (Objects.equals(number, "2")){
                int[] time = {5000,30000,60000};
                String[] seconds = {"5 sec", "30 sec", "60 sec"};
                for (int i = 0; i < 3; i++){
                    System.out.print("Login or ID: ");
                    sIn.setLoginOrId(s.nextLine());
                    System.out.print("Password: ");
                    sIn.setEnteredPassword(s.nextLine());
                    if (sUp.isValidPassword(sIn.getEnteredPassword())){
                        if (sIn.Authorization()){
                            System.out.println("You have successfully logged in");
                            break;
                        }else{
                            System.out.println("    Incorrect login or password, try again in " + seconds[i]);
                            Thread.sleep(time[i]);
                        }
                    }else{
                        System.out.println("    The password must be longer than 7 letters, " +
                                "contain a capital letter, and a digit");
                        System.out.println("    Try again");
                        i -= 1;
                    }
                }
            }else if (Objects.equals(number, "3")){
                break;
            }
        }
    }
}

