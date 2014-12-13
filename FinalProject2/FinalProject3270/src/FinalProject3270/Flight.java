package FinalProject3270;

import java.util.ArrayList;
import java.util.Date;

public class Flight {
	int flightNumber;
	String airlineName;
	String destinationCity;
	String departureCity;
	Date arrivalDate;
	Date departureDate;
	int capacity;
	int remainingSeats;
	

	Flight() {

	}

	Flight(String airlineName,int flightNumber,  String destinationCity,
			String departureCity,int capacity, Date arrivalDate, Date departureDate
			) {
		this.flightNumber = flightNumber;
		this.airlineName = airlineName;
		this.destinationCity = destinationCity;
		this.departureCity = departureCity;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.capacity = capacity;
		this.remainingSeats = capacity;

	}

	/*
	 * returns true if the flight is full and false along with the number of
	 * seats remaining if it has seats left
	 */
	public boolean full() {
		if (remainingSeats == 0) {
			System.out.println("No more seats");
			return true;
		}
		System.out.println(remainingSeats + "seats remaining");
		return false;
	}

	/*
	 * Returns the number of remaining seats on the flight
	 */
	public int getRemainingSeats() {
		return remainingSeats;
	}

	/*
	 * Search for a Flight with the given a flightNumber
	 */
	public String toString() {
		return "FLIGHT NUMBER:"+flightNumber+" \nAIRLINE:"+airlineName+" \nDEPARTURE:"+ departureCity +
				" \nARRIVAL:"+destinationCity+" \nDATE:"+departureDate+" \nCAPACITY:"
				+ capacity+ " REMAINING SEATS:"+getRemainingSeats();
	}
	//returns a array of flights that closets match the search options given by the user
	public static ArrayList<Flight> search(ArrayList<Flight> flights,String departure,String arrival){
		//sort through the array to match the departure and arrival cities and puts it in a new array
		ArrayList<Flight> filtered = new ArrayList<Flight>();
		// search through the array for particular flight information
		for(Flight i: flights ){
			
				if((i.departureCity.equals(departure))&&(i.destinationCity.equals(arrival))){
					// if they both match then add it to the new array
					filtered.add(i);
				}
		}
		return filtered;
	}

}
