package fcul.pco.eurosplit.domain;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/**
 *The Date class represents a date composed by year, month, day, hour and minute
 *@author Catarina Carvalho - 48656 e Vicky Rajani - 53598
 *@version 4.7.3a
 */
public class Date {
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;

	/**
	 * A constructor that initializes the year, the month, the day, the hour and  
	 * the minute of a new object Date
	 * Arguments must specify a valid date
	 * @param y the year
	 * @param m the month (1 is January,...)
	 * @param d the day in the month
	 * @param h the hour in 0-23 format
	 * @param min the minute
	 */
	public Date(int year, int month, int day, int hour, int minute) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}
	
	/**
	 * @return the year of the date
	 */
	
	public int getYear() {
		return year;
	}
	
	/**
	 * @return the month of the date
	 */
	
	public int getMonth() {
		return month;
	}
	
	/**
	 * @return the day of the date
	 */
	
	public int getDay() {
		return day;
	}
	
	/**
	 * @return the hour of the date
	 */
	
	public int getHour() {
		return hour;
	}
	
	/**
	 * @return the minute of the date
	 */
	
	public int getMinute() {
		return minute;
	}
	
	/**
	 * Textual representation of this date
	 * @return the String date
	 */
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append(getYear());
		sb.append("/");
		sb.append(getMonth());
		sb.append("/");
		sb.append(getDay());
		sb.append("/");
		sb.append(getHour());
		sb.append("/");
		sb.append(getMinute());
		
		return sb.toString();
	}

	/**
	 * This method receives the input from the user (a String) to return the type Date.
	 * @param d the input string
	 * @return a Date 
	 */
	
	public static Date fromString (String d) {
				
		String [] data = d.split("/");

		int year = Integer.parseInt(data[0]);
		int month = Integer.parseInt(data[1]);
		int day = Integer.parseInt(data[2]);
		
		int hour = Integer.parseInt(data[3]);
		int minute = Integer.parseInt(data[4]);
		Date d1 = new Date(year, month, day, hour, minute);
		return d1;
	}
	
	/**
	 * @return the current date
	 */
	public static Date now() {
		LocalDateTime today = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/mm");
		String formatDateTime = today.format(formato);
		return fromString(formatDateTime);
		
	}
}

 
