package finalproject;

import java.io.*;
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

	// construct the GUI for user interaction
	FlightSearch(String name) {
		// give the panel a name call the super class which is JFrame
		super(name);

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
		String[] airlineNames = { "Delta", "Spirit", "GSUAir", "WeFlyHi",
				"AirJamaica", "SouthWest" };
		airlines = new JComboBox<String>(airlineNames);
		JLabel airline = new JLabel("Airline: ");

		JComboBox<String> cityD = new JComboBox<String>();
		JLabel cityDepart = new JLabel("Depart: ");

		JComboBox<String> cityA = new JComboBox<String>();
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
		 * down and stores the data selected by tehe user
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
	}

		//inner class to implement the action listener
	class Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// search the data base for the flight information requested and display it in
			// another GUI window if the search button is clicked
			if(e.getSource()==search){
				BookFlight flight = new BookFlight(airlines.getSelectedObjects().toString());
				JOptionPane.showMessageDialog(search, "You Clicked this button now what?");
			//if the home button is clicked return the user to the main account screen
			}else if(e.getSource()==home){
				
			}

		}
	}

	public static void main(String[] args) {
		Flight[] flights = new Flight[3];
		FlightSearch search = new FlightSearch("Flight List");
		search.setVisible(true);
		search.setSize(650, 300);

		search.setDefaultCloseOperation(FlightSearch.EXIT_ON_CLOSE);
	}

}
