// CST-239
package store;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;




/**
 * This class operates as the main method for the salable objects and shopping cart
 * The user will navigate the menu to add or remove objects to the cart.
 * This class does not have any attributes.
 * @author cartercampbell
 *
 */
public class StoreFront
{
	/**
	 * Class objects variables to use class methods
	 */
	static InventoryManager inventory = new InventoryManager();
	static InventoryService service = new InventoryService();
	static ShoppingCart cart = new ShoppingCart();
	
	/**
	 * Main method to activate the gameMenu.
	 * @param args - main method parameter
	 */
	public static void main(String[] args) 
	{
		StoreFront a = new StoreFront();
		try
		{
			inventory.intializeInventory(); // read JSON file and initialize inventory
			ServerThread thread = new ServerThread(); // thread object
			thread.start(); // multithreading with the AdminServer
			a.gameMenu(); // calls the gameMenu method to start the game.
			
		}
		catch(IOException e)
		{
			System.out.println("IO Error");
		}
	}
	
	/**
	 * Method to display the current store inventory with correct names and quantities
	 */
	public void displayInventory()
	{
		Collections.sort(inventory.returnList()); // initialize inventory
		int j = 1; // logic variable
		System.out.println("**------------------------------------------------------**");
		System.out.println("Current Store Inventory: ");
		for(int i = 0; i < inventory.returnList().size(); i ++) // loop through the inventory
		{
			if(inventory.returnList().get(i).getQuantity() > 0) // if quantity is greater than 0, print quantity
			{
				System.out.println(j + ".) " + inventory.returnList().get(i).getName() + 
					" - Quantity: " + inventory.returnList().get(i).getQuantity()); // print each name and quantity
				j++; // increment j for formatting
			}
			else if(inventory.returnList().get(i).getQuantity() <= 0) // if quantity is = to or < 0, print SOLD OUT
			{
				System.out.println(j + ".) " + inventory.returnList().get(i).getName() + 
						" - Quantity: SOLD OUT");
				j++;
			}
		}
		System.out.println("");
		System.out.println("Please enter the corresponding number to add or remove from the cart: ");
		System.out.println("**------------------------------------------------------**");
	}
	/**
	 * gameMenu method to handle the user logic and experience.
	 * the gameMenu method used a scanner to read in user input and respond accordingly.
	 * @throws IOException - exception to be handled in the main. Thrown from FileService.
	 */
	public void gameMenu() throws IOException
	{
			Scanner scanner = new Scanner(System.in); // Initialize the scanner object
			String yesNo; // logic variable
			String addItem; // logic variable
			String removeItem; // logic variable
			
			
			System.out.println("**-----------------------**");
			System.out.println("** Welcome to the store! **"); // Welcome message
			System.out.println("**-----------------------**");
			System.out.println("");
			
			boolean active = true;
			while(active == true) // keeps menu running while active == true
			{
				// Main Menu
				System.out.println("**------------------------------------------------**");
				System.out.println("What would you like to do?");
				System.out.println("");
				System.out.println("1.) Add item to cart");
				System.out.println("2.) Remove item from the cart");
				System.out.println("3.) View Cart");
				System.out.println("4.) Empty Cart");
				System.out.println("5.) Checkout");
				System.out.println("**------------------------------------------------**");
				yesNo = scanner.next();// Gather input from the user
				
				// Switch case with user-input from Main Menu
				switch(yesNo)
				{
				case "1": // if the user wants to add an item
					// Print the current store inventory so the user can pick what item to buy
					displayInventory();
					addItem = scanner.next(); // Read in the user input
					
					switch(addItem) // switch case with the user input
					{
					case "1": // add vest
						System.out.println("");
						inventory.removeSalable(inventory.returnList().get(0));
						cart.addSalable(inventory.returnList().get(0));
						System.out.println(inventory.returnList().get(0).getName() + " found and removed from inventory");
						System.out.println(inventory.returnList().get(0).getName() + " added to the cart!");
						System.out.println("");
						break;
					case "2": // add shield
						System.out.println("");
						inventory.removeSalable(inventory.returnList().get(1));
						cart.addSalable(inventory.returnList().get(1));
						System.out.println(inventory.returnList().get(1).getName() + " found and removed from inventory");
						System.out.println(inventory.returnList().get(1).getName() + " added to the cart!");
						System.out.println("");
						break;
					case "3": // add health pack
						System.out.println("");
						inventory.removeSalable(inventory.returnList().get(2));
						cart.addSalable(inventory.returnList().get(2));
						System.out.println(inventory.returnList().get(2).getName() + " found and removed from inventory");
						System.out.println(inventory.returnList().get(2).getName() + " added to the cart!");
						System.out.println("");
						break;
					case "4": // add sword
						System.out.println("");
						inventory.removeSalable(inventory.returnList().get(3));
						cart.addSalable(inventory.returnList().get(3));
						System.out.println(inventory.returnList().get(3).getName() + " found and removed from inventory");
						System.out.println(inventory.returnList().get(3).getName() + " added to the cart!");
						System.out.println("");
						break;
					case "5": // add staff
						System.out.println("");
						inventory.removeSalable(inventory.returnList().get(4));
						cart.addSalable(inventory.returnList().get(4));
						System.out.println(inventory.returnList().get(4).getName() + " found and removed from inventory");
						System.out.println(inventory.returnList().get(4).getName() + " added to the cart!");
						System.out.println("");
						break;
					default: // default (input error)
						System.out.println("");
						System.out.println("Invalid Input.");
						System.out.println("");
						break;
					
					}
					System.out.println("");
					break;
				case "2": // if the user wants to remove from the cart
						displayInventory();
						removeItem = scanner.next(); // read in user input
						switch(removeItem) // switch case for what item they want to remove
						{
						case "1": // remove vest
							System.out.println("");
							inventory.addSalable(inventory.returnList().get(0));
							cart.removeSalable(inventory.returnList().get(0));
							System.out.println(inventory.returnList().get(0).getName() + " found and returned to inventory");
							System.out.println(inventory.returnList().get(0).getName() + " removed from cart!");
							System.out.println("");
							break;
						case "2": // remove shield
							System.out.println("");
							inventory.addSalable(inventory.returnList().get(1));
							cart.removeSalable(inventory.returnList().get(1));
							System.out.println(inventory.returnList().get(1).getName() + " found and returned to inventory");
							System.out.println(inventory.returnList().get(1).getName() + " removed from cart!");
							System.out.println("");
							break;
						case "3": // remove health pack
							System.out.println("");
							inventory.addSalable(inventory.returnList().get(2));
							cart.removeSalable(inventory.returnList().get(2));
							System.out.println(inventory.returnList().get(2).getName() + " found and returned to inventory");
							System.out.println(inventory.returnList().get(2).getName() + " removed from cart!");
							System.out.println("");
							break;
						case "4": // remove sword
							System.out.println("");
							inventory.addSalable(inventory.returnList().get(3));
							cart.removeSalable(inventory.returnList().get(3));
							System.out.println(inventory.returnList().get(3).getName() + " found and returned to inventory");
							System.out.println(inventory.returnList().get(3).getName() + " removed from cart!");
							System.out.println("");
							break;
						case "5": // remove staff
							System.out.println("");
							inventory.addSalable(inventory.returnList().get(4));
							cart.removeSalable(inventory.returnList().get(4));
							System.out.println(inventory.returnList().get(4).getName() + " found and returned to inventory");
							System.out.println(inventory.returnList().get(4).getName() + " removed from cart!");
							System.out.println("");
							break;	
						default: // default (input error)
							System.out.println("");
							System.out.println("Invalid Input.");
							System.out.println("");
							break;
						
						}
						System.out.println("");
						break;
				case "3": // if the user wants to view the cart
					System.out.println("");
					System.out.println("**------------------------------------**");
					boolean answer = cart.viewCart(cart);
					if(answer == true) // check if cart is empty
					{
						System.out.println("The cart is empty");
					}
					else // if "not empty" iterate through and print out each element
					{
						System.out.println("Your cart: \n");
						for(int i = 0; i < cart.shoppingCart.size(); i++)
						{
							System.out.println(cart.shoppingCart.get(i).getName());
						}
					}
					System.out.println("**------------------------------------**");
					System.out.println("");
					break;
				case "4": // if the user wants to empty the cart
					System.out.println("");
					System.out.println("**------------------------------------**");
					cart.emptyCart(cart);
					System.out.println("Cart is emptied.");
					System.out.println("**------------------------------------**");
					System.out.println("");
					break;
				case "5": // if the user wants to checkout
					service.saveToFile("save.json", inventory.returnList());
					service.readFromFile("save.json");
					System.out.println("");
					System.out.println("**------------------------------------**");
					System.out.println("**         Checkout Complete!         **");                      
					System.out.println("**------------------------------------**");
					active = false;
					break;
				default: // invalid input
					System.out.println("**------------------------------------**");
					System.out.println("**           Invalid Input            **");
					System.out.println("**------------------------------------** \n");
						
					}
				}
				scanner.close();
			}

		}
	


