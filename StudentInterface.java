import java.util.ArrayList;

/**
 * Interface for Student class that defines signatures of methods which are used in Student class.
 * @author Xintong Zhu
 * @version 0.1
 */

public interface StudentInterface {
	
	/**
	 * View all courses' information.
	 * @param An array list of Course objects.
	 */
	public void viewAll(ArrayList<Course> courseList);
	
	/**
	 * View all courses that are not full.
	 * @param courseList An array list of Course objects.
	 */
	public void viewNotFull (ArrayList<Course> courseList);
	
	/**
	 * Allow a student to register into a specific course by providing his/her first name and last name, and add this course to this student's course list.
	 * @param courseList An array list of Course objects.
	 * @param studentList An array list of Student object.
	 */
	public void registerInCourse(ArrayList<Course> courseList, ArrayList<Student> studentList);
	
	/**
	 * Allow a student to withdraw from a course and remove this course from this student's course list.
	 * @param courseList An array list of Course object.
	 * @param studentList An array list of Student object.
	 */
	public void withdraw(ArrayList<Course> courseList, ArrayList<Student> studentList);
	
	/**
	 * Review all registered courses of this student.
	 */
	public void reviewAllRegistered();

} // interface
