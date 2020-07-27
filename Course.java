import java.util.ArrayList;

/**
 * A program to generate Course object with basic info.
 * @author Xintong Zhu
 * @version
 */
public class Course implements java.io.Serializable, Comparable<Course> {
	
	// instance attributes
	
	/**
	 * A string variable of the course's name.
	 */
	private String courseName;
	/**
	 * A string variable of the course's ID.
	 */
	private String courseId;
	/**
	 * An integer variable of the maximum number of students in this course.
	 */
	private int maxStudents;
	/**
	 * An integer of the number of current students enrolled in this course.
	 */
	private int currentStudents;
	/**
	 * An array list of Student object who enrolled in this course (and would later be used to display their names).
	 */
	private ArrayList<Student> studentObject;
	/**
	 * An array list of string of the enrolled students' names.
	 */
	private ArrayList<String> studentName;
	/**
	 * A string variable of the course's instructor.
	 */
	private String courseInstructor;
	/**
	 * An integer variable of the course's section number.
	 */
	private int courseSection;
	/**
	 * A string variable of the course's location.
	 */
	private String location;
	
	
	
	// constructors
	
	/**
	 * Default constructor of a Course object.
	 */
	public Course() {};
	/**
	 * Overloaded constructor of a Course object that set the basic information up.
	 * @param courseName A string of the course's name.
	 * @param courseId A string of the course's ID.
	 * @param maxStudents An integer of the maximum number of students in this course.
	 * @param currentStudents An integer of the number of current students in this course.
	 * @param studentName An array list of string of enrolled students' raw names.
	 * @param courseInstructor A string of this course's instructor.
	 * @param courseSection An integer of this course's section number.
	 * @param location A string of this course's location.
	 * @param studentList An array list consisted of Student objects who have enrolled in this course.
	 */
	public Course(String courseName, String courseId, int maxStudents, int currentStudents, String courseInstructor, int courseSection, String location) {
		this.courseName = courseName;
		this.courseId = courseId;
		this.maxStudents = maxStudents;
		this.currentStudents = currentStudents;
		this.courseInstructor = courseInstructor;
		this.courseSection = courseSection;
		this.location = location;
		this.studentName = new ArrayList<String>();
		this.studentObject = new ArrayList<Student>();
	}

	
	
	
	// getters and setters

	/**
	 * Getter of the course name.
	 * @return A string of this course's name.
	 */
	public String getCourseName() {
		return this.courseName;
	}
	/**
	 * Setter of the course name.
	 * @param courseName A string of this course's name to be set.
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * Getter of the course id.
	 * @return A string of this course's id.
	 */
	public String getCourseId() {
		return this.courseId;
	}
	/**
	 * Setter of the course id.
	 * @return A string of this course's id to be set.
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	/**
	 * Getter of the course's maximum students number.
	 * @return An integer of this course's maximum students number.
	 */
	public int getMaxStudents() {
		return this.maxStudents;
	}
	/**
	 * Setter of the course's maximum students number.
	 * @return An integer of this course's maximum students number to be set.
	 */
	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	/**
	 * Getter of the course's current students number.
	 * @return An integer of this course's current students number.
	 */
	public int getCurrentStudents() {
		return this.currentStudents;
	}
	/**
	 * Setter of the course's current students number.
	 * @return An integer of this course's current students number to be set.
	 */
	public void setCurrentStudents(int currentStudents) {
		this.currentStudents = currentStudents;
	}
	/**
	 * Getter of the course's students list.
	 * @return An array list of Student objects enrolled in this course.
	 */
	public ArrayList<Student> getStudentObject() {
		return this.studentObject;
	}
	/**
	 * Setter of the course's students list.
	 * @return An array list of Student objects enrolled in this course to be set.
	 */
	public void setStudentObject(ArrayList<Student> studentObject) {
		this.studentObject = studentObject;
	}
	/**
	 * Getter of this course's instructor. 
	 * @return A string of this course's instructor.
	 */
	public String getCourseInstructor() {
		return this.courseInstructor;
	}
	/**
	 * Setter of this course's instructor. 
	 * @return A string of this course's instructor to be set.
	 */
	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}
	/**
	 * Getter of the course's section number.
	 * @return An integer of the course's section number.
	 */
	public int getCourseSection() {
		return this.courseSection;
	}
	/**
	 * Setter of the course's section number.
	 * @return An integer of the course's section number to be set.
	 */
	public void setCourseSection(int courseSection) {
		this.courseSection = courseSection;
	}
	/**
	 * Getter of the course's location.
	 * @return A string of the course's location.
	 */
	public String getLocation() {
		return this.location;
	}
	/**
	 * Setter of the course's location.
	 * @return A string of the course's location to be set.
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * Getter of the course's students' names lists.
	 * @return An array list of String objects of students' names enrolled in this course.
	 */
	public ArrayList<String> getStudentName(){
		return this.studentName;
	}
	
	
	
	// instance methods
	
	@Override
	/**
	 * Override the comparetTo method to sort the course object by current students number.
	 */
	public int compareTo(Course course) {
		// converse the primitive int type to Integer type
		Integer currentInt = new Integer(this.getCurrentStudents());
		Integer customerInt = new Integer(course.getCurrentStudents());
		return currentInt.compareTo(customerInt);
	} // CompareTo
	
	/**
	 * Add current students number (because of new students enrolled).
	 */
	public void addStudents() {
		this.currentStudents++;
	} // addStudents
	
	/**
	 * Reduce current students number (because of students removed).
	 */
	public void minusStudents() {
		this.currentStudents--;
	} // minusStudents

} // class