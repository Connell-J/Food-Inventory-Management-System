package proj4fa24;
/**
 * <p>Title: FoodItem Class</p>
 * 
 * <p>Description: The FoodItem class represents an item with a specified quantity and expiration date.
 * It provides methods for comparing FoodItem objects, updating quantity, and checking expiration. </p>
 * 
 * @author Jesse Connell
 */

public class FoodItem implements Comparable<Object>
{

	private int quantity; // The quantity of the food item
	private Date expirationDate; // The expiration date of the food item
	
	/**
	 * FoodItem--
     * Constructs a FoodItem object with the specified quantity and expiration date.
     * 
     * @param amt the quantity of the food item
     * @param expirationDate the expiration date of the food item
     */
	public FoodItem(int quantity, Date expirationDate)
	{
		this.quantity = quantity; 
		this.expirationDate = expirationDate; 
	}
	
	/**
	 * getQuantity--
     * Gets the quantity of the food item.
     * 
     * @return the quantity of the food item
     */
	public int getQuantity()
	{
		return quantity;
	}
	
	/**
	 * getExpirationDate--
    * Gets the expiration date of the food item.
    * 
    * @return the expiration date of the food item
    */
	public Date getExpirationDate()
	{
		return expirationDate;
	}
	
	/**
	 * isExpired--
     * Checks if the food item has expired based on a given date.
     * The food item is considered expired if its expiration date is before the provided date.
     * 
     * @param theDate the date to compare against the food item's expiration date
     * @return true if the food item is expired, false otherwise
     */
	public boolean isExpired(Date theDate)
	{
		return expirationDate.compareTo(theDate) < 0;
	}
	
	/**
	 * updateQuantity--
     * Updates the quantity of the food item by adding the specified amount.
     * 
     * @param amt the amount to add to the food item's quantity
     */
	public void updateQuantity(int amt)
	{
		this.quantity += amt;
	}

	/**
	 * equals--
     * Compares this FoodItem object with another FoodItem object for equality.
     * For two FoodItem objects to be considered equal, they must have the same expiration date.
     * 
     * @param item the object to compare this FoodItem with
     * @return true if the FoodItem objects are equal, false otherwise
     */
	public boolean equals(Object item)
	{
		return false;
	}
	
	/**
	 * compareTo--
     * Compares this FoodItem object with another object based on its expiration date.
     * The comparison uses the expiration date to determine order.
     * 
     * @param item the object to compare this FoodItem with
     * @return a negative integer, zero, or a positive integer as this FoodItem's expiration date
     *         is less than, equal to, or greater than the expiration date of the specified object
     */
	public int compareTo(Object item)
	{
		return this.expirationDate.compareTo(((FoodItem)item).expirationDate);
	}
	
	/**
	 * toString--
     * Returns a string representation of the FoodItem object.
     * The string contains the quantity and expiration date of the food item.
     * 
     * @return a string representation of the FoodItem object
     */
	public String toString() 
	{
		return "Quantity: " + quantity + "\n" + "Expiration Date: " + expirationDate;
	}
}

