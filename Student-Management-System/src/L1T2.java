
package l1t2;
import java.util.*;


public class L1T2 {
        L1T2(){
            Scanner scanner=new Scanner(System.in);
            System.out.println("Portal");
            System.out.println("");
            System.out.println("Press 1=Teacher 2=Student 3=Admin");
            System.out.print("Enter:");
            int user=scanner.nextInt();
            switch (user) {
                case 1:
                    {
                        Teacher teacher=new Teacher();
                        // Clear console
            for (int i = 0; i < 30;i++) System.out.println();
                        teacher.teacher();
                        break;
                    }
                case 2:
                    {
                        
                        Student student=new Student();
                        // Clear console
            for (int i = 0; i < 30;i++) System.out.println();
                        student.student();
                        
                        break;
                    }
                case 3:
                    {
                        Admin admin=new Admin();
                        // Clear console
            for (int i = 0; i < 30;i++) System.out.println();
                        admin.admin();
                        break;
                    }
                default:
                    System.out.println("Enter 1/2/3");
                    break;
            }
            
            }
    
    public static void main(String[] args) {
        L1T2 ob1 = new L1T2();
        
        
        /*2 data base
        for users = my_database
        for resultsand attendance=student_records
        */
        
    }
    
}
