
package l1t2;
import java.sql.*;
import java.util.Scanner;

public class StudentDatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_records";
    private static final String USER = "root";
    private static final String PASS = "<$hafin123:mysql>";

    public StudentDatabaseManager() {
        // Constructor
    }

    // Method to create the database if it does not exist
    public void createDatabase(String dbName) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", USER, PASS);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to create table for storing student results and attendance
    public void createTable() {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement()) {
        String sql = "CREATE TABLE IF NOT EXISTS student_data (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "subject VARCHAR(255)," + // Add subject column
                "marks INT," +
                "grade VARCHAR(5)," +
                "attendance DOUBLE," +
                "total_classes INT," +
                "attended_classes INT)";
        stmt.executeUpdate(sql);
        System.out.println("Table created successfully");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // Method to insert student data
public void insertStudentData() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter student ID: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consume newline character
    System.out.print("Enter subject name: ");
    String subject = scanner.nextLine();
    System.out.print("Enter marks: ");
    int marks = scanner.nextInt();
    System.out.print("Enter total classes: ");
    int totalClasses = scanner.nextInt();
    System.out.print("Enter attended classes: ");
    int attendedClasses = scanner.nextInt();

    double attendance = (double) attendedClasses / totalClasses * 100; // Calculate attendance percentage

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement("INSERT INTO student_data (id, subject, marks, grade, attendance, total_classes, attended_classes) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
        String grade = calculateGrade(marks);
        pstmt.setInt(1, id);
        pstmt.setString(2, subject);
        pstmt.setInt(3, marks);
        pstmt.setString(4, grade);
        pstmt.setDouble(5, attendance);
        pstmt.setInt(6, totalClasses);
        pstmt.setInt(7, attendedClasses);
        pstmt.executeUpdate();
        System.out.println("Student data inserted successfully");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



    // Method to calculate grade based on marks
    private String calculateGrade(int marks) {
        // Implement your grading system logic here
        if (marks >= 95) {
            return "A+";
        } else if (marks >= 90) {
            return "A";
        } else if (marks >= 85) {
            return "A-";
        } else if (marks >= 80) {
            return "B+";
        } else if (marks >= 75) {
            return "B";
        } else if (marks >= 70) {
            return "B-";
        } else if (marks >= 65) {
            return "C+";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 55) {
            return "C-";
        } else if (marks >= 50) {
            return "D+";
        } else if (marks >= 45) {
            return "D";
        } else if (marks >= 40) {
            return "D-";
        } else {
            return "Fail";
        }
    }

    // Method to read student result by ID
public void readStudentResult(int id) {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement("SELECT id, name, subject, marks, grade FROM student_data WHERE id = ?")) {
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Subject: " + rs.getString("subject"));
            System.out.println("Marks: " + rs.getInt("marks"));
            System.out.println("Grade: " + rs.getString("grade"));
        } else {
            System.out.println("Student not found");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

// Method to read student attendance by ID
public void readStudentAttendance(int id) {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement("SELECT id, subject, attendance, total_classes, attended_classes FROM student_data WHERE id = ?")) {
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Subject: " + rs.getString("subject"));
            System.out.println("Attendance: " + rs.getDouble("attendance") + "%");
            System.out.println("Total Classes: " + rs.getInt("total_classes"));
            System.out.println("Attended Classes: " + rs.getInt("attended_classes"));
        } else {
            System.out.println("Student not found");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void readStudentResultAndAttendance(int id) {
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement("SELECT id, name, subject, marks, grade, attendance, total_classes, attended_classes FROM student_data WHERE id = ?")) {
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Subject: " + rs.getString("subject"));
            System.out.println("Marks: " + rs.getInt("marks"));
            System.out.println("Grade: " + rs.getString("grade"));
            System.out.println("Attendance: " + rs.getDouble("attendance"));
            System.out.println("Total Classes: " + rs.getInt("total_classes"));
            System.out.println("Attended Classes: " + rs.getInt("attended_classes"));
        } else {
            System.out.println("Student not found");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}




   // Method to update student marks
public void updateStudentMarks(int id) {
    Scanner scanner = new Scanner(System.in);

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement("UPDATE student_data SET marks = ?, grade = ? WHERE id = ?")) {
        
        // Prompt the teacher to enter the new marks
        System.out.print("Enter new marks: ");
        int marks = scanner.nextInt();

        // Prompt the teacher to enter the subject name
        System.out.print("Enter subject: ");
        String subject = scanner.next();

        // Calculate grade based on the new marks
        String grade = calculateGrade(marks);

        // Set parameters for the SQL statement
        pstmt.setInt(1, marks);
        pstmt.setString(2, grade);
        pstmt.setInt(3, id);

        // Execute the update query
        int rowsAffected = pstmt.executeUpdate();
        
        // Check if the update was successful
        if (rowsAffected > 0) {
            System.out.println("Marks updated successfully");
        } else {
            System.out.println("Student not found");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        scanner.close(); // Close the scanner
    }
}

// Method to update student attendance
public void updateStudentAttendance(int id) {
    Scanner scanner = new Scanner(System.in);

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         PreparedStatement pstmt = conn.prepareStatement("UPDATE student_data SET attendance = ?, total_classes = ?, attended_classes = ? WHERE id = ?")) {
        
        // Prompt the teacher to enter the total classes
        System.out.print("Enter total classes: ");
        int totalClasses = scanner.nextInt();

        // Prompt the teacher to enter the attended classes
        System.out.print("Enter attended classes: ");
        int attendedClasses = scanner.nextInt();

        // Prompt the teacher to enter the subject name
        System.out.print("Enter subject: ");
        String subject = scanner.next();

        // Calculate attendance percentage
        double attendance = (double) attendedClasses / totalClasses * 100;

        // Set parameters for the SQL statement
        pstmt.setDouble(1, attendance);
        pstmt.setInt(2, totalClasses);
        pstmt.setInt(3, attendedClasses);
        pstmt.setInt(4, id);

        // Execute the update query
        int rowsAffected = pstmt.executeUpdate();
        
        // Check if the update was successful
        if (rowsAffected > 0) {
            System.out.println("Attendance updated successfully");
        } else {
            System.out.println("Student not found");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        scanner.close(); // Close the scanner
    }
}



    // Method to delete student data
    public void deleteStudentData(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM student_data WHERE id = ?")) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student data deleted successfully");
            } else {
                System.out.println("Student not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}

