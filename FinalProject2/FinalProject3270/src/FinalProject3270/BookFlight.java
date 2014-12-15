package FinalProject3270;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class to build the GUI for the Flight Booker to add flights the user looked
 * up to the users account
 */
public class BookFlight extends JFrame {
	// used to store all the flights of a particular flight
	ArrayList<Flight> flights;
	String airline;
	String depart;
	String arrive;
	JButton book = new JButton("Book");
	JButton search = new JButton("Search");
	JButton account = new JButton("Account");
	Account userAccount;//users account information
	
JRadioButton radio = new JRadioButton();

	ButtonGroup group;

	BookFlight(String airline, String depart, String arrive,Account userAccount)
			throws ClassNotFoundException, SQLException {
		//user account info*****Should be from USER object
		this.userAccount = userAccount;
		// builds the array through the database
		flights = Database.gatherFlight(airline);
		// sort this array with only requested information
		flights = Flight.search(flights, depart, arrive);

		/**
		 * NOW TO CREATE THE GUI
		 */
		JPanel top = new JPanel();
		JPanel mid = new JPanel();
		JPanel low = new JPanel();

		// top panel layout
		top.setLayout(new FlowLayout());
		JLabel title = new JLabel(airline);
		Font custom = new Font(Font.SANS_SERIF, Font.BOLD, 42);
		title.setFont(custom);
		top.setBackground(Color.white);
		top.add(title);
		add(top);

		// middle panel layout
		mid.setLayout(new FlowLayout());
		group = new ButtonGroup();
		// add content from the array
		mid.add(new JLabel("FLIGHT INFORMATION\n"));
		
		
		
			for (Flight i : flights) {
				// add the flight information from the database to the panel
				// along with the buttons and labels
			JRadioButton radio = new JRadioButton(i.toString());
			radio.setActionCommand(i.toString());
				group.add(radio);
				mid.add(radio);
			}
		

		add(mid);

		low.add(book);
		low.add(search);
		low.add(account);
		add(low);
		
		setSize(900, 600);
		setDefaultCloseOperation(BookFlight.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 1));
		setLocation(150,20);
		setVisible(true);

		Action action = new Action(this);

		book.addActionListener(action);
		search.addActionListener(action);
		radio.addActionListener(action);
		account.addActionListener(action);
	
	}

	class Action implements ActionListener {
		// takes in the current BookFlight object as a object in order to
		// manipulate and access its data fields
		BookFlight bookF;

		Action(BookFlight book) {
			this.bookF = book;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			/**
			 * if book is selected the flights selected get added to the users
			 * account else if search is selected it returns the user back to
			 * the search GUI else if the home is selected the user is taken to
			 * their accounts GUI
			 */
			if (e.getSource() == book) {
				// adds the flight object selected to the user account
				try{	
					String flightNum =	group.getSelection().getActionCommand().substring(14,17);
					int flightnum = Integer.parseInt(flightNum);
					
					for(Flight i: flights){
						if( i.flightNumber == flightnum){
							userAccount.addFlight(i);
							JOptionPane.showMessageDialog(null, "Flight Added");
					}
					
					}
					
					
				}catch(NullPointerException n){
					n.getMessage();
					JOptionPane.showMessageDialog(null,"choose  a option" );
				}
			String flightNum =	group.getSelection().getActionCommand().substring(15,group.getSelection().getActionCommand().length());
			System.out.println(flightNum);
			} else if (e.getSource() == search) {
				// takes you to the search flight GUI
				FlightSearch run = new FlightSearch("Flight Search",userAccount);
				run.setVisible(true);
				run.setSize(900, 600);
				run.setDefaultCloseOperation(FlightSearch.EXIT_ON_CLOSE);
				book.setVisible(false);

			} else if (e.getSource() == account) {
//takes you to the user account GUI
				AccountGUI page= new AccountGUI(userAccount);
			}
		}
		}
		BookFlight(String airline, String depart,Account userAccount) throws ClassNotFoundException, SQLException {
			this.userAccount =userAccount;
			// builds the array through the database
			flights = Database.gatherFlight(airline);
			// sort this array with only requested information
			flights = Flight.search(flights, depart);

			/**
			 * NOW TO CREATE THE GUI
			 */
			JPanel top = new JPanel();
			JPanel mid = new JPanel();
			JPanel low = new JPanel();

			// top panel layout
			top.setLayout(new FlowLayout());
			JLabel title = new JLabel(airline);
			Font custom = new Font(Font.SANS_SERIF, Font.BOLD, 42);
			title.setFont(custom);
			top.setBackground(Color.white);
			top.add(title);
			add(top);

			// middle panel layout
			mid.setLayout(new FlowLayout());
			group = new ButtonGroup();
			// add content from the array
			mid.add(new JLabel("FLIGHT INFORMATION\n"));
			for (Flight i : flights) {
				// add the flight information from the database to the panel
				// along with the buttons and labels
			JRadioButton radio = new JRadioButton(i.toString());
			radio.setActionCommand(i.toString());
				group.add(radio);
				mid.add(radio);
			}

			add(mid);

			low.add(book);
			low.add(search);
			low.add(account);
			add(low);
			
			setSize(900, 600);
			setDefaultCloseOperation(BookFlight.EXIT_ON_CLOSE);
			setLayout(new GridLayout(3, 1));
			setLocation(150,20);
			setVisible(true);

			Action2 action = new Action2(this);

			book.addActionListener(action);
			search.addActionListener(action);
			account.addActionListener(action);
		}

		class Action2 implements ActionListener {
			// takes in the current BookFlight object as a object in order to
			// manipulate and access its data fields
			BookFlight bookF;

			Action2(BookFlight book) {
				this.bookF = book;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * if book is selected the flights selected get added to the users
				 * account else if search is selected it returns the user back to
				 * the search GUI else if the home is selected the user is taken to
				 * their accounts GUI
				 */
				if (e.getSource() == book) {
					// adds the flight object selected to the user account
				try{	
					
					String flightNum =	group.getSelection().getActionCommand().substring(14,17);
					int flightnum = Integer.parseInt(flightNum);
					System.out.println(flights.toString());
					//get the correct flight to return to add to the list
					for(Flight i: flights){
						if( i.flightNumber == flightnum){
							userAccount.addFlight(i);		
							JOptionPane.showMessageDialog(null, "Flight Added");
					}
					
					}
				}catch(NullPointerException n){
					JOptionPane.showMessageDialog(null,"Choose a choice" );
				}
				} else if (e.getSource() == search) {
					// takes you to the search flight GUI
					FlightSearch run = new FlightSearch("Flight Search",userAccount);
					run.setVisible(true);
					run.setSize(900, 700);
					run.setDefaultCloseOperation(FlightSearch.EXIT_ON_CLOSE);
					book.setVisible(false);

				} else if (e.getSource() == account) {
					AccountGUI page= new AccountGUI(userAccount);
				}
			}
			}
	

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		Date now = new Date();
		Date after = new Date();
		
		Flight mia = new Flight("Delta",123,"Atlanta","Miami",50,now,after);
		Account acct = new Account();
		acct.addFlight(mia);
		
		BookFlight book = new BookFlight("Delta", "Miami",acct);

		for(Flight i : book.flights){
			System.out.println(i);
		}
		
		

	}

}
