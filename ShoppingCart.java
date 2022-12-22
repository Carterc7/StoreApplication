// CST-239
package store;
import java.util.ArrayList;
import salable.Salable;


/**
 * The ShoppingCart class represent a shopping cart object.
 * The cart is capable of adding or removing armor, health, or weapons.
 * The cart's attribute is an ArrayList of salables
 * @author cartercampbell
 *
 *
 */
public class ShoppingCart 
{
	/**
	 * Attribute of the shopping cart
	 * Single ArrayList to hold the salable objects in the cart
	 */
	ArrayList<Salable> shoppingCart = new ArrayList<Salable>(5);
	
	/**
	 * Method to add a salable object into the shopping cart.
	 * @param a - the object being added
	 */
	public void addSalable(Salable a)
	{
		shoppingCart.add(a);
	}
	
	/**
	 * Method to remove a salable object from the shopping cart.
	 * @param a - the object being removed
	 */
	public void removeSalable(Salable a)
	{
		shoppingCart.remove(a);
	}
	
	/**
	 * Method to empty the contents of the shopping cart
	 * @param a - shopping cart object that we are emptying
	 */
	public void emptyCart(ShoppingCart a)
	{
		shoppingCart.removeAll(shoppingCart);
	}
	
	/**
	 * Method to return the contents of the shopping cart.
	 * @return - returns the shopping cart.
	 */
	public ArrayList<Salable> returnList()
	{
		return shoppingCart;
	}
	
	/**
	 * Method to view the contents of the user's cart
	 * @param a - the ShoppingCart we are viewing
	 * @return - a boolean to determine if the cart is empty or not
	 */
	public boolean viewCart(ShoppingCart a)
	{
		boolean answer = shoppingCart.isEmpty();
		return answer;
	}
	
}

