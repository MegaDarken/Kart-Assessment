//File: PacketHandling.java
import java.net.*;
import java.io.*;

public class PacketHandling 
{
   static final int SERVER_PORT = 25610;
   static final int SERVER_MAX_CONNECTIONS = 8;
   
   static final int MAX_CONNECTIONS = 1;
   
   
   private DatagramSocket socket;
   
   private DatagramPacket currentPacket;
   
   private String[] connectionAddress;
   private String[] connectionPort;
   
   
   public PacketHandling(boolean isServer)
   {
      try
	  	{
      
      //Based on 
      if (isServer)
      {
         socket = new DatagramSocket(SERVER_PORT);
         
         connectionAddress = new String[SERVER_MAX_CONNECTIONS];
         connectionPort = new String[SERVER_MAX_CONNECTIONS];
      }
      else
      {
         socket = new DatagramSocket();
         
         connectionAddress = new String[MAX_CONNECTIONS];
         connectionPort = new String[MAX_CONNECTIONS];
      }
      }
		catch( UnknownHostException e )
		{
			System.err.println ("UnknownHostException: " + e );
		}
		catch( IOException e )
		{
			System.err.println ("IOException: " + e );
		}
      
   }
   
   
   
}