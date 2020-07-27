import java.util.ArrayList;
import java.util.Scanner;

/**
 * A program that generates Student object and defines several instance methods and instance behaviors.
 * Inherit from User class
 * Implements the java.io.Serializable interface.
 * @author Xintong Zhu
 * @version 0.1
 */
public class Student extends User implements java.io.Serializable, StudentInterface {

	// instance variables
	
	/**
	 * A string variable of the student's first name.
	 */
	//private String firstName;
	/**
	 * A string variable of the student's last name.
	 */
	//private String lastName;
	/**
	 * An array list of string of this student's registered course.
	 */
	private ArrayList<String> registeredCourse;
	
	
	
	// constructors
	
	/**
	 * Default constructor of Student object.
	 */
	public Student() {};
	/**
	 * Overloaded constructor of Student object that defines all instance attributes.
	 * @param userName A string of student's username.
	 * @param password A string of student's password.
	 * @param firstName A string of student's first name.
	 * @param lastName A string of student's last name.
	 */
	public Student (String userName, String password, String firstName, String lastName) {
		// inherit from the parent class' constructor
		super(userName, password);
		// add child class' own instance attributes
		this.firstName = firstName;
		this.lastName = lastName;
		this.registeredCourse = new ArrayList<String>();
	}
	/**
	 * Overloaded constructor of Student object that also defines registered courses.
	 * @param userName A string of student's username.
	 * @param password A string of student's password.
	 * @param firstName A string of student's first name.
	 * @param lastName A string of student's last name.
	 */
	public Student (String userName, String password, String firstName, String lastName, ArrayList<String> registeredCourse) {
		// inherit from the parent class' constructor
		super(userName, password);
		// add child class' own instance attributes
		this.firstName = firstName;
		this.lastName = lastName;
		this.registeredCourse = registeredCourse;
	}
	
	
	
	// getters
	
	/**
	 * Getter of this student's first name.
	 * @return A string of this student's first name.
	 */
	public String getFirstName() {
		return this.firstName;
	}
	/**
	 * Getter of this student's last name.
	 * @return A string of this student's last name.
	 */
	public String getLastName() {
		return this.lastName;
	}
	/**
	 * Getter of this student's registered course.
	 * @return An array list of string of all courses this student has registered.
	 */
	public ArrayList<String> getRegisteredCourse(){
		return this.registeredCourse;
	}
	
	
	
	
	// instance methods
	
	// create a scanner object
	transient Scanner scan = new Scanner (System.in);
	
	@Override
	/**
	 * View all courses' information.
	 * @param An array list of Course objects.
	 */
	public void viewAll(ArrayList<Course> courseList) {
		// inherit from the parent class
		super.viewAll(courseList);
		System.out.println();
		// use a for loop to iterate through the course
		for (Course course: courseList) {
			System.out.println("* COURSE NAME: " + course.getCourseName());
			System.out.println("* COURSE ID: " + course.getCourseId());
			System.out.println("* MAX #: " + course.getMaxStudents());
			System.out.println("* CURRENT #: " + course.getCurrentStudents());
			System.out.println("* COURSE INSTRUCTOR: " + course.getCourseInstructor());
			System.out.println("* LOCATION: " + course.getLocation());
			System.out.println("\n*********************** press ENTER to continue view next ... **********************");
			String nextCourse = scan.nextLine();
		} // course
	} // courseList
	
	/**
	 * View all courses that are not full.
	 * @param courseList An array list of Course objects.
	 */
	public void viewNotFull (ArrayList<Course> courseList) {
		System.out.println("The course name and course section # are listed below:");
		// create a variable if all courses are full
		int notFull = 0;
		// use a for loop to iterate through the course list
		for (Course course: courseList) {
			// check if this course is full
			if (course.getMaxStudents() > course.getCurrentStudents()) {
				// not full
				System.out.println("* " + course.getCourseName() + " (Section #" +  course.getCourseSection() + ")");
				notFull++;
			}
		} // course
		// if all courses are full
		if (notFull == 0) {
			System.out.println("\nSorry, but all courses are currently full :(");
		}
	} // viewNotFull
	
	/**
	 * Allow a student to register into a specific course by providing his/her first name and last name, and add this course to this student's course list.
	 * @param courseList An array list of Course objects.
	 * @param studentList An array list of Student object.
	 */
	public void registerInCourse(ArrayList<Course> courseList, ArrayList<Student> studentList) {
//		System.out.println(courseList.get(0).getCourseName());
		// prompt the user for the course information
		System.out.print("Please enter the COURSE NAME (EXACTLY and CORRECTLY): ");
		String registerCourseName = scan.nextLine();
		System.out.print("Please enter the COURSE SECTION # (EXACTLY and CORRECTLY): ");
		int registerCourseSection = Integer.parseInt(scan.nextLine());
		int registerIndex = -1;
		// find the course
		for (Course course: courseList) {
			if (course.getCourseName().equals(registerCourseName) && course.getCourseSection() == registerCourseSection) {
				registerIndex = courseList.indexOf(course);
			}
		} // course
		if (registerIndex == -1) {
			// invalid course information
			System.out.println("\nNo course found! Please check the course information.");
		}
		else {
			// valid course information
			// check if the course is currently full
			if (courseList.get(registerIndex).getMaxStudents() == courseList.get(registerIndex).getCurrentStudents()) {
				// course is full
				System.out.println("\nSorry! This course has already been full. Please try another course.");
			}
			else {
				// course not full
				System.out.println("\n- In order for the exact personal information correctness, case matter.");
				System.out.println("- So please enter your name exactly as your registered name.\n");
				System.out.print("Please enter your first name (example: Mary): ");
				String studFirst = scan.nextLine();
				System.out.print("Please enter your last name (example: Smith): ");
				String studLast = scan.nextLine();
				// set a variable for unvalid input
				int check = 0;
				// check the validity
				for (Student student: studentList) {
					if (student.getFirstName().equals(studFirst) && student.getLastName().equals(studLast)) {
						// valid
						check++;
						// check if this student is already in this course
						if (courseList.get(registerIndex).getStudentName().indexOf(studFirst + " " + studLast) >= 0 ) {
							// already in
							System.out.println("\nYou are already in this course! No need to register again :)");
						}
						else {
							// not in
							// add this student to the course
							courseList.get(registerIndex).getStudentName().add(studFirst + " " + studLast);
							courseList.get(registerIndex).getStudentObject().add(student);
							// add the course number
							courseList.get(registerIndex).addStudents();
							System.out.println("\nCongratulations! You have successfully registered into this course.");
//							System.out.println(courseList.get(registerIndex).getCurrentStudents());
							// add this course to the student's course list
							this.registeredCourse.add(courseList.get(registerIndex).getCourseName());
						}
					}
				} // student
				// if invalid
				if (check == 0) {
					System.out.println("\nIncorrect personal information. Please check your first name and last name.");
				}
			}
		}
	} // registerInCourse
	
	/**
	 * Allow a student to withdraw from a course and remove this course from this student's course list.
	 * @param courseList An array list of Course object.
	 * @param studentList An array list of Student object.
	 */
	public void withdraw(ArrayList<Course> courseList, ArrayList<Student> studentList) {
		// prompt the user for the course information
		System.out.print("Please enter the COURSE NAME (EXACTLY and CORRECTLY): ");
		String withdrawName = scan.nextLine();
		System.out.print("Please enter the COURSE SECTION # (EXACTLY and CORRECTLY): ");
		int withdrawSection = Integer.parseInt(scan.nextLine());
		int withdrawIndex = 0;
		// find the course
		for (Course course: courseList) {
			if (course.getCourseName().equals(withdrawName) && course.getCourseSection() == withdrawSection) {
				withdrawIndex = courseList.indexOf(course);
			}
		} // course
		if (withdrawIndex == 0) {
			// invalid course information
			System.out.println("\nNo course found! Please check the course information.");
		}
		else {
			// valid course information
			// prompt the user for personal information
			System.out.print("Please enter your first name (example: Mary): ");
			String studFirst = scan.nextLine();
			System.out.print("Please enter your last name (example: Smith): ");
			String studLast = scan.nextLine();
			// set a variable for unvalid input
			int check = 0;
			// check the validity
			for (Student student: studentList) {
				if (student.getFirstName().equals(studFirst) && student.getLastName().equals(studLast)) {
					// valid
					check++;
					// check if this student is actually in this course
					if (courseList.get(withdrawIndex).getStudentName().indexOf(studFirst + " " + studLast) < 0) {
						// student currently not in this course
						System.out.println("\nYou are actually not in this course. No need to withdraw.");
						System.out.println(courseList.get(withdrawIndex).getCurrentStudents());
					}
					else {
						// in this course
						// remove this student from the course
//						System.out.println(courseList.get(withdrawIndex).getStudentObject().toString());
						courseList.get(withdrawIndex).getStudentName().remove(studFirst + " " + studLast);
						courseList.get(withdrawIndex).getStudentObject().remove(student);
//						System.out.println(courseList.get(withdrawIndex).getStudentObject().toString());
						// minus the current students in course
						courseList.get(withdrawIndex).minusStudents();
						System.out.println("\nYou have withdrawn from this course.");
//						System.out.println(courseList.get(withdrawIndex).getCurrentStudents());
						// remove this course from the student's course list
						this.registeredCourse.remove(courseList.get(withdrawIndex).getCourseName());
					}					
				}
			} // student
			// if invalid
			if (check == 0) {
				System.out.println("\nIncorrect personal information. Please check your first name and last name.");
			}
		}
	} // withdraw
	
	/**
	 * Review all registered courses of this student.
	 */
	public void reviewAllRegistered() {
		System.out.println("* You have currently registered in ...\n");
		System.out.println("- emplty if no course registered -");
		System.out.println(this.registeredCourse.toString());
	} // reviewAllRegistered 
	
	
} // class
