package l1t2;

import java.util.*;
import java.sql.*;

public class UserManager {
    public UserManager() {
        // Constructor
    }

    public void createUser() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter ID: ");
            String userId = scanner.next();
            scanner.nextLine(); // consume newline character
            System.out.print("Enter Password: ");
            String userPassword = scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Father's Name: ");
            String fathersName = scanner.nextLine();
            System.out.print("Enter Mother's Name: ");
            String mothersName = scanner.nextLine();
            System.out.print("Enter Blood Group: ");
            String bloodGroup = scanner.nextLine();
            System.out.print("Enter Mobile Number: ");
            String mobileNumber = scanner.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_database", "root",
                    "<$hafin123:mysql>");
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user_info (id, password, name, fathers_name, mothers_name, blood_group, mobile_number) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userPassword);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, fathersName);
            preparedStatement.setString(5, mothersName);
            preparedStatement.setString(6, bloodGroup);
            preparedStatement.setString(7, mobileNumber);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            System.out.println("User created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void readUser(String userId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_database", "root",
                    "<$hafin123:mysql>");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_info WHERE id = '" + userId + "'");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Father's Name: " + resultSet.getString("fathers_name"));
                System.out.println("Mother's Name: " + resultSet.getString("mothers_name"));
                System.out.println("Blood Group: " + resultSet.getString("blood_group"));
                System.out.println("Mobile Number: " + resultSet.getString("mobile_number"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter ID of the user you want to update: ");
            String userId = scanner.next();
            scanner.nextLine(); // consume newline character

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_database", "root",
                    "<$hafin123:mysql>");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_info WHERE id = '" + userId + "'");

            if (resultSet.next()) {
                System.out.print("Enter New Password: ");
                String newPassword = scanner.nextLine();
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Father's Name: ");
                String fathersName = scanner.nextLine();
                System.out.print("Enter Mother's Name: ");
                String mothersName = scanner.nextLine();
                System.out.print("Enter Blood Group: ");
                String bloodGroup = scanner.nextLine();
                System.out.print("Enter Mobile Number: ");
                String mobileNumber = scanner.nextLine();

                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE user_info SET password = ?, name = ?, fathers_name = ?, mothers_name = ?, blood_group = ?, mobile_number = ? WHERE id = ?");
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, fathersName);
                preparedStatement.setString(4, mothersName);
                preparedStatement.setString(5, bloodGroup);
                preparedStatement.setString(6, mobileNumber);
                preparedStatement.setString(7, userId);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                System.out.println("Password and user information updated successfully.");
            } else {
                System.out.println("User not found.");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String userId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_database", "root",
                    "<$hafin123:mysql>");
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user_info WHERE id = ?");
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void studentLogin() {
        Scanner scanner = new Scanner(System.in);
        StudentDatabaseManager studentDatabaseManager = new StudentDatabaseManager(); // Create an object of
                                                                                      // StudentDatabaseManager

        try {
            System.out.print("Enter ID: ");
            String userId = scanner.next();
            scanner.nextLine(); // consume newline character

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_database", "root",
                    "<$hafin123:mysql>");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_info WHERE id = ?");
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // User ID exists, prompt for password
                System.out.print("Enter Password: ");
                String userPassword = scanner.nextLine();

                // Check password
                if (userPassword.equals(resultSet.getString("password"))) {
                    // Password correct, proceed with user information
                    int id = Integer.parseInt(userId); // Convert user ID from String to int
                    String name = resultSet.getString("name");
                    String fathersName = resultSet.getString("fathers_name");
                    String mothersName = resultSet.getString("mothers_name");
                    String bloodGroup = resultSet.getString("blood_group");
                    String mobileNumber = resultSet.getString("mobile_number");

                    // Clear console
                    for (int i = 0; i < 30; i++)
                        System.out.println();

                    // Print welcome message and user information
                    System.out.println("Student portal");
                    System.out.println("");
                    System.out.println("Welcome, " + name + "!");
                    System.out.println("Name: " + name);
                    System.out.println("Father's Name: " + fathersName);
                    System.out.println("Mother's Name: " + mothersName);
                    System.out.println("Blood Group: " + bloodGroup);
                    System.out.println("Mobile Number: " + mobileNumber);

                    // Ask user for action
                    System.out.println("Enter 1= check result, 2= check attendance ,3= exit:");
                    System.out.print("Enter choice:");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1: {
                            System.out.println("");
                            System.out.println("Result--------->");
                            System.out.println("");
                            // Call method to check result
                            studentDatabaseManager.readStudentResult(id); // Pass converted user ID to the method
                            System.out.println("");
                            System.out.println("Press 1=check attendance 2= exit");
                            System.out.print("Choice:");
                            int choice1 = scanner.nextInt();

                            switch (choice1) {
                                case 1: {
                                    System.out.println("");
                                    System.out.println("Attendance--------->");
                                    System.out.println("");
                                    // Call method to check attendance
                                    studentDatabaseManager.readStudentAttendance(id); // Pass converted user ID to the
                                                                                      // method
                                    System.out.println("");

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
                        case 2: {
                            System.out.println("");
                            System.out.println("Attendance--------->");
                            System.out.println("");

                            // Call method to check attendance
                            studentDatabaseManager.readStudentAttendance(id); // Pass converted user ID to the method
                            System.out.println("");
                            System.out.println("Press 1=check result 2= exit");
                            System.out.print("Choice:");
                            int choice1 = scanner.nextInt();

                            switch (choice1) {
                                case 1: {
                                    System.out.println("");
                                    System.out.println("Result--------->");
                                    System.out.println("");
                                    // Call method to check result
                                    studentDatabaseManager.readStudentResult(id); // Pass converted user ID to the
                                                                                  // method
                                    System.out.println("");
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
                        case 3: {

                            break;
                        }
                        default: {
                            System.out.println("Invalid choice.");
                            break;
                        }
                    }
                    /*
                     * if (choice == 1) {
                     * // Call method to check result
                     * studentDatabaseManager.readStudentResult(id); // Pass converted user ID to
                     * the method
                     * } else if (choice == 2) {
                     * // Call method to check attendance
                     * studentDatabaseManager.readStudentAttendance(id); // Pass converted user ID
                     * to the method
                     * }
                     * 
                     * else {
                     * System.out.println("Invalid choice.");
                     * }
                     */
                } else {
                    // Wrong password
                    System.out.println("Incorrect password. Please try again.");
                }
            } else {
                // User ID does not exist
                System.out.println("User ID not found. Please create a new account.");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void teacherLogin() {
        Scanner scanner = new Scanner(System.in);
        StudentDatabaseManager studentDatabaseManager = new StudentDatabaseManager(); // Create an object of
                                                                                      // StudentDatabaseManager

        try {
            System.out.print("Enter ID: ");
            String userId = scanner.next();
            scanner.nextLine(); // consume newline character
            System.out.print("Enter Password: ");
            String userPassword = scanner.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_database", "root",
                    "<$hafin123:mysql>");
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM user_info WHERE id = ? AND password = ?");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String fathersName = resultSet.getString("fathers_name");
                String mothersName = resultSet.getString("mothers_name");
                String bloodGroup = resultSet.getString("blood_group");
                String mobileNumber = resultSet.getString("mobile_number");

                // Clear console
                for (int i = 0; i < 30; i++)
                    System.out.println();

                // Print welcome message and user information
                System.out.println("Teacher portal");
                System.out.println("");
                System.out.println("Welcome, " + name + "!");
                System.out.println("Father's Name: " + fathersName);
                System.out.println("Mother's Name: " + mothersName);
                System.out.println("Blood Group: " + bloodGroup);
                System.out.println("Mobile Number: " + mobileNumber);

                // Prompt for action
                System.out.println("Press:");
                System.out.println("1 - Read Result & Attendance ");
                System.out.println("2 - Update Marks");
                System.out.println("3 - Update Attendance");
                System.out.println("4 - Insert Result & Attendance ");
                System.out.println("5 - Exit ");
                System.out.print("Enter:");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        // Clear console
                        for (int i = 0; i < 30; i++)
                            System.out.println();
                        // Call method to read result and attendance
                        System.out.print("Enter student ID to read result and attendance: ");
                        int studentIdResultAndAttendance = scanner.nextInt();
                        System.out.print("The Student");
                        studentDatabaseManager.readStudentResultAndAttendance(studentIdResultAndAttendance);

                        boolean validChoice = false;

                        while (!validChoice) {
                            System.out.println("Press:");
                            System.out.println("1 - Read Result & Attendance ");
                            System.out.println("2 - Update Marks");
                            System.out.println("3 - Update Attendance");
                            System.out.println("4 - Insert Result & Attendance ");
                            System.out.println("5 - Exit ");
                            System.out.print("Enter:");

                            if (scanner.hasNextInt()) { // Check if input is an integer
                                choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        // Clear console
                                        for (int i = 0; i < 30; i++)
                                            System.out.println();
                                        // Call method to read result and attendance
                                        System.out.print("Enter student ID to read result and attendance: ");
                                        studentIdResultAndAttendance = scanner.nextInt();
                                        System.out.print("The Student");
                                        studentDatabaseManager
                                                .readStudentResultAndAttendance(studentIdResultAndAttendance);
                                        break;
                                    case 2:
                                        // Call method to update marks
                                        System.out.println("Enter student ID to update marks: ");
                                        int studentIdMarks = scanner.nextInt();
                                        studentDatabaseManager.updateStudentMarks(studentIdMarks);
                                        break;
                                    case 3:
                                        // Call method to update attendance
                                        System.out.println("Enter student ID to update attendance: ");
                                        int studentIdAttendance = scanner.nextInt();
                                        studentDatabaseManager.updateStudentAttendance(studentIdAttendance);
                                        break;
                                    case 4:
                                        studentDatabaseManager.insertStudentData();
                                        break;
                                    case 5:
                                        validChoice = true; // Exit loop after valid input
                                        break;
                                    default:
                                        System.out.println("Invalid choice");
                                        break;
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a valid choice (1-5).");
                                scanner.next(); // Clear the invalid input
                            }
                        }

                        break;
                    case 2:
                        // Call method to update marks
                        System.out.println("Enter student ID to update marks: ");
                        int studentIdMarks = scanner.nextInt();

                        studentDatabaseManager.updateStudentMarks(studentIdMarks);

                        break;
                    case 3:
                        // Call method to update attendance
                        System.out.println("Enter student ID to update attendance: ");
                        int studentIdAttendance = scanner.nextInt();

                        studentDatabaseManager.updateStudentAttendance(studentIdAttendance);
                        break;
                    case 4: {
                        studentDatabaseManager.insertStudentData();
                        break;
                    }
                    case 5: {

                        break;
                    }
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } else {
                System.out.println("Incorrect ID or password. Please try again.");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void adminLogin() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter ID: ");
            String userId = scanner.next();
            scanner.nextLine(); // consume newline character
            System.out.print("Enter Password: ");
            String userPassword = scanner.nextLine();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_database", "root",
                    "<$hafin123:mysql>");
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM user_info WHERE id = ? AND password = ?");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");

                // Clear console
                for (int i = 0; i < 30; i++)
                    System.out.println();

                // Print welcome message and user information
                System.out.println("Admin portal");
                System.out.println("");
                System.out.println("Welcome, " + name + "!");
                System.out.println("Press");
                System.out.println("1=");
                System.out.println("2");
                System.out.println("3");
                System.out.println("4");
                System.out.println("5");
                System.out.println("6");
                System.out.println("7");
                System.out.println("8");
                System.out.println("9");
                System.out.println("10");
                System.out.println("12");
                System.out.println("13");
            } else {
                System.out.println("Incorrect ID or password. Please try again.");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
