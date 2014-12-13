package FinalProject3270;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

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
	


	ButtonGroup group;

	BookFlight(String airline, String depart, String arrive)
			throws ClassNotFoundException, SQLException {

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
	
		// add content from the array
		mid.add(new JLabel("FLIGHT INFORMATION\n"));
		for (Flight i : flights) {
			// add the flight information from the database to the panel
			// along with the buttons and labels
			group = new ButtonGroup();
			mid.add(new JRadioButton(i.toString()));

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
				
			} else if (e.getSource() == search) {
				// takes you to the search flight GUI
				FlightSearch run = new FlightSearch("Flight Search");
				run.setVisible(true);
				run.setSize(900, 600);
				run.setDefaultCloseOperation(FlightSearch.EXIT_ON_CLOSE);
				book.setVisible(false);

			} else if (e.getSource() == account) {

			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		BookFlight book = new BookFlight("Delta", "Atlanta", "Miami");

		
		

	}

}
