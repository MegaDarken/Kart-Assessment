//File: PacketHandling.java
import java.net.*;
import java.io.*;

public class PacketHandling 
{
   static final int SERVER_PORT = 25610;
   static final int SERVER_MAX_CONNECTIONS = 8;
   
   static final int MAX_CONNECTIONS = 1;
   
   static final int SOCKET_TIMEOUT = 1000;
   
   static final String RESPONCE_STRING = "Received";
   static final String REQUEST_VERIFY_STRING = "Verify Existance";
   
   private DatagramSocket socket;
   
   private DatagramPacket currentPacket;
   
   private InetAddress[] connectionAddress;
   private int[] connectionPort;
   
   
   public PacketHandling(boolean isServer)
   {
      try
	  	{
      
      //Based on 
      if (isServer)
      {
         socket = new DatagramSocket(SERVER_PORT);
         
         connectionAddress = new InetAddress[SERVER_MAX_CONNECTIONS];
         connectionPort = new int[SERVER_MAX_CONNECTIONS];
      }
      else
      {
         socket = new DatagramSocket();
         
         connectionAddress = new InetAddress[MAX_CONNECTIONS];
         connectionPort = new int[MAX_CONNECTIONS];
      }
      
      socket.setSoTimeout(SOCKET_TIMEOUT); //In ms
      
      }
		//catch( UnknownHostException e )
		//{
			//System.err.println ("UnknownHostException: " + e );
		//}
		catch( IOException e )
		{
			System.err.println ("IOException: " + e );
		}
      
   }
   
   private void addConnection(InetAddress newAddress, int newPort)
   {
      for (int i = 0; i < SERVER_MAX_CONNECTIONS; i++)
      {
         if(connectionAddress[i] == null && connectionPort[i] == -1)
         {
            connectionAddress[i] = newAddress;
            connectionPort[i] = newPort;
            
            return;
         }
      }
   }
   
   private void removeConnection(int index)
   {
      connectionAddress[index] = null;
      connectionPort[index] = -1;
   }

   
   private boolean SendPacket(String dataSent, int hostIndex)
   {
      try
		{
			// Create a message to send using a UDP packet
			ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
			PrintStream pout = new PrintStream( bytesOut );
			pout.print(dataSent);
			
			// Get contents of message as an array of bytes
			byte[] bytesArray = bytesOut.toByteArray();
			
			// Create a datagram packet containing the byte array
			DatagramPacket packet = new DatagramPacket( bytesArray, bytesArray.length );
			 
			
         			
			// Address packet to sender
			packet.setAddress(this.connectionAddress[hostIndex]);
			packet.setPort(this.connectionPort[hostIndex]);
			
			// Send the packet - no guarantee  of delivery
			socket.send( packet );
			
			//System.out.println ("Packet sent!" );
         
         //RESPONCE
         packet = new DatagramPacket( new byte[256], 256 );
         
         //Receive packet information for responce
			socket.receive( packet );
         
         int senderPort = packet.getPort();
         InetAddress senderAddress = packet.getAddress();
         
         System.out.println ("\nData from " + senderAddress + ":" + senderPort);
         
         ByteArrayInputStream bytesIn= new ByteArrayInputStream( packet.getData());
         
         String dataReceived = "";
         
         for (int i=0; i < packet.getLength(); i++)
         { 
            int data = bytesIn.read();
            
            if(data == -1 ) 
            {
               break;
            }
            else
            {
               //System.out.print( (char) data);
               
               dataReceived += (char) data;
            }
         } 
         
         //Calculate if received correctly
         return (this.connectionAddress[hostIndex] == senderAddress && this.connectionPort[hostIndex] == senderPort) && dataReceived == RESPONCE_STRING;
         
		}
		catch( UnknownHostException e )
		{
			System.err.println ("UnknownHostException: " + e );
		}
		catch( IOException e )
		{
			System.err.println ("IOException: " + e );
		}
            
      return false;
   }
   
   
   private String ReceivePacket()
   {
   
   
      try
      {
         // Create a datagram packet for the byte array
			DatagramPacket packet = new DatagramPacket( new byte[256], 256 );
			 
         //Receive packet information
			socket.receive( packet );
         
         int senderPort = packet.getPort();
         InetAddress senderAddress = packet.getAddress();
         
         System.out.println ("\nData from " + senderAddress + ":" + senderPort);
         //System.out.println ("Sender address " + senderAddress)
         //InputStream pin = packet.getData(); // further processing
         
         ByteArrayInputStream bytesIn= new ByteArrayInputStream( packet.getData());
         
         String dataReceived = "";
         
         for (int i=0; i < packet.getLength(); i++)
         { 
            int data = bytesIn.read();
            
            if(data == -1 ) 
            {
               break;
            }
            else
            {
               //System.out.print( (char) data);
               
               dataReceived += (char) data;
            }
         } 
         
         //Is this data valid?
         
         
         // Create a message to send using a UDP packet
			ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
			PrintStream pout = new PrintStream( bytesOut );
			pout.print(RESPONCE_STRING);
			
			// Get contents of message as an array of bytes
			byte[] bytesArray = bytesOut.toByteArray();
			
			// Create a datagram packet containing the response array
			packet = new DatagramPacket( bytesArray, bytesArray.length );
         
         // Address packet to sender
			packet.setAddress( senderAddress );
			
			// Set port number to 2000
			packet.setPort( senderPort );
         
         socket.send( packet );
         
         return dataReceived;
         
      }
		catch( UnknownHostException e )
		{
			System.err.println ("UnknownHostException: " + e );
		}
		catch( IOException e )
		{
			System.err.println ("IOException: " + e );
		}
      
      return null;
   }
   
   
   public void CheckConnections()
   {
      if (isServer)
      {
         //For each client
         for (int i = 0; i < SERVER_MAX_CONNECTIONS; i++)
         {
            //Check connection
            boolean responded = this.SendPacket(REQUEST_VERIFY_STRING, i);
         
            //No responce
            if (!responded)
            {
               removeConnection(i);
            }
         }
      }
      else
      {
         //Check connection
         boolean responded = this.SendPacket(REQUEST_VERIFY_STRING, 0);
         
         //No responce
         if (!responded)
         {
            //Attempt Reconnect
            
            //Go into Timeout mode
         }
      }
   }
   
   
}