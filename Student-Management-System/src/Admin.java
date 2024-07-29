
package l1t2;
import java.util.Scanner;


public class Admin {
    void admin(){
                Scanner scanner=new Scanner(System.in);
                System.out.println("Admin portal");
                System.out.println("");
                UserManager usermanager=new UserManager();
                System.out.println("Press 1=Login 2=Create new Account 3=Forgot");
                System.out.print("Enter:");
                int choice = scanner.nextInt();
                switch(choice){
                    case 1:{
                    usermanager.adminLogin();
                   break;
                    }
                    case 2:{
                    usermanager.createUser();
                   break;
                    }
                    case 3:{
                    usermanager.updateUser();
                   break;
                    }
                    default:{
                        System.out.println("Enter 1/2/3");
                   break;
                    }
                }
    }
    
}
/*

System.out.println("Press");
                System.out.println("1=createUser");
                System.out.println("2=updateUser");
                System.out.println("3deleteUser");
                System.out.println("4");
                System.out.println("5");
                System.out.println("6");
                System.out.println("7");
                System.out.println("8");
                System.out.println("9");
                System.out.println("10");
                System.out.println("12");
                System.out.println("13");
*/