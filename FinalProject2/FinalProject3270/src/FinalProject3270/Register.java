package FinalProject3270;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Register extends JFrame {
	
	/**This class constructs the registration page if a new user chooses to register from the login gui */
	
	//Window Title
	JLabel title = new JLabel("TopFlight");
	
	//Labels and fields for data entry
	JLabel firstName = new JLabel ("First Name");
	JTextField enterFirst = new JTextField();
	JLabel lastName = new JLabel ("Last Name");
	JTextField enterLast = new JTextField();
	JLabel address = new JLabel ("Address");
	JTextField enterAddress = new JTextField();
	JLabel zipCode = new JLabel ("Zip Code");
	JTextField enterZip = new JTextField();
	JLabel state = new JLabel("State");
	JTextField enterState = new JTextField();
	JLabel userName = new JLabel("User Name");
	JTextField enterUser = new JTextField();
	JLabel password = new JLabel("Password");
	JTextField enterPassword = new JTextField();
	JLabel email = new JLabel("Email");
	JTextField enterEmail = new JTextField();
	JLabel ssn = new JLabel("SSN");
	JTextField enterSSN = new JTextField();
	JLabel securityQuestion = new JLabel("Security Question");
	JTextField enterQuestion = new JTextField();
	JLabel securityAnswer = new JLabel("Answer");
	JTextField enterAnswer = new JTextField();
	
	//submit button
	JButton submit = new JButton("Submit");
	
	//create the Register GUI
	
	Register(){
		//set frame layout
		getContentPane().setLayout(new GridLayout(3,2));
		//divide window into 3 segments for organization
		JPanel up = new JPanel();
		up.setForeground(Color.DARK_GRAY);
		JPanel mid = new JPanel();
		JPanel low = new JPanel();
		
		//font color and borders
		up.setBackground(Color.WHITE);
		Font font = new Font(Font.SERIF, Font.BOLD, 42);
		title.setFont(font);
		
		// add borders to the panel
		up.setBorder(new TitledBorder("Register New User"));
		mid.setBorder(new TitledBorder("User Information"));
		low.setBorder(new TitledBorder(""));
		//Panel Layout
		up.setLayout(new GridLayout(1,2));
		mid.setLayout(new GridLayout(0,2));
		low.setLayout(new GridLayout(3, 1));
		
		//add objects to panels
		up.add(title);
		mid.add(firstName);
		mid.add(enterFirst);
		mid.add(lastName);
		mid.add(enterLast);
		mid.add(address);
		mid.add(enterAddress);
		mid.add(zipCode);
		mid.add(enterZip);
		mid.add(state);
		mid.add(enterState);
		mid.add(userName);
		mid.add(enterUser);
		mid.add(password);
		mid.add(enterPassword);
		mid.add(email);
		mid.add(enterEmail);
		mid.add(ssn);
		mid.add(enterSSN);
		mid.add(securityQuestion);
		mid.add(enterQuestion);
		mid.add(securityAnswer);
		mid.add(enterAnswer);
		low.add(submit);
	
		//add panels to frame
		getContentPane().add(up);
		getContentPane().add(mid);
		getContentPane().add(low);
		setSize(600,750);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
		
		//add action listener to submit button
		Action action = new Action();
		submit.addActionListener(action);
		
	}
	
	public class Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == submit){
				
				//set User details to submitted info
				User newUser = new User ();
				try{
				newUser.setFirstName(enterFirst.getText());
				newUser.setLastName(enterLast.getText());
				newUser.setAddress(enterAddress.getText());
				newUser.setZip(Integer.valueOf(enterZip.getText()));
				newUser.setState(enterState.getText());
				newUser.setUserName(enterUser.getText());
				newUser.setPassword(enterPassword.getText());
				newUser.setEmail(enterEmail.getText());
				newUser.setSSN(Integer.valueOf(enterSSN.getText()));
				newUser.setSecurityQuestion(enterQuestion.getText());
				newUser.setAnswer(enterAnswer.getText());
				
				User user = Database.createUser(newUser.userName);
				setVisible(false);
				FlightSearch search = new FlightSearch("Search Flight", user.account);
				}
				catch (Exception ex){
					System.out.println("error");
					
				}
				
				//add user to database
				try {
					Database.addUser(newUser);
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//take user back to login screen
			
			}


		}
		
	}
	
	
	public static void main(String[] args){
		Register r = new Register();
		
	
	
	
	
	}

}
