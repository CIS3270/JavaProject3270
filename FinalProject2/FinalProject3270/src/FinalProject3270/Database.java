package FinalProject3270;

import java.sql.*;
import java.util.ArrayList;

/*
 * Database class that allows for access to the database to check password, usernames, 
 * flighs,store new users and access the databes when needed
 */
public class Database {

	/****************************
	 * Method to verify the user credentials for logging into the system and
	 * accessing their account This is done by loading all the users names and
	 * passwords Then matching the user name passed in to the array. If it is
	 * found then the corresponding password in the password array is checked
	 * returns true of found false if not
	 */
	public static boolean connectDb(String user, String pwd)
			throws ClassNotFoundException, SQLException {
		// load the driver from the jar file
		boolean valid = false;
		
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver Loaded");

		// Create a variable to connect to a database and print it out to make
		// sure there is a connection
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/Project", "root", "root");
		System.out.println(connection);
		// prepare query that will access information in the database
		PreparedStatement statement2 = connection
				.prepareStatement("select username, password from user ");
		ResultSet result = statement2.executeQuery();

		// arrays to store the userIDs and Passwords
		ArrayList<String> users = new ArrayList<String>();
		ArrayList<String> pwds = new ArrayList<String>();
		// adds the user id and passwords to the arrays
		while (result.next()) {
			users.add(result.getString(1));
			pwds.add(result.getString(2));
		}
		// all the user names and password have been obtained now we search
		// the list for the user name entered to find a match using the findUser
		// method.
		int found = findUser(users, user);

		// if the user was found the found variable is the index of the
		// corresponding password
		// in the password array
		if (found >= 0) {
			if (pwds.get(found).equals(pwd)) {
				System.out.println("This password looks good to me let em in");
				valid= true;
			}
		}
		return valid;
	}

	/*
	 * A method to search for the user in the array using a for each loop to
	 * search through the array and returning the index number if found and -1
	 * if not found
	 */
	public static int findUser(ArrayList<String> user, String find) {
		for (String name : user) {
			if (name.equals(find)) {
				return user.indexOf(name);
			}
		}
		return -1;
	}
	
	/*connects to the database and pulls all the flight information from a particular
	 *airline and builds a flight object for all the flights of a particular airline
	 *and puts them into a array and return that array.
	 */
	public static ArrayList<Flight> gatherFlight( String flight) throws ClassNotFoundException, SQLException{
		//create a array to store the data
		ArrayList<Flight> list = new ArrayList<Flight>();
		// connect to the database
		//first load the driver 
		Class.forName("com.mysql.jdbc.Driver");
		
		//next create a variable to reference the connection with the driver manager to the database
		//and print it to verify its connection
		Connection connect = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/Project", "root", "root");
		
		System.out.println(connect);
		
		// prepare a query to access the information in database using the PreparedStatement class
		String query ="SELECT airline,flight_num, dest_city, depart_city,capacity, depart_date, arrival_date FROM flight WHERE airline = '"+flight+"'";
		PreparedStatement statement = connect.prepareStatement(query);
		
		// now to execute the statement, PreparedStatement, and store the results using a object from the ResultSet class
		ResultSet result = statement.executeQuery();
		
		//now pull the results to build a Flight object
		// and add the object to the list
		while(result.next()){
			Flight fly = new Flight(result.getString(1), result.getInt(2),result.getString(3),
					result.getString(4),result.getInt(5),result.getDate(6),result.getDate(7));
			
			list.add(fly);
		}
		//now return the list full of Flight objects from the list of flights that correspond to a particular airline 
		return list;
	}
	//return selected information from the combo box
	//this method adds a new user to the database
	
		public static void addUser(User user) throws SQLException, ClassNotFoundException{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Project", "root", "root");
			String insert = "INSERT into user(SSN,user_id,first_name,last_name,email,username,password,address,zip,state,security_question,security_answer )"
					+ "values (" + user.ssn +","+user.userId+", '"+user.firstName+"','"+ user.lastName +"' , '"+  user.email+"' , '"+ user.userName+"' , '"+ user.password+ "', '"+ user.address +"', "+ user.zipCode+", '" + user.state+"', '" + user.securityQuestion +"', '"+user.securityAnswer+"' )";
					
			System.out.println(insert);
		PreparedStatement statement = connect.prepareStatement(insert);
			statement.executeUpdate();
			
		}	
		public static User createUser(String username) throws SQLException{
			User user = new User();
			Connection connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Project", "root", "root");
			
			System.out.println(connect);
			
			// prepare a query to access the information in database using the PreparedStatement class
			String query ="SELECT SSN,user_id,first_name,last_name,email,username,password,address,zip,state,security_question,security_answer FROM user WHERE username  = '"+username+"'";
			PreparedStatement statement = connect.prepareStatement(query);
			
			// now to execute the statement, PreparedStatement, and store the results using a object from the ResultSet class
			ResultSet result = statement.executeQuery();
			
			//now pull the results to build a Flight object
			// and add the object to the list
			while(result.next()){
				 user = new User(result.getInt(1), result.getInt(2),result.getString(3), result.getString(4),result.getString(5),result.getString(6),result.getString(7),result.getString(8), result.getInt(9),result.getString(10),result.getString(11),result.getString(12) );
				
			
			}
			return user;
		}

public static void main(String[]args) throws ClassNotFoundException, SQLException{
	ArrayList<Flight> trial = new ArrayList<Flight>();
	
	trial = gatherFlight("delta");
	
	for(Flight f: trial){
		System.out.println(f);
	}
	Database me = new Database();
	
	
	
	
	User user = me.createUser("CuriousG");
	System.out.println(user.toString());
}

}
