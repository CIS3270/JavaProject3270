package FinalProject3270;

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.util.Date;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javafx.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Class to manage the GUI app that allows the user to search for a flights
 */
public class FlightSearch extends JFrame {
	JButton search = new JButton("Search");
	JButton home = new JButton("Home");
	JComboBox<String> airlines;
	JComboBox<String> cityD;
	JComboBox<String> cityA;
	Account account;
	// construct the GUI for user interaction
	FlightSearch(String name,Account account) {
		// give the panel a name call the super class which is JFrame
		super(name);
		//need the account object to transfer to call the account GUI of the user
		this.account = account;
		// Build two panels for organization upper and lower
		JPanel upper = new JPanel();
		JPanel lower = new JPanel();
		JPanel title = new JPanel();

		// set the layout for the panels
		setLayout(new GridLayout(3, 1));
		title.setLayout(new GridLayout(1, 1));
		upper.setLayout(new FlowLayout());
		lower.setLayout(new FlowLayout());

		// add objects to the panels
		// adding combo boxes to give the user the airline choices
		String[] airlineNames = { "Select Airline","Delta", "Spirit", "GSUAir", "WeFlyHi",
				"AirJamaica", "SouthWest" };
		airlines = new JComboBox<String>(airlineNames);
		JLabel airline = new JLabel("Airline: ");

		cityD = new JComboBox<String>();
		
		JLabel cityDepart = new JLabel("Depart: ");

		cityA = new JComboBox<String>();
		cityA.addItem("...");
		
		JLabel cityArrival = new JLabel("Arrival: ");

		JLabel departureDate = new JLabel("Departure Date ");
		JLabel arrivalDate = new JLabel("Arrival Date ");

		JLabel titleH = new JLabel("JavaBookers ");

		Font custom = new Font(Font.SANS_SERIF, Font.BOLD, 43);
		titleH.setFont(custom);

		// add combo buttons and JLabels for the upper panel
		title.add(titleH);

		upper.add(airline);
		upper.add(airlines);
		upper.add(cityDepart);
		upper.add(cityD);
		upper.add(cityArrival);
		upper.add(cityA);

		// adding calendar portion
		/**
		 * to add a calendar I downloaded the jar file for the JDatePicker
		 * library the variable model is needed to represent the selected data
		 * as a particular type of date object in java. UtilDateModel returns
		 * the date as type Java.Util.Date The JDatePanelImpl builds the GUI
		 * that displays the calendar The JDatePickerImpl pops the GUI up and
		 * down and stores the data selected by the user
		 */
		// declare JDatePickerImpl and declare and create the data model,
		// models specify the type of object the time will be stores as
		JDatePickerImpl dateDepart;
		JDatePickerImpl dateArrival;
		UtilDateModel model = new UtilDateModel();
		UtilDateModel model2 = new UtilDateModel();
		// pass the model type to the JDatePanlImp which creates the GUI for the
		// calendar
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);

		// now pass the panels to JDatePicker which implement the pop up of the
		// frame and recording of data
		dateDepart = new JDatePickerImpl(datePanel);
		dateDepart.setBounds(100, 100, 100, 100);

		dateArrival = new JDatePickerImpl(datePanel2);
		dateArrival.setBounds(100, 100, 100, 100);

		lower.add(departureDate);
		lower.add(dateDepart);
		lower.add(arrivalDate);
		lower.add(dateArrival);
		lower.add(search);
		lower.add(home);

		title.setBorder(new TitledBorder("Welcome"));
		// add panels to the JFrame
		add(title);
		add(upper);
		add(lower);
		
	

		/**
		 * Implement the action listeners to access the data base on button
		 * clicks
		 */
		Action action = new Action();
		search.addActionListener(action);
		home.addActionListener(action);
		// combo box action listener
		airlines.addActionListener(action);
		cityA.addActionListener(action);
		cityD.addActionListener(action);
		// set the visibility size and location
		
		setSize(900, 600);
		setLocation(150,20);
		setVisible(true);
		setDefaultCloseOperation(FlightSearch.EXIT_ON_CLOSE);
	}

		//inner class to implement the action listener
	class Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// search the data base for the flight information requested and display it in
			// another GUI window if the search button is clicked
			if(e.getSource()==search){
				String selected =(String) airlines.getSelectedItem();
				String citydepart =(String) cityD.getSelectedItem();
				String cityarrive = (String) cityA.getSelectedItem();
				
				try {
					if(cityarrive.equals("...")){
						BookFlight flight = new BookFlight(selected, citydepart,account);
						setVisible(false);
					}else{
					BookFlight flight = new BookFlight(selected, citydepart, cityarrive,account);
					setVisible(false);
					}
				} catch (ClassNotFoundException e1) {
					//  Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					//  Auto-generated catch block
					e1.printStackTrace();
				}
				
			//if the home button is clicked return the user to the main account screen
			}else if(e.getSource()==home){
				try{
				AccountGUI panel = new AccountGUI(account);
				}
				catch(NullPointerException t){
					
				}
			}else if(e.getSource() == airlines ){
				// add the cities that this particular flight travels to 
				String selected = (String) airlines.getSelectedItem();
				// switch statement switches the combo box values based on the airline selected	
				switch(selected){
					//these are the cities that a particular airline flies out from	
					case "Delta": 
					//remove all clears the previous combo box values.
					//add item gives it values according to that particular airline
					cityD.removeAllItems();
					cityD.addItem("...");
					cityD.addItem("Miami");
					cityD.addItem("Charlotte");
					cityD.addItem("Atlanta");
					//arrival cities
					break;
					case "Spirit": 
						cityD.removeAllItems();
						cityD.addItem("...");
						cityD.addItem("Fort Lauderdale");
						cityD.addItem("Jacksonville");
						cityD.addItem("Orlando");
						cityD.addItem("New Orleans");
				}
			}
			if(e.getSource()==cityD){
				String cityASel= (String)cityD.getSelectedItem();
				switch(cityASel){
				case "Charlotte":
					cityA.removeAllItems();
					cityA.addItem("...");
					cityA.addItem("New Orleans");
					cityA.addItem("Atlanta");
					cityA.addItem("Chicago");
					break;
				case "Miami":
					cityA.removeAllItems();
					cityA.addItem("...");
					cityA.addItem("Atlanta");
					cityA.addItem("Houston");
					cityA.addItem("Detroit");
					break;
				case "Atlanta":
					cityA.removeAllItems();
					cityA.addItem("...");
					cityA.addItem("Tampa");
					cityA.addItem("New York");
					cityA.addItem("Los Angeles");
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		Flight[] flights = new Flight[3];
		Account acct = new Account();
		Date now = new Date();
		Date after = new Date();
		Flight mia = new Flight("Delta",123,"Atlanta","Miami",50,now,after);
		acct.addFlight(mia);

		FlightSearch sear = new FlightSearch("Looking",acct);
		//FlightSearch search = new FlightSearch("Flight List");
		
	}

}
