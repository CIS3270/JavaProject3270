package FinalProject3270;





public class User {
	
	//class variables
	
	String firstName;
	 String lastName;
	 String address;
	 int zipCode;
	 String state;
	 String userName;
	 String password;
	 String email;
	 int ssn;
	 String securityQuestion;
	 String securityAnswer;
	 int userId;
	 Account account;
	
	//constructors
	
	User(){
	}
	
	User (int ssn,int userID, String firstName, String lastName, String email, String username, String password,String address, int zipCode, String state,  String securityQuestion,String securityAnswer ){
		account = new Account();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.zipCode = zipCode;
		this.state = state;
		
		this.ssn =ssn;
		this.userId =userID;
		this.email =email;
		this.userName = username;
		this.password = password;
		this.securityQuestion= securityQuestion;
		this.securityAnswer= securityAnswer;
		
	
	}
	
	//class methods
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setZip (int zipCode){
		this.zipCode = zipCode;
	}
	public void setState (String state){
		this.state = state;
	}
	public void setEmail (String email){
		this.email = email;
	}
	public void setSSN (int ssn){
		this.ssn = ssn;
	}
	
	public void setUserName (String userName){
		this.userName = userName;
	}
	
	public  void setPassword (String password){
		this.password = password;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setSecurityQuestion (String securityQuestion){
		this.securityQuestion = securityQuestion;
	}
	
	public void setAnswer (String answer){
		securityAnswer = answer;
	}
	
	public String toString(){
		return null;
		
	}
	
	
	
	
	

}
