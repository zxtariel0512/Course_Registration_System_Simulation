import java.util.ArrayList;

/**
 * Interface for Admin class that defines signatures of methods which are used in Admin class.
 * @author Xintong Zhu
 * @version 0.1
 */

public interface AdminInterface {
	
	/**
	 * Create a new course based on the Admin's request, and add it to the current course list.
	 * @param courseList An array list of Course objects of the current course list.
	 */
	public void createCourse(ArrayList<Course> courseList);

	/**
	 * Delete a course based on course ID
	 * @param courseList An array list of Course objects of the current course list.
	 */
	public void deleteCourse(ArrayList<Course> courseList);
	
	/**
	 * Edit the course information.
	 * @param courseList An array list of Course objects of current course information.
	 */
	public void editCourse (ArrayList<Course> courseList, ArrayList<String> studentNameList, ArrayList<Student> studentList);
	
	/**
	 * Display the information of a given course.
	 * @param courseList An array list of Course objects of current course information.
	 */
	public void display(ArrayList<Course> courseList);
	
	/**
	 * Register a student by entering basic student's information without assigning to course.
	 * @param studentList An array list of Student objects of current students.
	 * @return A Student object that has just been registered.
	 */
	public Student registerStudent(ArrayList<Student> studentList);
	
	/**
	 * View all courses information, including student's basic information.
	 * @param An array list of course objects.
	 */
	public void viewAll(ArrayList<Course> courseList);
	
	/**
	 * View all courses (course name and section #) that are full.
	 * @param courseList An array list of Course objects.
	 */
	public void viewFull(ArrayList<Course> courseList);
	
	/**
	 * Write a file to list all full courses.
	 * @param courseList An array list of Course objects.
	 */
	public void fileFull(ArrayList<Course> courseList);
	
	/**
	 * View students' names of a specific course.
	 * @param courseList An array list of Course objects.
	 */
	public void viewStudents(ArrayList<Course> courseList);
	
	/**
	 * View all courses a given student has registered.
	 * @param studentList An array list of Student objects of current existed students.
	 */
	public void courseOfStudent(ArrayList<Student> studentList);
	
	/**
	 * Sort courses based on current students number.
	 * @param courseList An array list of course objects.
	 */
	public void sortCourse(ArrayList<Course> courseList);
	
	
	
} // interface
