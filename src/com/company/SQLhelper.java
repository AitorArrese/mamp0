package com.company;

import java.sql.*;

public class SQLhelper {
    private static String baseURL ="jdbc:mysql://127.0.0.1:8889/";
    private static String schemaName = "DataStructuresYay";
    private static String fullURL = baseURL + schemaName;
    private static String username = "Aitor";
    private static String password = "saint123";
    private static Connection connection = null;

    String createTransactions = "CREATE TABLE IF NOT EXISTS `DataStructuresYay`.`Transactions` (\n" +
            "  `TransactionID` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `BookID` INT NOT NULL,\n" +
            "  `StudentID` INT NOT NULL,\n" +
            "  `Action` INT NOT NULL,\n" +
            "  PRIMARY KEY (`TransactionID`),\n" +
            "  UNIQUE INDEX `TransactionID_UNIQUE` (`TransactionID` ASC),\n" +
            "  UNIQUE INDEX `BookID_UNIQUE` (`BookID` ASC),\n" +
            "  UNIQUE INDEX `StudentID_UNIQUE` (`StudentID` ASC),\n" +
            "  CONSTRAINT `BookID`\n" +
            "    FOREIGN KEY (`BookID`)\n" +
            "    REFERENCES `DataStructuresYay`.`BookList` (`bookID`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `StudentID`\n" +
            "    FOREIGN KEY (`StudentID`)\n" +
            "    REFERENCES `DataStructuresYay`.`Students` (`studentID`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION)\n" +
            "ENGINE = InnoDB;";
    String createStudents = "CREATE TABLE IF NOT EXISTS `DataStructuresYay`.`Students` (\n" +
            "  `studentID` INT NOT NULL,\n" +
            "  `firstName` VARCHAR(45) NOT NULL,\n" +
            "  `lastName` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`studentID`),\n" +
            "  UNIQUE INDEX `studentID_UNIQUE` (`studentID` ASC))\n" +
            "ENGINE = InnoDB;";
    String createBookList = "CREATE TABLE IF NOT EXISTS `DataStructuresYay`.`BookList` (\n" +
            "  `bookID` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `bookName` VARCHAR(45) NOT NULL,\n" +
            "  `Taken` VARCHAR(45) NOT NULL,\n" +
            "  `Genre` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`bookID`),\n" +
            "  UNIQUE INDEX `bookID_UNIQUE` (`bookID` ASC),\n" +
            "  UNIQUE INDEX `bookName_UNIQUE` (`bookName` ASC))\n" +
            "ENGINE = InnoDB;";

    public String addStudent(String firstName1, String lastName1) throws SQLException
    {
        connection = DriverManager.getConnection(baseURL,username,password);
        Statement stmt = connection.createStatement();
        String sqlCommand = "INSERT INTO Students (firstName, lastName) VALUES ('"+firstName1+"', '" + lastName1 +"');";
        return sqlCommand;
    }

    public String addBook(String bookName, String Genre, int taken) throws SQLException
    {
        connection = DriverManager.getConnection(baseURL,username,password);
        Statement stmt = connection.createStatement();
        String sqlCommand = "INSERT INTO BookList (bookName, Taken, Genre) VALUES ('"+bookName+"', '" + taken +"', '"+Genre+"');";
        return sqlCommand;
    }

    public void addTransaction(int bookID, int studentID, int action) throws SQLException
    {
        connection = DriverManager.getConnection(fullURL,username,password);
        Statement stmt = connection.createStatement();
        stmt.execute("INSERT INTO Transactions (BookID, StudentID, Action) VALUES ('"+bookID+"', '" + studentID +"', '"+action+"');");
        stmt.execute("UPDATE BookList SET Taken = '\"+action+\"' WHERE bookID = '\"+bookID+\"';");
    }

    public int getBookIDAction(int bookID) throws SQLException {
        connection = DriverManager.getConnection(fullURL,username,password);
        Statement stmt = connection.createStatement();
        String quiry = "select bookID, bookName, Taken from BookList";
        ResultSet rs = stmt.executeQuery(quiry);
        while(rs.next())
        {
            if(rs.getString("bookID").equals(bookID))
            {
                return rs.getInt("Taken");
            }
        }
        return 0;
    }
    public int getStudentID(String firstName, String lastName) throws SQLException {
        connection = DriverManager.getConnection(fullURL,username,password);
        Statement stmt = connection.createStatement();
        String quiry = "select StudentID, firstName, LastName from Students";
        ResultSet rs = stmt.executeQuery(quiry);
        while(rs.next())
        {
            if(rs.getString("firstName").equals(firstName) && rs.getString("lastName").equals(lastName))
            {
                return rs.getInt("StudentID");
            }
        }
        return 0;
    }


}
