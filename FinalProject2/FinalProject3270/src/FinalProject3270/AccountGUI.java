package FinalProject3270;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import java.util.InputMismatchException;

import FinalProject3270.BookFlight.Action;
import FinalProject3270.BookFlight.Action2;


public class AccountGUI extends JFrame{
//building the GUI for the Account GUI
	JButton delete = new JButton("Delete");
	JButton search = new JButton("Search");
	JButton logout = new JButton("Logout");
	JButton submit = new JButton("Delete");// the second delete button to finalize the delete
	JButton update = new JButton("Update");
	
	ArrayList<Flight> accFlight;

	ButtonGroup group;
	AccountGUI( Account user){
		
		accFlight = user.getFlights();
		
	
		/**
		 * NOW TO CREATE THE GUI
		 */
		setLayout(new BorderLayout());
		JPanel top = new JPanel();
		JPanel mid = new JPanel();
		JPanel low = new JPanel();

		// top panel layout
		top.setLayout(new FlowLayout());
		JLabel title = new JLabel("Account");
		Font custom = new Font(Font.SANS_SERIF, Font.BOLD, 42);
		title.setFont(custom);
		top.setBackground(Color.white);
		top.add(title);
		add(top, BorderLayout.NORTH);

		// middle panel layout
		mid.setLayout(new FlowLayout());
		group = new ButtonGroup();
		// add content from the array
		mid.add(new JLabel("Flights Booked\n"));
		for (Flight i : accFlight) {
			// add the flight information from the database to the panel
			// along with the buttons and labels
		
			mid.add( new JLabel(i.toString()));
		}
		mid.setBorder(new TitledBorder("Flight list"));
		add(mid, BorderLayout.CENTER);

		low.add(delete);
		low.add(search);
		low.add(logout);
		low.add(update);
		add(low, BorderLayout.SOUTH);
		
		setSize(1100, 600);
		setDefaultCloseOperation(BookFlight.EXIT_ON_CLOSE);
		
		setLocation(150,20);
		setVisible(true);

		Action action = new Action(user);

		delete.addActionListener(action);
		search.addActionListener(action);
		submit.addActionListener(action);
		update.addActionListener(action);
		logout.addActionListener(action);
	}

	class Action implements ActionListener {
		// takes in the current Account object as a object in order to
		// manipulate and access its data fields
		
		Account acc;
		JTextField text ;

		Action( Account account) {
			
			acc = account;
			ArrayList<Flight> accFlight = acc.getFlights();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			/**
			 * if delete is selected the flights selected get deleted from the users
			 * account else if search is selected it returns the user back to
			 * the search GUI else if the 
			 */
			if (e.getSource() == delete) {
				// deletes the flight object selected to the user account
				//add a textfield to the panel and prompt for the flight number
				AccountGUI deleteGUI = new AccountGUI(acc);
				setVisible(false);
				deleteGUI.setLayout(new GridLayout(4,1));
				JPanel del = new JPanel();
				text = new JTextField();
				text.setColumns(10);
				
				JLabel remove = new JLabel("Enter flight number:");
				
				del.setLayout(new FlowLayout());
				del.add(remove);
				del.add(text);
				del.add(submit);
				deleteGUI.add(del);
				// takes the flight number as a argument and send ot to the delete flight method of account
				
				acc.getFlights();
				//get its flight number
				//call the delete method in the account class
				
			
			} else if (e.getSource() == search) {
				// takes you to the search flight GUI
				FlightSearch run = new FlightSearch("Flight Search",acc);
				run.setVisible(true);
				run.setSize(900, 600);
				run.setDefaultCloseOperation(FlightSearch.EXIT_ON_CLOSE);
				delete.setVisible(false);
				setVisible(false);

			} else if (e.getSource() == submit) {
				//calls the delete method and deletes the requested flight
				try{
				int delete = Integer.parseInt(text.getText());
				if(acc.getFlights().isEmpty()){
					JOptionPane.showMessageDialog(null,"You have no flights to delete");
				}else{
				acc.delete(delete);
				setVisible(false);
				JOptionPane.showMessageDialog(null,"Its been removed");
				}
				}
				catch(InputMismatchException t){
					JOptionPane.showMessageDialog(null,"Sorry Wrong Input");
				}
				catch(NumberFormatException n){
					JOptionPane.showMessageDialog(null,"Sorry Wrong Input");
				}
			}else if(e.getSource()==update){
				AccountGUI update = new AccountGUI(acc);
				setVisible(false);
			}
			else if(e.getSource()==logout){}
			Login start = new Login();
			setVisible(false);
		}
		}
		public static void main(String[] args){
			Date now = new Date();
			Date after = new Date();
			Flight fl = new Flight("Delta",123,"Orlando","Atlanta",300,now,after);
			Flight ga = new Flight("Delta",124,"Charlotte","Compton",60,now,after);
			Flight ny = new Flight("Delta",125,"New York","Jamaica",30,now,after);
			Flight mia = new Flight("Delta",127,"Atlanta","Miami",50,now,after);
			Account acct = new Account();
			
			AccountGUI gui = new AccountGUI(acct);
		}
	
}
