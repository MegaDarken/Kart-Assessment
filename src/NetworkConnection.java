//File: NetworkConnection.java
import java.io.*;
import java.net.*;
import java.util.*;
//import java.lang.*;
//import java.util.concurrent.*;

class NetworkConnection implements Runnable
{
   //Const
   private final String REQUEST_CONTROL = "control";
   private final String REQUEST_KART = "kart";
   
   private final String SPLIT_CHAR = " ";

   //Attribute(s)
   private boolean connected;

   private InetAddress hostAddress;
   private int hostPort;
   
   private Scanner scanner;// = new Scanner(System.in);
   
   private Socket clientSocket = null;
   private DataOutputStream outputStream = null;
   private String request;
   private int index;
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
      
      System.out.println("Attempt Connection: " + hostAddress + ":" + hostPort);
		
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
            clientSocket.getInputStream()
         );
         
         connected = true;
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
      System.out.println("Request: " + request);
   
		// check the socket
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
            String line = null;
            
            do 
            {
               index = index % AssessMode.world.GetKarts().length;
            
               /*
               System.out.print("CLIENT: ");
               request = scanner.nextLine(); 

				   outputStream.writeBytes( request + "\n" );
            
   				if((responseLine = inputStream.readLine()) != null)
   				{
   					System.out.println("SERVER: " + responseLine);
   				}
               */
               
               //Determine action
               request = REQUEST_KART;
               
               System.out.print("Loop_");
               
               System.out.print("Sending: " + index);
               sendMessage(request + SPLIT_CHAR + index);
               
               AttemptSleep(10);
                  
               sendRequest();
               
               //if line is received?
               if ((line = receiveMessage()) != null)
               {
                  System.out.print("Receiving: " + index);
                  
                  //Split line into parts
                  String[] splitLine = line.split(SPLIT_CHAR);
                  
                  //Check length
                  if(line.length() > 1)
                  {
                     request = splitLine[0];
                     index = Integer.parseInt(splitLine[1]);
                     
                     AttemptSleep(10);
                     
                     receiveRequest();
                  }
               }
               
               
               
               if ( request.equals("CLOSE") )
               {
                  break;
               }
               
               AttemptSleep(10);
               
               index++;
               
            } while(connected);
            
								
				// close the input/output streams and socket
				CloseConnections();
			}
			catch (Exception e)
			{
				System.err.println("Exception:  " + e);
			}
		}
      else
      {
         System.out.println("Failed to run due to 'null' element");
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
   
   public void CloseConnections()
   {
      try
      {
         outputStream.close();
   		inputStream.close();
         
         outputObject.close();
   		inputObject.close();
   
         
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
   
   //REQUEST HANDLING
   
   private void sendRequest()
   {
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
               System.out.println("(Send:Send1)");
				   sendMessage( request + SPLIT_CHAR + index );
            
               System.out.println("(Send:Reponce1)");
   				if((responseLine = receiveMessage()) != null)
   				{
   					System.out.println("SERVER: " + responseLine);
   				}
               
               System.out.println("(Send:Send2)");
               sendMessage( request + SPLIT_CHAR + index );
               
               if ( request.equals("CLOSE") )
               {
                  //Send signal to exit
                  //break;
               }
               */
               
               System.out.println("(Send:Reponce2File)");
               switch(request)
               {
                  case REQUEST_CONTROL:
                     
                     //Get object
                     receiveControl();
                     
                     break;
                  
                  case REQUEST_KART:
                     
                     //Get object
                     receiveKart();
                     
                     break;
                  
                  default:
                     String currentObject = (String)inputObject.readObject();
                     System.out.println("Object Defaulted: " + currentObject);
               }
               
               //AttemptSleep(10);
               
            //} while(true);
            
								
				// close the input/output streams and socket
				//outputStream.close();
				//inputStream.close();
				//clientSocket.close();
			}
         catch (ClassNotFoundException e)
         {
            System.err.println("Class not found: " + e);
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
   
   private void receiveRequest()
   {
      if (
         clientSocket != null && 
         outputStream != null && 
         inputStream != null &&
         outputObject != null &&
         inputObject != null
      ) {
      
      String line = "";
            
         try 
         {
            /*
               // get object and cast it to a Kart (serializable class)
               Kart kart = (Kart) inputObject.readObject();
               
               // test out the kart
               System.out.println( "Kart name: " + kart.getName() );
            } catch (ClassNotFoundException e) 
            {
               
            }
            */
            //Connection Loop
            //do
            //{
               /*
      			if((line = receiveMessage()) != null)
      			{
      				
                  sendMessage( line );
      			}
               
               line = receiveMessage();
               
               if ( line.equals("CLOSE") )
               {
                  //Send signal to exit
                  //break
               }
               
               //Split line into parts
               String[] splitLine = line.split(SPLIT_CHAR);
               
               //Check length
               request = splitLine[0];
               int currentIndex = Integer.parseInt(splitLine[1]);
               */
               
               switch(request)
               {
                  case REQUEST_CONTROL:
                     
                     //Get object
                     
                     sendControl();
                     break;
                  
                  case REQUEST_KART:
                     
                     //Get object
                     //AssessMode.world.GetKarts()[index];//RaceKart currentKart = (RaceKart) inputObject.readObject();
                     
                     // write object to stream
                     sendKart();
            
                     // send it
                     //outputObject.flush();
                     break;
               
               }

               
               //AttemptSleep(1);
   
            //} while(true);
            
   			
   			// Comment out/remove the outputStream and server close statements if server should remain live
   			//outputStream.close();
   			//inputStream.close();
            
         }
         catch (Exception e)
         {
            System.out.print("Exception thown." + e);
         }
      }
   }
   
   
   private void sendMessage(String message)
   {
      try
      {
         outputStream.writeBytes( message + "\n" );
      }
      catch (IOException e)
		{
			System.err.println("IOException:  " + e);
         connected = false;
      }
   }
   
   private String receiveMessage() 
   {
      try 
      {
         return inputStream.readLine();
         
      } 
      catch (IOException e)
		{
			System.err.println("IOException:  " + e);
         connected = false;
         return null;
      }
   }
   
   private void sendKart()
   {
      try
      {
         // write object to stream
         outputObject.writeObject(AssessMode.world.GetKarts()[index]);
   
         // send it
         outputObject.flush();
      }
		catch (IOException e)
		{
			System.err.println("IOException:  " + e);
         connected = false;
		}
   }
   
   private void receiveKart()
   {
      try
      {
         //Collect kart
         RaceKart currentKart = (RaceKart) inputObject.readObject();
         
         //Place into world
         AssessMode.world.GetKarts()[index] = currentKart;
      }
      catch (ClassNotFoundException e)
      {
         System.err.println("Class not found: " + e);
      }
		catch (IOException e)
		{
			System.err.println("IOException:  " + e);
         connected = false;
		}
   }
   
   private void sendControl()
   {
      try
      {
         // write object to stream
         outputObject.writeObject(AssessMode.world.GetControls()[index]);
      
         // send it
         outputObject.flush();
      }
		catch (IOException e)
		{
			System.err.println("IOException:  " + e);
         connected = false;
		}
   }
   
   private void receiveControl()
   {
      try
      {
         //Collect control
         byte[] currentControl = (byte[]) inputObject.readObject();
         
         //Place into world
         AssessMode.world.GetControls()[index] = currentControl;
      }
      catch (ClassNotFoundException e)
      {
         System.err.println("Class not found: " + e);
      }
		catch (IOException e)
		{
			System.err.println("IOException:  " + e);
         connected = false;
		}
   }
   
   
   private void AttemptSleep(int duration)
   {
      try
      {
         Thread.sleep(duration);
      }
      catch(Exception e)
      {
         System.out.print("Exception thrown for Thread.sleep: " + e);
      }
   }
}