package finalproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.TitledBorder;



/*
 * Class to manage the GIU for the login/registration process the user interfaces with 
 */
public class Login extends JFrame {
	// j buttons to process the events of logging on or registering a new uses
	JButton login = new JButton("Login");
	JButton register = new JButton("Register");
	JTextField user = new JTextField();
	JTextField pwd = new JTextField();
	JLabel userName = new JLabel("User ID:");
	JLabel userPwd = new JLabel("Password:");
	JLabel title = new JLabel("TopFlight");

	// create the Login GUI
	Login() {
		// set the frame layout
		setLayout(new GridLayout(4, 2));

		// Panels for GUI organization
		JPanel up = new JPanel();
		JPanel mid = new JPanel();
		JPanel low = new JPanel();

		// editing panel font, color, and borders
		up.setBackground(Color.white);
		Font custom = new Font(Font.SERIF, Font.BOLD, 42);
		title.setFont(custom);

		// add borders to the panel
		up.setBorder(new TitledBorder("Welcome"));
		mid.setBorder(new TitledBorder("Login"));
		low.setBorder(new TitledBorder(""));

		// Set the Layout for the panels
		up.setLayout(new GridLayout(1, 1));
		mid.setLayout(new GridLayout(2, 2));
		low.setLayout(new GridLayout(1, 2));

		// add the objects to the panel
		up.add(title);
		mid.add(userName);
		mid.add(user);
		mid.add(userPwd);
		mid.add(pwd);
		low.add(login);
		low.add(register);

		// add the panels to the frame
		add(up);
		add(mid);
		add(low);

		// action listeners to give the buttons responses to being clicked
		Action action = new Action();
		login.addActionListener(action);
		register.addActionListener(action);
	}

	/*
	 * Action class that implements action listener this manages the validation
	 * of the user password by gathering the information in the textfields and
	 * passing them to the method that validates the user Authenticity.
	 */
	public class Action implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// action if the login button is clicked
			if (e.getSource() == login) {
				String credentialsUser = user.getText();
				String credentialsPwd = pwd.getText();
				// to check if the user name and password are valid from the
				// user input
				try {
					// passes user credentials to connectDb method to verify
					// that is matches up
					boolean valid = Database.connectDb(credentialsUser,
							credentialsPwd);
					// if it matches up then proceed to the user account
					// if not then notify the user invalid password/userid
					// combination
					if (valid) {
						System.out.println("Welcome to the club ");
					
					
					}/** Needs to transition the user to the account menu from here  */
					
					//invalid password result
					else{
				
					/** Need to add result for when the user enters invalid information  */
					}
				
				} catch (ClassNotFoundException t) {
				} catch (SQLException g) {
				} catch(NullPointerException n){
					JOptionPane.showMessageDialog(login, "Yea thats not gona Fly."
							+ " Correct username password combination needed. "
							+ "\nTry Again", "Sorry", JOptionPane.WARNING_MESSAGE);
				}
				}
			}
		}
	

	public static void main(String[] args) {
		Login log = new Login();
		log.setVisible(true);
		log.setSize(350, 350);
		log.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);

	}

}
