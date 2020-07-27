import java.util.Scanner;
import java.util.ArrayList;

/**
 * A program that defines attributes and behavior of a user object.
 * Parent class of Student and Admin.
 * @author Xintong Zhu
 * @version 0.1
 */

public abstract class User implements java.io.Serializable {
	
	// instance variables
	
	/**
	 * A string variable of the user's user name.
	 */
	private String userName;
	/**
	 * A string variable of the user's password.
	 */
	private String password;
	/**
	 * A string of the user's first name
	 */
	protected String firstName;
	/**
	 * A string of the user's last name
	 */
	protected String lastName;
	
	
	
	// constructors
	
	/**
	 * Default constructor for a User object.
	 */
	public User() {};
	/**
	 * Overloaded constructor for a User object.
	 * @param userName A string of this user's user name.
	 * @param password A string of this user's password.
	 * @param firstName A string of this user's first name.
	 * @param lastName A string of this user's last name.
	 */
	public User (String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	
	
	// getters
	
	/**
	 * Getter of this user's user name.
	 * @return A string of this user's user name.
	 */
	public String getUserName() {
		return this.userName;
	}
	/**
	 * Getter of this user's password.
	 * @return A string of this user's password.
	 */
	public String getPassword() {
		return this.password;
	}
	
	
	
	// instance methods
	
	
	
	/**
	 * Tell the user that they would be led back to the primary menu which would let them re-choose their identy (Admin / Student), or exit the whole program).
	 */
	public void exit() {
		System.out.println("\nHope you have enjoyed your course registration!");
		System.out.println("Program would then lead you back to the primary menu.");
	} // exit
	
	/**
	 * View all courses information.
	 * Would be override by two child classes in different ways.
	 */
	public void viewAll(ArrayList<Course> courseList) {
		System.out.println("\n---------- ALL COURSES INFO ----------");
	} // viewAll

} // class
