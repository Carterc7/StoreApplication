package store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import com.fasterxml.jackson.databind.ObjectMapper;

import salable.Salable;

/**
 * AdminServer class to interact with client and receive commands
 * "R" command to send the inventory to client
 * "U" command to receive a new inventory from client
 * "0" command to quit 
 * @author cartercampbell
 *
 */
public class AdminServer 
{
	/**
	 * Socket and reader/writer variables
	 */
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	static StoreFront sf;
	static InventoryManager inventory = new InventoryManager();
	static InventoryService service = new InventoryService();
	
	/**
	 * Main method to start the server
	 * @param args - default
	 * @throws IOException - thrown and handled in main
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws IOException, JSONException
	{
		AdminServer server = new AdminServer(); // server object
		server.start(6666); // start server on specified port
		server.cleanup(); // cleanup all sockets and reader/writers
	}
	
	/**
	 * Method to start the server and connect to a specified port.
	 * This method also receives commands from client and responds accordingly.
	 * 
	 * @param port - port to listen on
	 * @throws IOException - Thrown in networking class
	 * @throws JSONException 
	 */
	public void start(int port) throws IOException, JSONException
	{
		// Get client connection
		System.out.println("Waiting for client connection... ");
		serverSocket  = new ServerSocket(port); // new socket on specified port
		clientSocket = serverSocket.accept(); // when client connects to server
		
		// client is connected to the server
		System.out.println("Received client connection on port " + clientSocket.getLocalPort());
		out = new PrintWriter(clientSocket.getOutputStream(), true); // define reader and writer
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		ObjectMapper objectMapper = new ObjectMapper(); // object mapper to write JSON
		List<Salable> inventory = new ArrayList<Salable>(); // creating inventory
		inventory = service.readFromFile("save.json"); // setting inventory to JSON file
		
		// Wait for command
		String inputLine;
		while((inputLine = in.readLine()) != null) // while commands are being received
		{
			if("R".equals(inputLine)) // "R" command
			{
				String reply = "";
				for(Salable salable : inventory) // iterate through the inventory
				{
					String json = objectMapper.writeValueAsString(salable); // write each salable in JSON
					reply += json; // add JSON to reply
				}
				out.println(reply); // send reply to client
				System.out.println("Inventory sent to the client.");
			}
			else if('U' == inputLine.charAt(0)) // "U" command
			{
				String[] tokens = inputLine.split("\\|"); // split message into tokens
				String json = tokens[1]; // store json inventory as string
				String[] jtokens = json.split("\\}"); // tokenize the inventory
				
				// create JSONArray with tokenized inventory and correct syntax
				JSONArray jsonArray = new JSONArray("[" + jtokens[0] + "}," + jtokens[1] + "}," + jtokens[2] + "}," + jtokens[3] + "}," + jtokens[4] + "}]");
				List<Salable> newInv = new ArrayList<Salable>(); // create a new inventory list
				for(int i = 0; i < jsonArray.length(); i++) // iterate through JSONArray
				{
					JSONObject jsonObject = jsonArray.getJSONObject(i); // create an object for each salable
					Salable salable = new Salable();
					// set salable name, description, price, and quantity from JSONObject
					salable.setName(jsonObject.getString("name"));
					salable.setDescription(jsonObject.getString("description"));
					salable.setPrice(jsonObject.getInt("price"));
					salable.setQuantity(jsonObject.getInt("quantity"));
					newInv.add(salable); // add the salable to the new inventory list
				}
				service.saveToFile("save.json", newInv); // save the new inventory
				System.out.println("File saved.");
			}
			else if("0".equals(inputLine)) // "0" command (quit)
			{
				System.out.println("Shutting the server down");
				out.println("Shutting the server down");
				break;
			}
			else // Invalid inputs
			{
				System.out.println("Invalid input.");
				out.println("Invalid input.");
			}
		}
		System.out.println("Server is shut down.");
	}
	
	/**
	 * Method to cleanup all network connections.
	 * 
	 * @throws IOException - thrown if anything bad happens in the networking class.
	 */
	public void cleanup() throws IOException
	{
		// close all input and output network buffers and sockets
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
	}

}
