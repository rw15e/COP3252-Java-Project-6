// HW6 by Ryan Winter
// COP3252 4/3/17

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

class StudentHandler  implements Serializable{
	static LinkedList<Object> students = new LinkedList<Object>(); // create linked list of students objects
	public static void main (String[] args) throws Exception { // throws Exception

	StudentHandler Object = new StudentHandler(); // create new object
	Scanner in = new Scanner(System.in); 
	int userIn = 1;
	
	do{
		System.out.println("1: Print out all loaded students");
		System.out.println("2: Add student");
		System.out.println("3: Clear students");
		System.out.println("4: Save students to file");
		System.out.println("5: Load students from file");
		System.out.println("6: Quit");
		System.out.println("Please input the number of your choice: ");
		while(!in.hasNextInt()){
			System.out.println("Invalid choice, try again.");
			in.next();
		}
		userIn = in.nextInt();
		switch(userIn){
		case 1: printAllStudents(); // print all loaded students
				break;
		case 2: addStudent(in); // add student
				break;
		case 3: clearAllStudents(); // clear students
				break;
		case 4: saveStudents(in); // save students to file
				break;
		case 5: loadStudents(in); // load students from file
				break;
		case 6: System.out.println("BYE!"); System.exit(0);
				break;
		}
	}
	while(userIn != 6);
} // end of main method

	public StudentHandler(){
		// instantiate the member data
	}
	public static void addStudent(Scanner s){
		// ask user for students name, grades, etc
		// create new object and add it the the StudentHandler
		
		Student tempStudent = new Student(); // creates a tempStudent Student object to be added to the StudentHandler list
		Scanner s2 = new Scanner(System.in);
		
		String firstName;
		String lastName;
		double tempInputHW = 1.0; 
		double tempInputT = 1.0;
		
		System.out.println("Please input a first name: ");
		firstName = s2.nextLine(); 
		System.out.println("Please input a last name: ");
		lastName = s2.nextLine();
		tempStudent.setname(firstName, lastName); // sets user inputed first/last names to the first/last name of this Student object
	
		do{ // accepts hw grades until a negative number entered
			System.out.println("Please input student homework grades one at a time (negative value to finish): ");
			while(!s2.hasNextDouble()){
				System.out.println("Invalid input, please try inputting the student again");
				s2.next();
			}
			tempInputHW = s2.nextDouble();
			if(tempInputHW > 0)
				tempStudent.addHW(tempInputHW); // adds the homework grade to tempStudent object
		}
		while(tempInputHW > -1); // runs until a negative value is entered
		
		do{ // accepts test grades until a negative number entered
			System.out.println("Please input student test grades one at a time (negative value to finish): ");
			while(!s2.hasNextDouble()){
				System.out.println("Invalid input, please try inputting the student again");
				s2.next();
			}
			tempInputT = s2.nextDouble();
			if(tempInputT > 0)
				tempStudent.addTest(tempInputT); // adds the homework grade to tempStudent object
		}
		while(tempInputT > -1); // runs until a negative value is entered
	
		students.add(tempStudent); // adds tempStudent to StudentHandler linked list 
	}
	public static void saveStudents(Scanner s) throws Exception{
		// ask user for filename to save under, and save the entire collection of students held in 
		// this StudentHandler as serialized objects
		
		Scanner s2 = new Scanner(System.in); 
		String temp1;
		System.out.println("Please input the filename to save to: ");
		temp1 = s2.nextLine();
		FileOutputStream outFile = new FileOutputStream(temp1); 
		ObjectOutputStream outFileStream = new ObjectOutputStream(outFile);
		
		outFileStream.writeObject(students); // check logic!
		outFileStream.close();	
	}
	public static void loadStudents(Scanner s) throws Exception{
		// clear all students out of StudentHandler, then ask for filename and load all students stored in that file
		// into the StudentHandler
		Scanner s2 = new Scanner(System.in);
		clearAllStudents(); // clears linked list
		String temp1;
		System.out.println("Please input the filename to load from: ");
		temp1 = s2.nextLine();
		ObjectInputStream inFileStream = new ObjectInputStream(new FileInputStream(temp1));
		
		LinkedList<Object> myList = (LinkedList<Object>) inFileStream.readObject();
		inFileStream.close();
	
		students.addAll(myList);
	}
	public static void printAllStudents(){
		// print all the the students, sorted by name (last name first, if they are same then sort by first name),
		// as well as print count of number of student records. MUST use static totalStudents member data for this counter
		
		// need to sort linked list by name, last name first (if they match then by first name)
		//Collections.sort(students);
		while(students.size() > Student.getNumStudents())
			Student.incrementStudentCount();
        System.out.println(students.toString());
		System.out.println("Total student records: " + Student.getNumStudents()); 
	}
	public static void clearAllStudents(){
		while(Student.getNumStudents() > 0)
			Student.decrementStudentCount();
		students.clear(); // removes all elements from linked list
	}
}