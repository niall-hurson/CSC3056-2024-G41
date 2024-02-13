package tests;

import model.User;
import utils.TestUtils;


public class UserTest {
	
	 public static void main(String[] args) {
	        // Call the test method(s) here
	        testUserConstructor();
	    }

	public static void testUserConstructor() {
		//1 Setup
		String test_username = "mike";
		String test_password = "my_passwrd";
		String test_first_name = "Mike";
		String test_last_name = "Smith";
		String test_mobile_number = "07771234567";
		
		//2 Exercise, run the object under test (constructor)
		User testUser = new User(test_username, test_password, test_first_name, test_last_name, test_mobile_number);
		
		//3 Verify (Assert)
		// username
		if (testUser.getUsername() == test_username)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC1 -getUsername passed "
					+ TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC1 failed: username did not match"+ 
		TestUtils.TEXT_COLOR_RESET);
		
		//password
		if (testUser.getPassword() == test_password)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC2 -getPassword passed" + 
		TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC1 failed: password did not match"+ 
		TestUtils.TEXT_COLOR_RESET);

		//first name
		if (testUser.getFirst_name() == test_first_name)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC3 -getFirstName passed "
					+ TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC3 failed: getFirstName did not match"+ 
		TestUtils.TEXT_COLOR_RESET);
		
		
		//last name
		if (testUser.getLast_name() == test_last_name)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC4 -getLastName passed "
					+ TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC4 failed: getLastName did not match"+ 
		TestUtils.TEXT_COLOR_RESET);
		
		//mobile number
		if (testUser.getMobile_number() == test_mobile_number)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC5 -getMobileNumber passed "
					+ TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC5 failed: getMobileNumber did not match"+ 
		TestUtils.TEXT_COLOR_RESET);
		
		//TODO 4
				
		//using asserts
		assert testUser.getUsername() == test_username;
		assert testUser.getPassword() == test_password;
		assert testUser.getFirst_name() == test_first_name;
		assert testUser.getLast_name() == test_last_name;
		assert testUser.getMobile_number() == test_mobile_number;

		System.out.println("All Java Assertions in the test suite passed (none failed)");



			
			


	}

}
