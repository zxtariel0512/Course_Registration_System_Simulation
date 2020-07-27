import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * A program that generates Admin object and defines several instance behaviors.
 * Inherit from the User class.
 * @author Xintong Zhu
 * @version 0.1
 */

public class Admin extends User implements AdminInterface {
	
	// constructor
	
	/**
	 * Default constructor of Admin object.
	 */
	public Admin() {}
	/**
	 * Overloaded constructor of Admin object to set up several instance attributes.
	 * Inherit from User parent class.
	 * @param userName A string of user name.
	 * @param password A string of password.
	 */
	public Admin(String userName, String password) {
		// inherit from the User constructor
		super(userName, password);
	}
	
	
	
	// instant methods
	
	// create a scanner object
	Scanner scan = new Scanner (System.in);
	
	/**
	 * Create a new course based on the Admin's request, and add it to the current course list.
	 * @param courseList An array list of Course objects of the current course list.
	 */
	public void createCourse(ArrayList<Course> courseList) {		
		// prompt the user for basic information
		System.out.print("* Please enter the COURSE NAME: ");
		String courseName = scan.nextLine();
		System.out.print("* Please enter the COURSE ID: ");
		String courseId = scan.nextLine();
		System.out.print("* Please enter the MAXIMUM # OF STUDENTS: ");
		int maxStudents = Integer.parseInt(scan.nextLine());
		System.out.print("* Please enter the INSTRUCTOR NAME: ");
		String instructor = scan.nextLine();
		System.out.print("* Please enter the COURSE SECTION #: ");
		int courseSection = Integer.parseInt(scan.nextLine());
		System.out.print("* Please enter the LOCATION: ");
		String location = scan.nextLine();		
		// create a new course
		Course newCourse = new Course(courseName, courseId, maxStudents, 0, instructor, courseSection, location);		
		// add the course to the course list
		courseList.add(newCourse);
		System.out.println("\nCongratulations! A new course has been created. ");
		System.out.println("The current students # is automatically set to be 0, and the students name list is automatically set to be empty.");	
	} // createCourse
	
	/**
	 * Delete a course based on course ID
	 * @param courseList An array list of Course objects of the current course list.
	 */
	public void deleteCourse(ArrayList<Course> courseList){		
		// prompt the user for the course to be deleted
		System.out.print("* Please enter the COURSE ID that you would like to delete (please enter it CORRECTLY and EXACTLY): ");
		String deleteCourse = scan.nextLine();	
		System.out.print("* Please enter the COURSE SECTION that you would like to delete (please enter it CORRECTLY and EXACTLY): ");
		int deleteSection = Integer.parseInt(scan.nextLine());
		// set an variable to check validity
		int validityCheck = 0;
		
		// find and remove the deleted course
		for (int c = 0; c < courseList.size(); c++) {
			if (courseList.get(c).getCourseId().equals(deleteCourse) && courseList.get(c).getCourseSection() == deleteSection) {
				courseList.remove(c);
				System.out.println("\nThe course has been deleted.");
				validityCheck = 1;
			}
		} // for c		
		// report the validity check result
		if (validityCheck == 0) {
			System.out.println("\nNo course found in existing course system. ");
			System.out.println("Hence no change would be made. Please make sure the course you would like to delete currently exists in the course system." );		
		}
	} // deleteCourse
	
	/**
	 * Edit the course information.
	 * @param courseList An array list of Course objects of current course information.
	 */
	public void editCourse (ArrayList<Course> courseList, ArrayList<String> studentNameList, ArrayList<Student> studentList){
		
		// prompt the user for the course to be edited
		System.out.println("Make sure the coure you would like to edit IS CRRENTLY IN THE COURSE SYSTEM ...");
		System.out.print("\nPlease enter the COURSE ID you would like to edit (EXACTLY and CORRECTLY): ");
		String editCourse = scan.nextLine();
		System.out.print("Please enter the COURSE SECTION # that you would like to display (EXACTLY and CORRECTLY): ");
		String editSectionString = scan.nextLine();
		int editSection = Integer.parseInt(editSectionString);
		int editIndex = -1;
		
		// find the edit course
		for (int c = 0; c < courseList.size(); c++) {
			if (courseList.get(c).getCourseId().equals(editCourse) && courseList.get(c).getCourseSection() == editSection) {
				editIndex = c;
			}
		} // for c
		
		// check the validity
		while (editIndex == -1) {
			System.out.print("\nNo course found. Please enter a valid course ID: ");
			editCourse = scan.nextLine();
			System.out.print("Please enter a valid section #: ");
			editSectionString = scan.nextLine();
			editSection = Integer.parseInt(editSectionString);
			for (int c = 0; c < courseList.size(); c++) {
				if (courseList.get(c).getCourseId().equals(editCourse) && courseList.get(c).getCourseSection() == editSection) {
					editIndex = c;
				}
			} // for c
		} // while
		
		// prompt the user for the attribute to be edited
		System.out.println("\n------ Which thing would you like to edit ------");
		System.out.println("* Enter 1 for MAXIMUM STUDENTS #");
		System.out.println("* Enter 2 for CURRENT STUDENTS #");
		System.out.println("* Enter 3 for LIST OF NAME");
		System.out.println("* Enter 4 for COURSE INSTRUCTOR");
		System.out.println("* Enter 5 for COURSE SECTION #");
		System.out.println("* Enter 6 for COURSE LOCATION");
		int choice = Integer.parseInt(scan.nextLine());;
		while (choice > 6) {
			System.out.println("\nInvalid input. Please choose again.");
		}
		
		// change the course info based on the user's choice
		if (choice == 1) {			
			// change the maximum students number
			System.out.print("Please enter the MAXIMUM STUDENTS # you would like to update to: ");
			int update = Integer.parseInt(scan.nextLine());
			// check the validity
			if (update < courseList.get(editIndex).getCurrentStudents()) {
				System.out.println("\nSorry, the current students # is more than your input maximum students #. Edit failures.");
			}
			else {
				courseList.get(editIndex).setMaxStudents(update);
				System.out.println("\nThe maximum students number information has been updated.");
			}
		}
		
		else if (choice == 2) {
			// change the current students number
			System.out.print("Please enter the CURRENT STUDENTS # you would like to update to: ");
			int update = Integer.parseInt(scan.nextLine());
			// check the validity
			if (update > courseList.get(editIndex).getMaxStudents()) {
				System.out.println("\nThe updated curreent students # exceeds the maximum students #. Edit failures.");
			}
			courseList.get(editIndex).setCurrentStudents(update);
		}
		
		else if (choice == 3) {
			// change the list of names
			System.out.print("Which way to you want to edit the STUDENT'S NAME LIST? (1 for ADD / 2 for REMOVE) ");
			int aOrR = Integer.parseInt(scan.nextLine());
			// check the validity
			while (aOrR != 1 && aOrR != 2) {
				System.out.println("\nInvalid input. Please enter again (1 for add / 2 for remove) ");
				aOrR = Integer.parseInt(scan.nextLine());
			} // while
			
			if (aOrR == 1) {
				// add a student
				// prompt the user for the student's name
				System.out.print("Please enter the student's first name: ");
				String first = scan.nextLine();
				System.out.print("Please enter the student's last name: ");
				String last = scan.nextLine();
				String name = first + " " + last;
				// check the student's validity
				if (studentNameList.indexOf(name) < 0) {
					// invalid name
					System.out.println("\nStudent's name NOT FOUND. Please first REGISTER this student into system.");
				}
				else {
					// valid name
					// check if the course is already full
					if (courseList.get(editIndex).getCurrentStudents() == courseList.get(editIndex).getMaxStudents()) {
						// course is full, cannot add
						System.out.println("\nSorry, the current course is already full. You can not add a new student in it.");
					}
					else {
						// course not full, add
						int studentIndex = studentNameList.indexOf(name);
						courseList.get(editIndex).getStudentObject().add(studentList.get(studentIndex));
						courseList.get(editIndex).getStudentName().add(studentNameList.get(studentIndex));
						// add this course to the student's course list
						studentList.get(studentIndex).getRegisteredCourse().add(courseList.get(editIndex).getCourseName());
						// add the students number
						courseList.get(editIndex).addStudents();
						System.out.println("\nCongratulations! The student has been succefully added to the course.");
					}
				}
			} // add
			
			else {
				// remove a student
				// prompt the user for the student's name
				System.out.print("Please enter the student's first name: ");
				String first = scan.nextLine();
				System.out.print("Please enter the student's last name: ");
				String last = scan.nextLine();
				String name = first + " " + last;
				// check the validity
				if (courseList.get(editIndex).getStudentName() == null) {
					System.out.println("\nThere is no student enrolled in this course currently. Please double check the course information.");
				}
				else {
					if (courseList.get(editIndex).getStudentName().indexOf(name) < 0){
						System.out.println("\nThe student is actually not in this course. You do not have to remove him/her.");
					}
					else {
						// check the student's validity
						if (studentNameList.indexOf(name) < 0) {
							// invalid name
							System.out.println("\nStudent's name NOT FOUND. Please first REGISTER this student into system.");
						}
						else {
							// valid name
							int studentIndex = studentNameList.indexOf(name);
							int removeIndex = courseList.get(editIndex).getStudentName().indexOf(name);
							courseList.get(editIndex).getStudentName().remove(removeIndex);
							// remove this course from the student's course list
							studentList.get(studentIndex).getRegisteredCourse().remove(courseList.get(editIndex).getCourseName());
							// minus students number
							courseList.get(editIndex).minusStudents();
							System.out.println("\nThe student has been removed.");
						}					
					}
				}				
			} // remove
		}
		
		else if (choice == 4) {
			// change the course instructor
			System.out.print("Please enter the COURSE INSTRUCTOR you would like to update to: ");
			String update = scan.nextLine();
			courseList.get(editIndex).setCourseInstructor(update);
			System.out.println("\nThe instructor information has been updated.");
		}
		
		else if (choice == 5) {
			// change the course section #
			System.out.print("Please enter the COURSE SECTION # you would like to update to: ");
			int update = Integer.parseInt(scan.nextLine());
			courseList.get(editIndex).setCourseSection(update);
			System.out.println("\nThe course section information has been updated.");
		}
		
		else if (choice == 6) {
			// change the course location
			System.out.print("Please enter the COURSE LOCATION you would like to update to: ");
			String update = scan.nextLine();
			courseList.get(editIndex).setLocation(update);
			System.out.println("\nThe course location information has been updated.");
		}
		
	} // editCourse
	
	/**
	 * Display the information of a given course.
	 * @param courseList An array list of Course objects of current course information.
	 */
	public void display(ArrayList<Course> courseList) {
		
		// prompt the user for the course to be displayed
		System.out.print("Please enter the COURSE ID that you would like to display (EXACTLY and CORRECTLY): ");
		String displayID = scan.nextLine();
		System.out.print("Please enter the COURSE SECTION # that you would like to display (EXACTLY and CORRECTLY): ");
		String displaySectionString = scan.nextLine();
		int displaySection = Integer.parseInt(displaySectionString);
		int displayIndex = 0;
		
		// find the course by its ID
		for (Course currentCourse: courseList) {
			if (currentCourse.getCourseId().equals(displayID) && currentCourse.getCourseSection() == displaySection) {
				displayIndex = courseList.indexOf(currentCourse);
			}
		} // currentCourse
		
		// display the information
		System.out.println("\n----------COURSE INFO----------");
		System.out.println("* COURSE NAME       : " + courseList.get(displayIndex).getCourseName());
		System.out.println("* COURSE ID         : " + courseList.get(displayIndex).getCourseId());
		System.out.println("* MAXIMUM STUDENTS #: " + courseList.get(displayIndex).getMaxStudents());
		if (courseList.get(displayIndex).getStudentName() == null) {
			System.out.println("* LIST OF NAME      : <currently no student registed yet>");
		}
		else 
			System.out.println("* LIST OF NAME      : " + courseList.get(displayIndex).getStudentName().toString());
		System.out.println("* COURSE INSTRUCTOR : " + courseList.get(displayIndex).getCourseInstructor());
		System.out.println("* COURSE SECTION #  : " + courseList.get(displayIndex).getCourseSection());
		System.out.println("* LOCATION          : " + courseList.get(displayIndex).getLocation());
	
	} // display
	
	/**
	 * Register a student by entering basic student's information without assigning to course.
	 * @param studentList An array list of Student objects of current students.
	 * @return A Student object that has just been registered.
	 */
	public Student registerStudent(ArrayList<Student> studentList) {
		// prompt the user for basic information
		System.out.print("* Please enter the student's USERNAME (example: ms): ");
		String userName = scan.nextLine();
		// check validity
		int exist = 0;
		int existStud = 0;
		while (exist == 0) {
			for (Student student: studentList) {
				if (student.getUserName().equals(userName)) {
					// exist, invalid
					existStud++;
				}
			} // student
			if (existStud == 0) {
				// not exist, valid
				exist ++;
			}
			else {
				System.out.print("Sorry, this username has already existed. Please try another one: ");
				userName = scan.nextLine();
				existStud = 0;
			}
		} // while
		System.out.print("* Please enter the student's PASSWORD (example: ms001): ");
		String password = scan.nextLine();
		System.out.print("* Please enter the student's FIRST NAME (example: Mary): ");
		String firstName = scan.nextLine();
		System.out.print("* Please enter the student's LAST NAME (example: Smith): ");
		String lastName = scan.nextLine();
		// create a new student object
		Student registered = new Student (userName, password, firstName, lastName);
		// add the student to the list
		studentList.add(registered);
		System.out.println("\nCongratulations! The student has been registered.");
		return registered;
	} // studentList	
	
	@Override
	/**
	 * View all courses information, including student's basic information.
	 * @param An array list of course objects.
	 */
	public void viewAll(ArrayList<Course> courseList) {
		super.viewAll(courseList);
		// use a for loop to iterate through course list
		for (Course course: courseList) {
			System.out.println("* COURSE NAME: " + course.getCourseName());
			System.out.println("* COURSE ID: " + course.getCourseId());
			System.out.println("* MAX #: " + course.getMaxStudents());
			System.out.println("* CURRENT #: " + course.getCurrentStudents());
			System.out.println("* COURSE INSTRUCTOR: " + course.getCourseInstructor());
			System.out.println("* LOCATION: " + course.getLocation());
			// use a for loop to iterate through students
			// check the current number
			if (course.getCurrentStudents() == 0) {
				System.out.println("This course currently has no student enrolled.");
			}
			else {
				System.out.println("* STUDENT INFO: ");
				for (Student student: course.getStudentObject()) {
					System.out.println(student.getFirstName() + " " + student.getLastName() + "     " + student.getUserName());
				} // student
			}
			System.out.println("\n*********************** press ENTER to view next ... **********************");
			String nextCourse = scan.nextLine();
		} // course
	} // viewAll
	
	/**
	 * View all courses (course name and section #) that are full.
	 * @param courseList An array list of Course objects.
	 */
	public void viewFull(ArrayList<Course> courseList) {
		System.out.println("---------- FULL COURSES LIST ----------\n");
		// create a variable in case no course is full
		int fullNum = 0;
		// use a for loop to iterate through the course list
		for (Course currentCourse: courseList) {
			if (currentCourse.getMaxStudents() == currentCourse.getCurrentStudents()) {
				// this course is full
				System.out.println(currentCourse.getCourseName() + " (Section #" + currentCourse.getCourseSection() + ")");
				fullNum++;
			}
		} // currentCourse
		// if no course if full
		if (fullNum == 0) {
			System.out.println("Currently no course is full.");
		}
	} // viewFull
	
	/**
	 * Write a file to list all full courses.
	 * @param courseList An array list of Course objects.
	 */
	public void fileFull(ArrayList<Course> courseList) {
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter("FullCourse.txt"));
			file.write("----- Full Courses are Listed Below -----");
			file.append("\nps: empty if no course is currently full\n");
			// use a for loop to iterate through the course list
			for (Course currentCourse: courseList) {
				if (currentCourse.getMaxStudents() == currentCourse.getCurrentStudents()) {
					// this course is full
					// append this course to the text file
					file.append("\n* " + currentCourse.getCourseName() + "(Section #" + currentCourse.getCourseSection() + ")");
				}
			} // currentCourse
			// close the file
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // IOException
	} // fileFull
	
	/**
	 * View students' names of a specific course.
	 * @param courseList An array list of Course objects.
	 */
	public void viewStudents(ArrayList<Course> courseList) {
		// prompt the user to enter a specific course
		System.out.print("Please enter the COURSE ID: ");
		String viewId = scan.nextLine();
		System.out.print("Please enter the COURSE SECTION: ");
		int viewSection = Integer.parseInt(scan.nextLine());
		// find the course
		for (Course course: courseList) {
			if (course.getCourseId().equals(viewId) && course.getCourseSection() == viewSection) {
				// check the number 
				if (course.getCurrentStudents() == 0) {
					System.out.println("\nNo student registered in this course yet.");
				}
				else {
					System.out.println("\nStudents enrolled in this course are listed as follows: ");
					System.out.println(course.getStudentName().toString());
				}
			}
		} // course
	} // viewStudents

	/**
	 * View all courses a given student has registered.
	 * @param studentList An array list of Student objects of current existed students.
	 */
	public void courseOfStudent(ArrayList<Student> studentList) {
		// prompt the user for a student
		System.out.print("Please enter the STUDENT'S ID (EXACTLY and CORRECTLY): ");
		String stuId = scan.nextLine();
		// set a variable in case invalid input
		int checkStudent = 0;
		// use a for loop to iterate and find the student
		for (Student student: studentList) {
//			System.out.println(studentList.size());
			if (student.getUserName().equals(stuId)) {
				// find the student
				checkStudent++;
				// check if the student currently has course
				if (student.getRegisteredCourse() == null) {
					// the student currently has no course
					System.out.println("\nThis student currently has no course registered yet.");
				}
				else {
					// has course
					System.out.println("\n* Courses registered are follow: ");
					System.out.println(student.getRegisteredCourse().toString());
				}
			}
		} // student
		if (checkStudent == 0) {
			// invalid input
			System.out.println("\nNo student found! Please check the student ID.");
		}
	} // courseOfStudent
	
	/**
	 * Sort courses based on current students number.
	 * @param courseList An array list of course objects.
	 */
	public void sortCourse(ArrayList<Course> courseList) {
		// sort the course by current students number
		Collections.sort(courseList);
		System.out.println("\n------ Sorted course names (based on current students #) are listed below ------");
		System.out.println();
		for (Course course: courseList) {
			System.out.println("* " + course.getCourseName() + " #" + course.getCurrentStudents());
		}
	} // sortCourse
	

} // class
