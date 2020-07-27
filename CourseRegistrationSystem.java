import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A program of Course Registration System that allows user (Admin / Student) to manage or register courses and get information of it.
 * @author Xintong Zhu
 * @version 0.1
 */

public class CourseRegistrationSystem {
	


	/**
	 * A main method to interact with users in order for them to manage or register in classes.
	 * @param args Any argument provided while running the program.
	 * @throws IOException 
	 */
	public static void main (String[] args) throws IOException {
		 
		System.out.println("*** Please make sure to EXIT the system by CHOOSING 'EXIT' OPTION in order to save your current information ***");
		System.out.println("*** Please pay attention to every input and try NOT to exit system ABNORMALLY ***");
		System.out.println("*** Information may lose due to ABNORMAL SYSTEM SHUT DOWN ***\n");
		
		// information initialization starts here ...

		// create a scanner object
		Scanner input = new Scanner (System.in);

		// create an array list for Course object data
		ArrayList<Course> courseList = new ArrayList<Course>();
		// create an array list for Student object data
		ArrayList<Student> studentList = new ArrayList<Student>();
		// create an array list for students' names
		ArrayList<String> studentNameList = new ArrayList<String>();
		
		// information reading process starts here ...
		
		// check if this is the first time to open the course information
		try {
			
			// if this is not the first time, then use the existing .ser file
			
			// receive bytes from a file
			FileInputStream fileInputStream = new FileInputStream ("CourseInfo.ser");
			// deserialize and reconstruct the data into an object
			ObjectInputStream objectInputStream = new ObjectInputStream (fileInputStream);
			// read the serialization file and cast the object to Course
			courseList = (ArrayList<Course>) objectInputStream.readObject();
			// close the stream
			fileInputStream.close();
			objectInputStream.close();
			
			System.out.println("* Welcom back to the Course Registration System!");
			System.out.println("* Your previous course registration record has been successfully kept! <Deserialization Completed>");
			
		} catch(IOException ioe) {
			
			// if this is the first time, then open and read the .csv file
			System.out.println("* Welcome to the Course Registration System!");
			
			try {
				
				// create a scanner object and open the .csv file
				Scanner scan = new Scanner (new File ("MyUniversityCourses.csv"));
				
				// create an array list to read store the data
				ArrayList<String> rawData = new ArrayList<String>();
				while (scan.hasNextLine()) {
					rawData.add(scan.nextLine());
				} // while hasNextLine
				
				// transform the raw string array list to Course object list
				for (int r = 1; r < rawData.size(); r++ ) {
					
					String currentRawData = rawData.get(r);
					// split the current line by comma
					String[] dataList = currentRawData.split(",");
					// create the course object (with the student list null at first)
					Course currentCourse = new Course(dataList[0], dataList[1], Integer.parseInt(dataList[2]), Integer.parseInt(dataList[3]), dataList[5], Integer.parseInt(dataList[6]), dataList[7]);
					// add this course to the course list
					courseList.add(currentCourse);
					
				} // for r
				System.out.println("* Information initialization completed!");
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} // FileNotFoundException			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  // ClassNotFoundException
		
		// check if there is any student object created before
		try {
			
			// if not, read the student .ser file for existed students objects
			// receive bytes from a file
			FileInputStream fileInputStream = new FileInputStream ("StudentInfo.ser");
			// deserialize and reconstruct the data into an object
			ObjectInputStream objectInputStream = new ObjectInputStream (fileInputStream);
			// read the serialization file and cast the object to Course
			try {
				ArrayList<Student> studentListTry= (ArrayList<Student>) objectInputStream.readObject();
				for (Student student: studentListTry) {
					Student studentAdd = new Student(student.getUserName(), student.getPassword(), student.getFirstName(), student.getLastName(), student.getRegisteredCourse());
					studentList.add(studentAdd);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// close the stream
			fileInputStream.close();
			objectInputStream.close();
			// create the studentNameList
			for (Student student: studentList) {
				studentNameList.add(student.getFirstName() + " " + student.getLastName());
			} // student
			System.out.println("* Previous students' registration information has been succesfully kept! <Deserialization Completed>");
			
		} catch(IOException ioe) {
			
			// if this is the first time
			System.out.println("* System shows that there is no students registration in this system. ");
			System.out.println("* You need to first login as an Admin to register students.");
			
		}
		
		
		
		
		
		// main program interactivity starts here ...
//		System.out.println(courseList.get(0).getCourseName());
		// set a variable to control the whole program
		int identityChoice = 0;
		
		while (identityChoice != 3) {
			
			// IDENTITY MENU ...
			System.out.println("\n==============================================================================================");
			System.out.println("Please choose your IDENTITY ...");
			System.out.println("- ps: if this is your first time in this system, you need to first login as Admin to register students -");
			System.out.println("\n* Enter 1 for Admin");
			System.out.println("* Enter 2 for Student");
			System.out.println("* Enter 3 for Exit");
			identityChoice = Integer.parseInt(input.nextLine());
			System.out.println("\n==============================================================================================");
			
			// ADMIN IDENTITY ...
			if (identityChoice == 1) {
				
				// LOGIN ...
				System.out.print("Please enter your (default) ADMIN USERNAME: ");
				String inputUsername = input.nextLine();
				System.out.print("Please enter your (default) ADMIN PASSWORD: ");
				String inputPassword = input.nextLine();
				if (inputUsername.equals("Admin") && inputPassword.equals("Admin001")) {
					
					// LOGIN SUCCESS ...
					// create the Admin object
					Admin admin = new Admin ("Admin", "Admin001");
					System.out.println("Login Success!");
					
					// ADMIN MENU ...					
					// set a variable to control the Admin menu
					int adminChoice = -1;
					while (adminChoice != 0) {
						System.out.println();
						System.out.println("============================== COURSE MANAGEMENT ==============================");
						System.out.println("1  Create a new course");
						System.out.println("2  Delete a course");
						System.out.println("3  Edit a course");
						System.out.println("4  Display information for a given course");
						System.out.println("5  Register a student");
						System.out.println("=================================== REPORTS ===================================");
						System.out.println("6  View all courses");
						System.out.println("7  View all courses that are FULL");
						System.out.println("8  Write to a file the list of course that are full");
						System.out.println("9  View the names of the students that are registered in a specific course");
						System.out.println("10 View the list of courses that a given student is registered in");
						System.out.println("11 Sort the courses based on the current number of students registered");
						System.out.println("\n0  Exit");
						System.out.print("\n* Please enter your option: ");
						
						adminChoice = Integer.parseInt(input.nextLine());
						
						// CREATE ...
						if (adminChoice == 1) {
							System.out.println("\n*********************************************************************************\n");
							// call the instance method
							admin.createCourse(courseList);
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // 1
						
						// DELETE ...
						else if (adminChoice == 2) {
							System.out.println("\n*********************************************************************************\n");
							// call the instance method
							admin.deleteCourse(courseList);
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // 2		
						
						// EDIT ...
						else if (adminChoice == 3) {
							System.out.println("\n*********************************************************************************\n");
							// call the instance method
							admin.editCourse(courseList, studentNameList, studentList);
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // 3
												
						// DISPLAY ...
						else if (adminChoice == 4) {
							System.out.println("\n*********************************************************************************\n");
							// call the instance method
							admin.display(courseList);
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // 4
												
						// REGISTER ...
						else if (adminChoice == 5) {
							System.out.println("\n*********************************************************************************\n");
							// call the instance method
							Student registered = admin.registerStudent(studentList);
							// add the student's name
							studentNameList.add(registered.getFirstName() + " " + registered.getLastName());
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // 5
												
						// VIEW ALL ...
						else if (adminChoice == 6) {
							System.out.println("\n*********************************************************************************\n");
							// call the instance method
							admin.viewAll(courseList);
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // 6						
						
						// VIEW FULL ...
						else if (adminChoice == 7) {
							System.out.println("\n*********************************************************************************\n");
							// call the instance method
							admin.viewFull(courseList);
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // 7
												
						// WRITE ...
						else if (adminChoice == 8) {
							System.out.println("\n*********************************************************************************\n");
							// call the instance method
							admin.fileFull(courseList);
							System.out.println("A text file has been generated (in this project)!");
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // 8
												
						// VIEW STUDENTS ...
						else if (adminChoice == 9) {
							System.out.println("\n*********************************************************************************\n");
							// call the instance method
							admin.viewStudents(courseList);
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // 9
												
						// VIEW COURSES ...
						else if (adminChoice == 10) {
							System.out.println("\n*********************************************************************************\n");
							// call the instance method
							admin.courseOfStudent(studentList);
//							System.out.println(studentNameList.toString());
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // 10
						
						// SORT COURSES ...
						else if (adminChoice == 11) {
							System.out.println("\n*********************************************************************************\n");
							// call the instance method
							admin.sortCourse(courseList);
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // 10						
						
						// EXIT ...
						else if (adminChoice == 0) {
							// call the parent class
							admin.exit();
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // exit						
						
						// INVALID ...
						else {
							System.out.println("\nInvalid option. Please read the menu and enter again.");
							System.out.println("\nPress ENTER to continue ...");
							String go = input.nextLine();
						} // invalid						
						
					} // AdminChoice					
										
				} // login success
				
				else {
					// LOGIN FAILURE ...
					System.out.println("\nLogin Failure. Please check your username or password.");
					System.out.println("The system would lead you back to the IDENTITY menu");
					System.out.println("\nPress ENTER to continue ...");
					String go = input.nextLine();
				} // login failure
				
			} // Admin
			
			
			
			
			// STUDENT IDENTITY ...
			else if (identityChoice == 2) {
				
				// LOGIN ...
				System.out.print("Please enter your (registered) STUDENT USERNAME: ");
				String inputUsername = input.nextLine();
				System.out.print("Please enter your (registered) STUDENT PASSWORD: ");
				String inputPassword = input.nextLine();
				
				// CHECK ...
				int studentIndex = -1;
				if (studentList.size() == 0) {
					// no student registered yet
					System.out.println("\nNo student registered yet. You should first login as Admin to register students.");
					System.out.println("The system would lead you back to the IDENTITY menu");
					System.out.println("\nPress ENTER to continue ...");
					String go = input.nextLine();
				}
				else {
					// has students
//					System.out.println(studentList.toString());
					for (Student student: studentList) {
						if (student.getUserName().equals(inputUsername) && student.getPassword().equals(inputPassword)) {
							// find the student
							studentIndex = studentList.indexOf(student);							
						}						
					} // student
					if (studentIndex == -1) {
						// LOGIN FAILURE ...
						System.out.println("\nLogin Failure. Please check your username or password.");
						System.out.println("The system would lead you back to the IDENTITY menu");
						System.out.println("\nPress ENTER to continue ...");
						String go = input.nextLine();
					} // login failure
					else {
						
						// LOGIN SUCCESS ...
						
						// STUDENT MENU ...					
						// set a variable to control the student menu
						int studentChoice = -1;
						while (studentChoice != 0) {
							
							System.out.println();
							System.out.println("============================== COURSE MANAGEMENT ==============================");
							System.out.println("1  View all courses");
							System.out.println("2  View all courses that are not full");
							System.out.println("3  Register in a course");
							System.out.println("4  Withdraw from a course");
							System.out.println("5  View all courses that you are registered in");
							System.out.println("\n0  Exit");
							System.out.print("\n* Please enter your option: ");
							
							studentChoice = Integer.parseInt(input.nextLine());
							
							// VIEW ALL ...
							if (studentChoice == 1) {
								System.out.println("\n*********************************************************************************\n");
								// call the instance method
								studentList.get(studentIndex).viewAll(courseList);
								System.out.println("\nPress ENTER to continue ...");
								String go = input.nextLine();
							} // 1
							
							
							// VIEW NOT FULL ...
							else if (studentChoice == 2) {
								System.out.println("\n*********************************************************************************\n");
								// call the instance method
								studentList.get(studentIndex).viewNotFull(courseList);
								System.out.println("\nPress ENTER to continue ...");
								String go = input.nextLine();
							} // 2
							
							
							// REGISTER ...
							else if (studentChoice == 3) {
								System.out.println("\n*********************************************************************************\n");
								// call the instance method
//								System.out.println(studentIndex);
//								System.out.println(courseList.toString());
								studentList.get(studentIndex).registerInCourse(courseList, studentList);
								System.out.println("\nPress ENTER to continue ...");
								String go = input.nextLine();
							} // 3
							
							
							// WITHDRAW ...
							else if (studentChoice == 4) {
								System.out.println("\n*********************************************************************************\n");
								// call the instance method
								studentList.get(studentIndex).withdraw(courseList, studentList);
								System.out.println("\nPress ENTER to continue ...");
								String go = input.nextLine();
							} // 4
							
							
							// VIEW REGISTERED ...
							else if (studentChoice == 5) {
								System.out.println("\n*********************************************************************************\n");
								// call the instance method
								studentList.get(studentIndex).reviewAllRegistered();
								System.out.println("\nPress ENTER to continue ...");
								String go = input.nextLine();
							} // 5
							
							// EXIT ...
							else if (studentChoice == 0) {
								studentList.get(studentIndex).exit();
								System.out.println("\nPress ENTER to continue ...");
								String go = input.nextLine();
							} // 0							
							
						} // student menu						
						
					} // login success					
					
				} // has student				
				
			} // Student
			
			
			
			
			// INVALID ...
			else if (identityChoice > 3) {
				System.out.println("\nInvalid input. Please read the instruction and enter again.");
			} // invalid
			
			
			
			// EXIT ...
			else if (identityChoice == 3) {
				System.out.println("\nCongratulations! Your information would be saved.");
				System.out.println("Hope you have enjoyed this system. Wish you a great semester :)");
			} // exit
			
			
		} // main program
		
		
		
		
		// information saved starts here ...
//		try {
			// receive bytes from a file
			FileOutputStream fileOutputStream1 = new FileOutputStream ("CourseInfo.ser");
			// deserialize and reconstruct the data into an object
			ObjectOutputStream objectOutputStream1 = new ObjectOutputStream (fileOutputStream1);
			// serialize the course information
			objectOutputStream1.writeObject(courseList);
			System.out.println("\n===================================================================================================");
			System.out.println("COURSE INFO SERIALIZATION COMPLETED");
			// close the stream
			fileOutputStream1.close();
			objectOutputStream1.close();
			// receive bytes from a file
			FileOutputStream fileOutputStream2 = new FileOutputStream ("StudentInfo.ser");
			// deserialize and reconstruct the data into an object
			ObjectOutputStream objectOutputStream2 = new ObjectOutputStream (fileOutputStream2);
			// serialize the student information
			objectOutputStream2.writeObject(studentList);
			System.out.println("STUDENT INFO SERIALIZATION COMPLETED");
			// close the stream
			fileOutputStream2.close();
			objectOutputStream2.close();

			
//		}	catch(IOException ioe) {
//			System.out.println("\nSerialization error.");
//		}
		
			
		
		
	} // main

} // class
