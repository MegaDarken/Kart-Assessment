//File: NetworkConnection.java
import java.io.*;
import java.net.*;
import java.util.*;
//import java.lang.*;
//import java.util.concurrent.*;

class NetworkConnection implements Runnable
{
   //Const
   //private final String REQUEST_CONTROL = "control";
   //private final String REQUEST_KART = "kart";
   

   //Attribute(s)

   private InetAddress hostAddress;
   private int hostPort;
   
   private Scanner scanner;// = new Scanner(System.in);
   
   private Socket clientSocket = null;
   private DataOutputStream outputStream = null;
   private String request;
   private BufferedReader inputStream = null;
	private String responseLine;
      
   private ObjectOutput outputObject = null;
   private ObjectInput inputObject = null;
   
   public NetworkConnection(InetAddress hostAddress, int hostPort)
   {
      //Connection type to server/client?
      // try
//       {
//          //host = InetAddress.getLocalHost();//Self
         this.hostAddress = hostAddress;
         this.hostPort = hostPort;
//       }
//       catch(UnknownHostException e)
//       {
//          System.out.println("ERR: Unable to resolve name and address");
//       }
      scanner = new Scanner(System.in);

		
		try
		{
			clientSocket = new Socket(hostAddress, hostPort);
			
			outputStream = new DataOutputStream(
				clientSocket.getOutputStream()
			);
			
			inputStream = new BufferedReader(
				new InputStreamReader(
					clientSocket.getInputStream()
				)
			);
         
         outputObject = new ObjectOutputStream(
            clientSocket.getOutputStream()
         );
         
         inputObject = new ObjectInputStream(
            server.getInputStream()
         );
		} 
		catch (UnknownHostException e)
		{
			System.err.println("Unknown host: " + hostAddress);
		}
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O for the connection to: " + hostAddress);
		}
   }

   public void run()
   {
		// Write data to the socket
		if (
         clientSocket != null && 
         outputStream != null && 
         inputStream != null &&
         outputObject != null &&
         inputObject != null
      ) {
			try
			{
         /*
            // setup serializable object - this would normally be done somewhere
            //    where the object can be easily used by the client, not just
            //    before sending it through the socket
            Kart kart = new Kart( "Kart1" );
            
            // write object to stream
            output.writeObject( kart );
            
            // send it
            output.flush();
            */
            
            do 
            {
               System.out.print("CLIENT: ");
               request = scanner.nextLine(); 

				   outputStream.writeBytes( request + "\n" );
            
   				if((responseLine = inputStream.readLine()) != null)
   				{
   					System.out.println("SERVER: " + responseLine);
   				}
               
               if ( request.equals("CLOSE") )
               {
                  break;
               }
            } while(true);
            
								
				// close the input/output streams and socket
				outputStream.close();
				inputStream.close();
				clientSocket.close();
			}
			catch (UnknownHostException e)
			{
				System.err.println("Trying to connect to unknown host: " + e);
			}
			catch (IOException e)
			{
				System.err.println("IOException:  " + e);
			}
		}
   }
   
   public InetAddress GetHostAddress()
   {
      return this.hostAddress;
   }
   
   public void SetHostAddress(InetAddress hostAddress)
   {
      this.hostAddress = hostAddress;
   }
   
   public int GetHostPort()
   {
      return this.hostPort;
   }
   
   public void SetHostPort(int hostPort)
   {
      this.hostPort = hostPort;
   }
   
}