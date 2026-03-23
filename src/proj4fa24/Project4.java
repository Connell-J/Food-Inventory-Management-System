package proj4fa24;

import java.io.File;
import exceptionclasses.ElementNotFoundException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import lists.ArrayUnorderedList;
import lists.ArrayOrderedList;

/**
 * <p>Title: Project4 Class</p>
 * 
 * <p>Description: The Project4 class serves as the main entry point for the inventory management system. 
 * It processes an initial inventory file and a daily log file containing transactions 
 * to manage food categories and their associated items in the inventory.  </p>
 * 
 * @author Jesse Connell
 */
public class Project4 
{
	private static ArrayUnorderedList<FoodCategory> inv = new ArrayUnorderedList<FoodCategory>(); //The inventory list containing all food categories.
	private static File invFile = new File("inventory.txt"); // The file containing the initial inventory data. 
	private static File dailyLogFile = new File("dailylog.txt"); // The file containing the daily transaction log.

	/**
	 * main--
     * The main method initializes the inventory from the inventory file and 
     * processes daily transactions from the transaction log file. 
     * It displays the initial inventory and outputs the results of the transactions.
     *
     */
	public static void main(String args[])
	{
		System.out.println("INITIAL INVENTORY: ");
		System.out.println(addCatagory(inv, invFile));

		System.out.println("BEGINNING TRANSACTIONS: ");
		processTransactions(dailyLogFile);
		System.out.println("--END OF TRANSACTIONS--");
	}

	/**
	 * addCatagory --
	 * Adds food categories to the inventory from the specified file. 
	 * The file contains information about categories, quantities, and expiration dates.
	 *  
	 *  //description//
	 *  
	 * @param inventory
	 * @param theFile
	 * @return inventory list
	 */

	private static ArrayUnorderedList<FoodCategory> addCatagory(ArrayUnorderedList<FoodCategory> inventory, File theFile )
	{
		try 
		{
			Scanner scanner = new Scanner(theFile);

			while (scanner.hasNext()) 
			{
				String line = scanner.nextLine();

				String[] itemParts = line.split(" ");

				String categoryName = itemParts[0];

				int quantity = Integer.parseInt(itemParts[1]);

				String date = itemParts[2];
				String[] dateString = date.split("/");
				int[] dateParts = new int[3];

				for (int i = 0; i < dateString.length; i++) 
				{
					dateParts[i] = Integer.parseInt(dateString[i]);
				}

				Date expirationDate = null;

				try 
				{
					expirationDate = new Date(dateParts[0], dateParts[1], dateParts[2]);

				}

				catch (DateException ex) 
				{
					System.out.println("DateException: " + ex.getMessage());
				}

				// Add item to inventory
				addItemToInventory(inv, categoryName, quantity, expirationDate);
			}

			scanner.close();

		}
		catch (FileNotFoundException ex) 
		{
			System.out.println("FileNotFoundException: " + ex.getMessage());
		} 		
		catch (NumberFormatException ex) 
		{
			System.out.println("NumberFormatException: " + ex.getMessage());
		}

		return inv;
	}

	/**
	 * addItemsToInventory --
	 * Adds a FoodItem to the inventory list. Updates quantity if the expiration date exists.
	 *
	 * @param inventory -  the list of FoodCategory objects
	 * @param categoryName -  the name of the food category
	 * @param quantity - the quantity of the food item
	 * @param expirationDate - the expiration date of the food item
	 */
	private static void addItemToInventory(ArrayUnorderedList<FoodCategory> inv, String categoryName, int quantity, Date expirationDate) 
	{
		FoodCategory targetCategory = null;

		// Search for the category in the inventory
		for (int i = 0; i < inv.size(); i++) 
		{
			FoodCategory currentCategory = inv.get(i);

			if (currentCategory.getCategoryName().equals(categoryName)) 
			{
				targetCategory = currentCategory; // Found the target category
			}
		}

		// If the target category does not exist, create it
		if (targetCategory == null) 
		{
			targetCategory = new FoodCategory(categoryName);
			inv.addToRear(targetCategory); // Add the new category to the inventory
		}

		// Add the new food item to the target category
		targetCategory.addItem(new FoodItem(quantity, expirationDate));
	}

	/**
	 *  processTransactions --
	 * Processes transactions from a given file, updating the inventory accordingly.
	 * Transactions can include adding new items, removing items for guests, or 
	 * checking and removing expired items.
	 *
	 * @param theFile the file containing the list of transactions to process.
	 */
	public static void processTransactions(File theFile) 
	{
		try {
			Scanner scanner = new Scanner(theFile);

			while (scanner.hasNextLine()) 
			{
				String line = scanner.nextLine();

				if (line.length() > 0)
				{
					String[] parts = line.split(" ", 3);
					String transactionType = parts[0];

					if (transactionType.equals("a")) 
					{
						// Addition of donations to the inventory
						String categoryName = parts[1];
						String[] itemParts = parts[2].split(" ", 2);
						int quantity = Integer.parseInt(itemParts[0]);

						String[] dateString = itemParts[1].split("/");

						int[] dateParts = new int[3];

						for (int i = 0; i < dateString.length; i++) 
						{
							dateParts[i] = Integer.parseInt(dateString[i]);
						}

						Date expirationDate = null;

						try 
						{
							expirationDate = new Date(dateParts[0], dateParts[1], dateParts[2]);
							addItemToInventory(inv, categoryName, quantity, expirationDate);
						}
						catch (DateException ex) 
						{
							System.out.println("DateException: " + ex.getMessage());
						}
					} 
					else if (transactionType.equals("d")) 
					{
						// Deletion of items when a guest visits
						String categoryName = parts[1];
						int quantity = Integer.parseInt(parts[2]);

						FoodCategory targetCategory = null;
						for (int i = 0; i < inv.size(); i++) 
						{
							if (inv.get(i).getCategoryName().equals(categoryName)) 
							{
								targetCategory = inv.get(i);
							}
						}

						if (targetCategory == null) 
						{
							try {
					        throw new ElementNotFoundException("Category not found in inventory: " + categoryName);
							}
							catch(ElementNotFoundException ex)
							{
								System.out.println("ElementNotFoundException: " + ex.getMessage() + "\n");
								continue;
							}

						}

						try 
						{
							int removedQuantity = targetCategory.deleteItems(quantity);
							System.out.println("Removed " + removedQuantity + " items from " + categoryName);

							// Remove category if all items are deleted
							if (targetCategory.isEmpty())
							{
								inv.remove(targetCategory);
								System.out.println("\nCategory " + categoryName + " removed as it is now empty.");
							}
						}
						catch (InsufficientQuantityException ex) 
						{
							System.out.println("InsufficientQuantityException: " + ex.getMessage() + "\n");
						}
					} 
					else if (transactionType.equals("c")) 
					{
						String[] dateString = parts[1].split("/");
						int[] dateParts = new int[3];
						for (int i = 0; i < dateString.length; i++) 
						{
							dateParts[i] = Integer.parseInt(dateString[i]);
						}

						Date checkDate = null;

						try 
						{
							checkDate = new Date(dateParts[0], dateParts[1], dateParts[2]);
						}
						catch (DateException ex) 
						{
							System.out.println("DateException: " + ex.getMessage());
						}

						for (int i = 0; i < inv.size(); i++) 
						{
							FoodCategory category = inv.get(i);

							ArrayOrderedList<FoodItem> expiredItems = category.deleteExpiredItems(checkDate);

							if (!expiredItems.isEmpty()) 
							{
								System.out.println("Expired items removed from " + category.getCategoryName() + ": Current date " + checkDate);

								while (!expiredItems.isEmpty()) 
								{
									System.out.println(expiredItems.removeLast().toString() + "\n");
								}
							}
						}
					} 
				}
				System.out.println("Updated Inventory:\n" + inv);
			}


			scanner.close();
		} 
		catch (FileNotFoundException ex) 
		{
			System.out.println("FileNotFoundException: " + ex.getMessage());
		} 
		catch (NumberFormatException ex) 
		{
			System.out.println("NumberFormatException: " + ex.getMessage());
		}
	}

}