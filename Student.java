// HW6 by Ryan Winter
// COP3252 4/3/17

/********************************
 * Student Class                *
 * Created by: W. John Thrasher *
 * For COP3252                  *
 *********************************/
import java.io.Serializable;
import java.util.ArrayList;

public class Student  implements Serializable{ // had to add implements Serializable!

    private ArrayList<Double> testGrades;
    private ArrayList<Double> hwGrades;
    
    //Should not be serialized
    private double grade = 0;
    
    private String fname, lname;
    
    private static int totalStudents = 0;
    
    //Get the number of students implemented at this time
    public static int getNumStudents() {
	return totalStudents;
    }
    
    //Resets the count of students
    public static void resetStudentCount() {
	totalStudents = 0;
    }
    
    //Increment number of students
    public static void incrementStudentCount() {
	totalStudents++;
    }
    
    //Decrement number of students
    public static void decrementStudentCount() {
	totalStudents--;
    }
    
    
    //default constructor
    public Student() {
	this("", "");
    }
    
    //Sets student first and last name based on parameters
    public Student(String f, String l) {
	testGrades = new ArrayList<Double>();
	hwGrades = new ArrayList<Double>();
	
	fname = new String(f);
	lname = new String(l);
	totalStudents++;
    }
    
    //Calculate final grade with tests and homeworks counting for
    //50% each
    //Returns true if there are values for both test and homework
    //False if either has zero entries
    public boolean calcGrade() {
	
	double testAvg = 0;
	double hwAvg = 0;
	boolean complete = true;
	
	for (Double d: testGrades) {
	    testAvg += d;
	}
	
	for (Double d: hwGrades) {
	    hwAvg += d;
	}
	
	if (testGrades.size() >= 1) {
	    testAvg /= testGrades.size();
	} else {
	    complete = false;
	}
	
	if (hwGrades.size() >= 1) {
	    hwAvg /= hwGrades.size();
	} else {
	    complete = false;
	}
	
	grade = (testAvg + hwAvg) / 2;
	
	return complete;
    }
    
    //Get final grade
    public double getGrade() {
	return grade;
    }
    
    //Get last name
    public String getLast() {
	return lname;
    }
    
    //Get first name
    public String getFirst() {
	return fname;
    }
    
    //Adds a new homework grade and calculates the new average 
    public void addHW(double hw) {
	hwGrades.add(hw);
	calcGrade();
    }
    
    //Adds a new test grade and calculates the new average
    public void addTest(double test) {
	testGrades.add(test);
	calcGrade();
    }
    
    //override of toString to print out student information
    public String toString() {
	StringBuilder theString = new StringBuilder("");
	
	theString.append("First name: " + fname);
	theString.append("\nLast name: " + lname);
	theString.append("\nFinal Grade: " + grade + "\n");
	return theString.toString();
    }
    
    public void setname(String f, String l) {
	fname = f;
	lname = l;
    }
}