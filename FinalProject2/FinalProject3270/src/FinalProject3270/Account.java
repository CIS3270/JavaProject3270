package FinalProject3270;
import java.util.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Account {
	private int numberOfFlights;
	private Date dateBooked;
	private ArrayList <Flight>flights;
	private ArrayList<Date> date;
	
	
	Account(){
	numberOfFlights= 0;	
	flights = new ArrayList<Flight>();
	date = new ArrayList<Date>();
	}

	public int getNumberOfFlights() {
		return numberOfFlights;
	}

	
// returns the booking date of a particular flights 
	public Date getDateBooked( int flightNum) {
		int dateIndex=0;//to track the date index;

		for(Flight i: flights){
			
				if( i.flightNumber == flightNum){
					dateIndex = flights.indexOf(i);
					dateBooked = date.get(dateIndex);
				}
			}
		return dateBooked;
		}
		
		
	

	
//returns a array of flight objects
	public ArrayList<Flight> getFlights() {
		
		return flights;
	}
	//access the database to add this particular flight to the array list of flight the user has
	public void addFlight(Flight flight){
		flights.add(flight);
		date.add(new Date());
		this.numberOfFlights++;
		
		
	}
	// removes a flight from the users account
	public void delete(int flightNum){
		int dateIndex=0;//to track the date index;
		String removed ="";
		ArrayList<Flight> copy = (ArrayList<Flight>) flights.clone();
		for(Flight i: flights){
			
				if( i.flightNumber == flightNum){
					dateIndex = flights.indexOf(i);
					date.remove(dateIndex);
					removed=i.toString();
					copy.remove(i);	
				}
				flights = copy;
		}
		
		
	}
	public Flight getFlight( int flightNum, ArrayList<Flight> flights) {
		
		Flight fly = flights.get(0);
		for(Flight i: flights){
			
				if( i.flightNumber == flightNum){
					return i;
					
			}
		
		}
		return fly; 
	}
	//returns a string of all the account information ////
	public String toString(){
		String info = "Flights booked: "+numberOfFlights+"\n" ;
		int current;
		for(Flight i: flights){
			current = flights.indexOf(i);
		info +=	"**Flight info**\n"+ i;
		info += "\nBOOKING DATE: "+date.get(current);			
			}
		return info;
	}
public static void main(String []args){
	Date now = new Date();
	Date after = new Date();
	Account acct = new Account();
	Flight mia = new Flight("Delta",123,"Atlanta","Miami",50,now,after);
	acct.addFlight(mia);
	ArrayList<Flight> fly = new ArrayList<Flight>();
	fly.add(mia);
	
	System.out.println(acct.toString());
	acct.delete(123);
	
	System.out.println(acct.flights.isEmpty());
	Flight me =acct.getFlight(123,fly );
	acct.getFlight(123, fly);
}
}
