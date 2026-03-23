package proj4fa24;

/**
 * <p>Title: DateException.java</p>
 * <p> Description: Exception data type class for the Date class. </p>
 * @author Jesse_Connell Cornelius_Jones
 */
	@SuppressWarnings("serial")
	
	public class DateException extends Exception {

		//Default constructor for DateException object
	public DateException() {
		super("Invalid value for Date");
	}
	/**
	 * Parameterized constructor
	 * @param message
	 */
	public DateException(String message) {
		super(message);
	}
}




