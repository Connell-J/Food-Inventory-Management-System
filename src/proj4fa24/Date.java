package proj4fa24;

/**
 *
 * <p>Title: The Date Class</p>
 *
 * <p>Description: Objects of this type can store a month, day, and year as integers.
 * The class provides accessors and mutators for all instance variables along with
 * a toString method.</p>
 *
 * @author Jesse_Connell Cornelius_Jones
 */

public class Date
{
	private int dMonth;		//variable to store the month
	private int dDay;		//variable to store the day
	private int dYear;		//variable to store the year

	/**
	 * default constructor --
	 * sets month, day, and year to 1, 1, and 1800 respectively
	 */
	public Date()
	{
		dMonth = 1;
		dDay = 1;
		dYear = 1800;
	}

	/**
	 * parameterized constructor --
	 * sets month, day, and year to user specified values
	 * @param month value to be stored as this object's month
	 * @param day value to be stored as this object's day
	 * @param year value to be stored as this object's year
	 */
	public Date(int month, int day, int year) throws DateException
	{
		setMonth(month);
		setDay(day);
		setYear(year);
	}

	/**
	 * setDate --
	 * stores month, day, and year by calling the setMethods defined
	 * @param month value to be stored as this object's month
	 * @param day value to be stored as this object's day
	 * @param year value to be stored as this object's year
	 */
	public void setDate(int month, int day, int year) throws DateException
	{
		setMonth(month);
		setDay(day);
		setYear(year);
	}

	/**
	 * setMonth --
	 * stores the user specified value as the month
	 * @param month the value to be stored
	 */
	public void setMonth(int month) throws DateException
	{
		if (month >= 1 && month <= 12)
			dMonth = month;

		else

			throw new DateException("Invalid Month: month out of range");

	}

	/**
	 * setDay --
	 * stores the user specified value as the day
	 * @param day the value to be stored
	 */
	public void setDay(int day) throws DateException
	{
		if (day >= 1 && day <= 31)
			dDay = day;

		else

			throw new DateException("Invalid Day: Day out of range");

	}

	/**
	 * setYear --
	 * stores the user specified value as the year
	 * @param year the value to be stored
	 */
	public void setYear(int year) throws DateException
	{
		if(year >= 0)
			dYear = year;

		else

			throw new DateException("Invalid Year: Year out of range");
	}

	/**
	 * getMonth --
	 * accessor for the month
	 * @return returns the value stored as the month
	 */
	public int getMonth()
	{
		return dMonth;
	}

	/**
	 * getDay --
	 * accessor for the day
	 * @return returns the value stored as the day
	 */
	public int getDay()
	{
		return dDay;
	}

	/**
	 * getYear --
	 * accessor for the year
	 * @return returns the value stored as the year
	 */
	public int getYear()
	{
		return dYear;
	}

	/**
	 * compareTo --
	 * Compares this date to another date. Comparison starts with the year, 
	 * then the month, and finally the day if needed.
	 * @param otherDate
	 * @return a negative number if this date is earlier, 0 if they are the same, 
	 *         or a positive number if this date is later.
	 */
	public int compareTo(Date otherDate)
	{
		if (this.dYear != otherDate.dYear)
		{
			return this.dYear - otherDate.dYear;
		}
		if (this.dMonth != otherDate.dMonth)
		{
			return this.dMonth - otherDate.dMonth;
		}
		return this.dDay - otherDate.dDay;
	}

	/**
	 * equals --
	 * Compares this Date object to another Date object.
	 * Two dates are considered equal if their day, month, and year values are identical.
	 * @param otherDate
	 * @return true if the two Date objects are equal, false otherwise
	 */
	public boolean equals(Object otherDate)
	{
		if(this.dYear == ((Date)otherDate).dYear && this.dMonth == ((Date)otherDate).dMonth && this.dDay == ((Date)otherDate).dDay)
		{
			return true;
		}
		return false;
	}

	/**
	 * toString --
	 * returns the month, day, and year in the format: mm-dd-yyyy;
	 * leading zeros are NOT contained within the string
	 * @return a String containing the date in month-day-year format
	 */
	public String toString()
	{
		return (dMonth + "-" + dDay + "-" + dYear);
	}
}

