# StoreApplication
Store application that allows the user to manage an inventory and purchase items. Admin functionality to update the inventory.

StoreFront runs all UI and logic and holds the main method for the application.
InventoryManager initializes the inventory upon start and holds methods to add and remove from the inventory.
ShoppingCart initializes the users cart and holds methods to view the cart, add, and remove from the cart, empty the cart, and return the cart.
InventoryService saves the inventory to a file in JSON format ("save.json") and reads the file to initialize the inventory. 
AdminServer is the server class that can connect to a client and run commands.
ServerThread runs the AdminServer on a thread and allows the user to run the StoreFront without connecting to a client. 
Salable is the base-class for the store products and has getters, setter, a non-default, and default constructor.
Health, Weapon, and Armor are the sub-classes of Salable and have their own attributes.
