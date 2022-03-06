package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static SQLhelper userHelper = new SQLhelper();
    static Scanner myObj = new Scanner(System.in);
    static boolean loginSuccesful;
    public static void main(String[] args) throws SQLException {
        // write your code here
        login();
        while(shouldContinue())
        {
            giveOptions();
        }
        }


    public static void login() throws SQLException {
          // Create a Scanner object
        while(!loginSuccesful)
        {
            System.out.println("Enter username: ");
            String username = myObj.nextLine();  // Read user input
            System.out.println("Enter password: ");
            String password = myObj.nextLine();  // Output user input
            if(userHelper.logIn(username,password))
            {
                System.out.println("Login Succesful!");
                loginSuccesful = true;
            }
            else
            {
                System.out.println("Login Failed");
            }
        }
    }
    public static void giveOptions()
    {
        System.out.println("Which of the following actions do you want to do?");
        System.out.println("1: add a Student");
        System.out.println("2: add a Book");
        System.out.println("3: add a Transaction");
        System.out.println("4: remove a Student");
        System.out.println("5: remove a Book");

        Scanner scanner2 = new Scanner(System.in);

        int choice = myObj.nextInt();
        if(choice == 1)
        {
            System.out.println("What is the Student's first name");
            String firstName = scanner2.nextLine();
            System.out.println("What is the Student's last name");
            scanner2 = new Scanner(System.in);
            String lastName = scanner2.nextLine();
            try {
                userHelper.addStudent(firstName,lastName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(choice == 2)
        {
            System.out.println("What is the Book's title: ");
            scanner2 = new Scanner(System.in);
            String bookName = scanner2.nextLine();
            System.out.println("What is the Book's genre: ");
            scanner2 = new Scanner(System.in);
            String genre = scanner2.nextLine();
            try {
                userHelper.addBook(bookName,genre, 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(choice == 3)
        {
            System.out.println("What is the Book's ID");
            int bookID = myObj.nextInt();
            System.out.println("What is the Student's ID");
            int studentID = myObj.nextInt();
            System.out.println("What is the transaction(0: take; 1: return): ");
            int action = myObj.nextInt();
            try {
                userHelper.addTransaction(bookID, studentID,action);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(choice == 4)
        {
            System.out.println("What is the Student's ID");
            int studentID = myObj.nextInt();
            try {
                userHelper.removeStudent(studentID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(choice == 5)
        {
            System.out.println("What is the Book's ID");
            int bookID = myObj.nextInt();
            try {
                userHelper.removeBook(bookID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Make sure to choose a valid int.");
        }

    }
    public static boolean shouldContinue()
    {
        System.out.println("Would you like to make a change to the database? (yes or no)");
        Scanner scanner2 = new Scanner(System.in);
        String response = scanner2.nextLine();
        if(response.equals("yes"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}

