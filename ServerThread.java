package store;

import java.io.IOException;

import org.json.JSONException;



/**
 * Class to start the server using overridden run method
 * @author cartercampbell
 *
 */
public class ServerThread extends Thread
{
	/**
	 * Overridden run method to start the server using threads
	 */
	public void run()
	{
		// Create server object
		// start the server on port 6666
		// cleanup the server by closing all buffers and ports
		AdminServer server = new AdminServer();
		try
		{
			server.start(6666);
			server.cleanup();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(JSONException j)
		{
			j.printStackTrace();
		}
	}
}

