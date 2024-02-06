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
		if (testUser.getUsername() == test_username)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC1 passed "
					+ TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC1 failed: username did not match"+ TestUtils.TEXT_COLOR_RESET);
		
		if (testUser.getPassword() == test_password)
			System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestConstructor-TC2 passed" + TestUtils.TEXT_COLOR_RESET);
		else
			System.out.println(TestUtils.TEXT_COLOR_RED + "TestConstructor-TC1 failed: username did not match"+ TestUtils.TEXT_COLOR_RESET);

 


	}

}
