# Food Inventory Management System

A Java inventory system that manages food items using ordered and unordered lists. The app reads an inventory file and a daily transaction log to track stock levels and automatically removes expired items.

## How It Works

The program loads an initial food inventory from a file, then processes a daily transaction log of commands that update stock in real time. Expired items are automatically removed using queue-based processing.

### Input Files

- **inventory.txt** — starting food inventory with item name, quantity, and expiration date
- **dailylog.txt** — daily transactions with a command, food item, quantity, and expiration date

## Project Structure
├── FoodItem.java                    # Represents a food item with quantity and expiration date
├── FoodCategory.java                # Groups food items by category
├── Date.java                        # Date representation for expiration tracking
├── DateException.java               # Custom exception for invalid dates
├── InsufficientQuantityException.java  # Custom exception for stock shortfalls
└── Project4.java                    # Entry point, loads files and runs the system

## How to Run

1. Clone the repository
2. Open the project in **Eclipse**
3. Ensure `inventory.txt` and `dailylog.txt` are in the project directory
4. Run `Project4.java`

## Concepts Demonstrated

- Ordered and unordered list data structures
- Queue-based expiration date processing
- File I/O and transaction log parsing
- Custom exceptions for domain-specific error handling
- Object-oriented design
