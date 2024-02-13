package tests;

import java.util.Date;
import model.Account;
import utils.TestUtils;

public class AccountTest {

    public static void main(String[] args) {
        // Call the test method(s) here
        testAccountConstructor();
        testSetters();
    }

    public static void testAccountConstructor() {
        // 1. Setup
        String test_account_number = "123456789";
        String test_username = "fajita";
        String test_account_type = "standard";
        Date test_account_opening_date = new Date(); // Current date

        // 2. Exercise: create an instance of Account
        Account testAccount = new Account(test_account_number, test_username, test_account_type, test_account_opening_date);

        // 3. Verify (Assert)
        // Account number
        if (testAccount.getAccount_number() == test_account_number)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestAccountConstructor-TC1: Account number getter test passed" + 
        TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestAccountConstructor-TC1 failed: Account number did not match" + 
        TestUtils.TEXT_COLOR_RESET);

        // Username
        if (testAccount.getUsername_of_account_holder() == test_username)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestAccountConstructor-TC2: Username getter test passed" + 
        TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestAccountConstructor-TC2 failed: Username did not match" + 
        TestUtils.TEXT_COLOR_RESET);

        // Account type
        if (testAccount.getAccount_type() == test_account_type)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestAccountConstructor-TC3: Account type getter test passed" + 
        TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestAccountConstructor-TC3 failed: Account type did not match" + 
        TestUtils.TEXT_COLOR_RESET);

        //get account date
        if (testAccount.getAccount_opening_date().toString().equals( test_account_opening_date.toString()))
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestAccountConstructor-TC4: Account opening date getter test passed" + 
        TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestAccountConstructor-TC4 failed: Account opening date did not match" + 
        TestUtils.TEXT_COLOR_RESET);
    }
    
    public static void testSetters() {
    	// 1. Setup
        String test_account_number = "123456789";
        String test_username = "fajita";
        String test_account_type = "standard";
        Date test_account_opening_date = new Date(); // Current date

        // 2. Exercise: create an instance of Account
        Account testAccount = new Account(test_account_number, test_username, test_account_type, test_account_opening_date);

        // Verify (Assert)
        // Test setter methods
        System.out.println();
        testAccount.setAccount_number(test_account_number);
        if (testAccount.getAccount_number() == test_account_number)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestSetters-TC1: Account number setter test passed" + 
        TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestSetters-TC1 failed: Account number setter did not set the value correctly" +
        TestUtils.TEXT_COLOR_RESET);

        testAccount.setUsername_of_account_holder(test_username);
        if (testAccount.getUsername_of_account_holder() == test_username)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestSetters-TC2: Username setter test passed" + 
        TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestSetters-TC2 failed: Username setter did not set the value correctly" + 
        TestUtils.TEXT_COLOR_RESET);

        testAccount.setAccount_type(test_account_type);
        if (testAccount.getAccount_type() == test_account_type)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestSetters-TC3: Account type setter test passed" + 
        TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestSetters-TC3 failed: Account type setter did not set the value correctly" + 
        TestUtils.TEXT_COLOR_RESET);

        testAccount.setAccount_opening_date(test_account_opening_date);
        if (testAccount.getAccount_opening_date().toString().equals( test_account_opening_date.toString()))
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TestSetters-TC4: Account opening date setter test passed" + 
        TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TestSetters-TC4 failed: Account opening date setter did not set the value correctly" + 
        TestUtils.TEXT_COLOR_RESET);
    }

}
