package l1t2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teacher {
    void teacher() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        boolean continueLoop = true; // Flag to control loop

        while (continueLoop) { // Loop until user chooses to exit
            System.out.println("Teacher portal");
            System.out.println("");
            UserManager usermanager = new UserManager();
            System.out.println("Press 1=Login 2=Create new Account 3=Forgot 4=exit");
            System.out.print("Enter:");

            if (scanner.hasNextInt()) { // Check if input is an integer
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                switch (choice) {
                    case 1: {
                        usermanager.teacherLogin();
                        continueLoop = false;
                        break;
                    }
                    case 2: {
                        usermanager.createUser();
                        // Clear console
                        for (int i = 0; i < 30; i++)
                            System.out.println();
                        System.out.println("Teacher portal");
                        System.out.println("Press 1=Login 2=Forgot 3=exit");
                        System.out.print("Choice:");
                        if (scanner.hasNextInt()) {
                            int choice1 = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            switch (choice1) {
                                case 1: {
                                    usermanager.teacherLogin();
                                    continueLoop = false;
                                    break;
                                }
                                case 2: {
                                    usermanager.updateUser();
                                    continueLoop = false;
                                    break;
                                }
                                case 3: {
                                    break;
                                }
                                default: {
                                    System.out.println("Enter valid choice");
                                }
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid choice.");
                            scanner.nextLine(); // Clear the input buffer
                        }
                        break;
                    }
                    case 3: {
                        usermanager.updateUser();
                        // Clear console
                        for (int i = 0; i < 30; i++)
                            System.out.println();
                        System.out.println("Teacher portal");
                        System.out.println("Press 1=Login 2=exit");
                        System.out.print("Choice:");
                        if (scanner.hasNextInt()) {
                            int choice1 = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            switch (choice1) {
                                case 1: {
                                    usermanager.teacherLogin();
                                    continueLoop = false;
                                    break;
                                }
                                case 2: {
                                    continueLoop = false;
                                    break;
                                }
                                default: {
                                    System.out.println("Enter valid choice");
                                }
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid choice.");
                            scanner.nextLine(); // Clear the input buffer
                        }
                        break;
                    }
                    case 4: {
                        continueLoop = false; // Exit loop
                        break;
                    }
                    default: {
                        System.out.println("Enter 1/2/3/4");
                    }
                }
            } else {
                if (scanner.hasNext()) {
                    System.out.println("Invalid input. Please enter a valid choice.");
                    scanner.next(); // Clear the invalid input
                    scanner.nextLine(); // Clear the input buffer
                } else {
                    System.out.println("Invalid input. Please enter a valid choice.");
                    scanner.close(); // Close scanner to release resources
                    return;
                }
            }
        }
        scanner.close(); // Close scanner to release resources
    }
}