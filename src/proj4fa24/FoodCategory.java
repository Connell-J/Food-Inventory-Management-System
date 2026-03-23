package proj4fa24;

import lists.ArrayOrderedList;
import lists.ArrayUnorderedList;

/**
 * <p>Title: FoodCategory Class</p>
 * 
 * <p>Description: The FoodCategory class represents a category of food items, each identified by its category name.
 * It allows for adding food items, deleting items by quantity, removing expired items, 
 * and checking if the category is empty. </p>
 * 
 * @author Jesse_Connell
 */

public class FoodCategory 
{

	private String categoryName; // The name of the food category
	
	private ArrayOrderedList<FoodItem> items; // List of food items in this category

	/**
	 * FoodCategory--
	 * Constructs a FoodCategory object with the specified category name.
	 * 
	 * @param categoryName the name of the food category
	 */
	public FoodCategory(String catName)
	{
		this.categoryName = catName;
		this.items = new ArrayOrderedList<>();
	}

	/**
	 * addItem--
	 * Adds a FoodItem to this category's list of items.
	 * 
	 * @param item the FoodItem to be added to the category
	 */
	public void addItem(FoodItem item) 
	{
	    ArrayOrderedList<FoodItem> tempList = new ArrayOrderedList<FoodItem>(); // Temporary list
	    
	    boolean itemExists = false;

	    // Transfer items to tempList and check for matching expiration date
	    while (!items.isEmpty())
	    {
	        FoodItem currentItem = items.removeLast(); // Remove from the original list
	        
	        if (currentItem.getExpirationDate().equals(item.getExpirationDate())) 
	        {
	            currentItem.updateQuantity(item.getQuantity()); // Update quantity
	            
	            itemExists = true; // Mark as found
	        }
	        tempList.add(currentItem); // Add to temporary list
	    }
	    // If no matching item exists, add the new item
	    if (!itemExists) 
	    {
	        tempList.add(item);
	    }

	    // Restore items list from tempList
	    items = tempList;
	}

	/**
	 * deleteItems--
	 * Deletes a specified quantity of items from the category.
	 * Returns the quantity of items actually removed. 
	 * 
	 * @param quantity the quantity of items to remove
	 * @return the number of items removed
	 */
	public int deleteItems(int amtRemoved) throws InsufficientQuantityException 
	{
	    int numRemoved = 0;

	    // Temporary list to hold remaining items
	    ArrayOrderedList<FoodItem> tempList = new ArrayOrderedList<FoodItem>();

	    while (!items.isEmpty()) 
	    {
	        tempList.add(items.removeLast());
	    }

	   
	    while (!tempList.isEmpty() && amtRemoved > 0) 
	    {
	        // Remove the first item from the list
	        FoodItem item = tempList.removeLast(); // Remove from the rear

	        if (item.getQuantity() <= amtRemoved) 
	        {
	            amtRemoved -= item.getQuantity(); // Decrease the remaining amount to remove
	            numRemoved += item.getQuantity(); // Increment the removed quantity
	        } 
	        else 
	        {
	            item.updateQuantity(-amtRemoved); // Update the item's quantity by reducing it
	            numRemoved += amtRemoved; // Add the amount removed from this item

	            // Add the updated item back to the temporary list
	            tempList.add(item);
	            amtRemoved = 0;
	        }

	        // If amtRemoved > 0 then there aren't enough items to remove
	        if (amtRemoved > 0) 
	        {
	            throw new InsufficientQuantityException("Not enough " + categoryName + " items to remove the requested quantity. " + amtRemoved + " more items needed.");
	        }
	    }

	    // Assign the updated list to items
	    while (!tempList.isEmpty()) 
	    {
	        items.add(tempList.removeLast());
	    }

	    // Return the number of items actually removed
	    return numRemoved;
	}


	/**
	 * deleteExpiredItems--
	 * Removes all expired food items from the category based on the specified date.
	 * 
	 * @param date the date to check expiration against
	 * @return a list of expired FoodItems removed from the category
	 */
	public ArrayOrderedList<FoodItem> deleteExpiredItems(Date date) 
	{
	    ArrayOrderedList<FoodItem> expiredItems = new ArrayOrderedList<FoodItem>();
	    
	    ArrayOrderedList<FoodItem> nonExpiredItems = new ArrayOrderedList<FoodItem>();

	   while(!items.isEmpty())
	    {
		   
	        FoodItem item = items.removeLast(); 

	        if (item.isExpired(date)) 
	        {
	            expiredItems.add(item); // Add expired items
	            
	        } 
	        else
	        {
	            nonExpiredItems.add(item); // Add non-expired items
	        }
	    }
	    
	    // Update the items list to contain only non-expired items
	    items = nonExpiredItems;

	    //returns an ArrayOrderedList with all expired items
	    return expiredItems;
	}

	/**
	 * equals--
	 * Compares this FoodCategory with another object. Two FoodCategory objects are considered equal
	 * if their category names are the same.
	 * 
	 * @param obj the object to compare this FoodCategory with
	 * @return true if the category names are the same, false otherwise
	 */
	public boolean equals(Object otherItem)
	{	
		return this.categoryName.equals(((FoodCategory)otherItem).categoryName);
	}

	/**
	 * getCategoryName--
	 * Gets the name of this food category.
	 * 
	 * @return the name of the category
	 */
	public String getCategoryName() 
	{
		return categoryName;
	}

	/**
	 * isEmpty--
	 * Checks if this food category has no items.
	 * 
	 * @return true if the category is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return items.size() == 0;
	}

	/**
	 * toString--
	 * Returns a string representation of the FoodCategory object. The string includes the
	 * category name and a list of all food items in the category.
	 * 
	 * @return a string representation of the FoodCategory object
	 */
	public String toString() 
	{
	    String str = "";
	    FoodItem item;

	    // Create a temporary list to store items
	    ArrayOrderedList<FoodItem> tempList = new ArrayOrderedList<FoodItem>();

	    // Create an unordered list for transferring items
	    ArrayUnorderedList<FoodItem> tempList2 = new ArrayUnorderedList<FoodItem>();

	    // Loop through items and remove last element, adding it to the rear of tempList2 (unordered list)
	    while (!items.isEmpty()) 
	    {
	        tempList2.addToRear(items.removeLast()); 
	    }

	    // Loop through the unordered tempList2 using get() to access each item
	    for (int i = 0; i < tempList2.size(); i++) 
	    {
	        item = tempList2.get(i);  // Use get() to access items in tempList2

	        // Add the item details to the string
	        str += getCategoryName() + "\n" + item.toString() + "\n";

	        // Add the item back to the ordered tempList
	        tempList.add(item);
	    }

	    // Restore the original ordered list (items) with the updated list
	    items = tempList;

	    return str;
	}

}
