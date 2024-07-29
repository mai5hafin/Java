package l1t2;

import java.util.Scanner;

public class Student {

    void student() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true; // Flag to control loop

        while (continueLoop) { // Loop until user chooses to exit
            System.out.println("Student portal");
            System.out.println("");
            UserManager usermanager = new UserManager();
            System.out.println("Press 1=Login 2=Create new Account 3=Forgot 4=exit");
            System.out.print("Enter:");

            if (scanner.hasNextInt()) { // Check if input is an integer
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: {
                        usermanager.studentLogin();
                        break;
                    }
                    case 2: {
                        usermanager.createUser();
                        // Clear console
                        for (int i = 0; i < 30; i++)
                            System.out.println();
                        System.out.println("Student portal");
                        System.out.println("Press 1=Login 2=Forgot 3=exit");
                        System.out.print("Choice:");
                        int choice1 = scanner.nextInt();
                        switch (choice1) {
                            case 1: {
                                usermanager.studentLogin();
                                break;
                            }
                            case 2: {
                                usermanager.updateUser();
                                break;
                            }
                            case 3: {
                                break;
                            }
                            default: {
                                System.out.println("Enter valid choice");
                            }
                        }
                        break;
                    }
                    case 3: {
                        usermanager.updateUser();
                        // Clear console
                        for (int i = 0; i < 30; i++)
                            System.out.println();
                        System.out.println("Student portal");
                        System.out.println("Press 1=Login 2=exit");
                        System.out.print("Choice:");
                        int choice1 = scanner.nextInt();
                        switch (choice1) {
                            case 1: {
                                usermanager.studentLogin();
                                break;
                            }
                            case 2: {
                                break;
                            }
                            default: {
                                System.out.println("Enter valid choice");
                            }
                        }
                        break;
                    }
                    case 4: {
                        continueLoop = false; // Exit loop
                        break;
                    }
                    default: {
                        System.out.println("Invalid input. Enter 1/2/3/4");
                        break;
                    }
                }
            } else {
                System.out.println("Invalid input. Enter 1/2/3/4.");
                scanner.next(); // Clear the invalid input
            }
        }
        scanner.close(); // Close scanner to release resources
    }
}
