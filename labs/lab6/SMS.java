import java.sql.*;
import java.util.ArrayList;

public class SMS {
    
    // main program to print students with their enrolled courses from data in the MYSql 'SMS2' database
	
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/SMS2";
        String user = "root";
        String password = "TA002757903";
		// 'Reports()' method could have multiple report types
        Reports("enrollment", url, user, password);   
    }
    
    public static void Reports(String reportName, String url, String user, String password) {
        ArrayList<Student> students = new ArrayList<>();
        
		if(reportName.toLowerCase().equals("enrollment")) {
            String sqlcommand = "SELECT student_id, firstname, lastname, classify, major "+ 
                                "FROM students " +
                                "ORDER BY student_id; ";
                                
            try (Connection myConn = DriverManager.getConnection(url, user, password)) {
                Statement myStmt = myConn.createStatement();
                ResultSet myRS = myStmt.executeQuery(sqlcommand);
                
                while (myRS.next()) {
                    Student temp = new Student();
					
					// use 'temp' to store studentID, firstname, lastname, classification, major, and the 
					// associated 'enrolled courses' using methods in the 'Student.java' object
                    // add the temp 'student' to the ArrayList (students) command: 'students.add(temp);'	
                    temp.setStudent_id(myRS.getInt("student_id"));
                    temp.setFname(myRS.getString("firstname"));
                    temp.setLname(myRS.getString("lastname"));
                    temp.setClassify(myRS.getString("classify"));
                    temp.setMajor(myRS.getString("major"));
                    temp.setEnrolled_courses();
                    students.add(temp);
                }
                myConn.close();
            
            } catch (Exception e) {
                System.out.println("ERROR " + e.getLocalizedMessage());
            } finally {
            }
			
			// calling the helper method, 'PrintEnrollments(students)' to print a student and 
			// all their enrolled courses.
			
			// STUDY THE CODE IN "PrintEnrollments()" method. 
            PrintEnrollments(students);
			
        } else {
            System.out.println("\n\n****ERROR**** Invalid report requested\n\n");
        }
    }
    
    public static void PrintEnrollments(ArrayList<Student> myStudents) {
		
		// * * * * * * * * * * * * change to your name here...
        System.out.println("\n\n\nStudent Enrollment Report by Taaruni Ananya\n");
        System.out.println("ID   CLASS\t MAJOR\t NAME\t\t\tEnrollments");
        for(Student s:myStudents) {
			
			// print the student information stored in 's' variable 
			// output as formatted in the instructions with using
			// the methods from 'Student.java' object. Use the "\t" for tab
			
            System.out.println(s.getStudent_id() + "   " + s.getClassify() + "\t " + s.getMajor() + "\t " + s.getFname() + " " + s.getLname());
           
		    // I am giving you the setup data structure to iterate over each student's enrollments using 
			// a second Arraylist and loading if first with 's.getEnrolled_courses()' method
			
            ArrayList<Enrollments> e1 = new ArrayList<>();
            e1.addAll(s.getEnrolled_courses());
            
            int count = 1;
			for( Enrollments eStudent:e1 ) {
				String enrollmentText = String.format("%d) %s %d, HRS:%d, %s",
                        count,
                        eStudent.getDeptID(),
                        eStudent.getCourseID(),
                        eStudent.getCourseHours(),
                        eStudent.getCourseName()
                );
                System.out.println("\t\t\t\t\t\t" + enrollmentText);
                count++;
            }
            System.out.println("\t\t\t\t\t\t-----------------------------------------");
        }
    }
} 