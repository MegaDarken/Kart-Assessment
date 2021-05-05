//File: PacketHandling.java
import java.net.*;
import java.io.*;

public class PacketHandling 
{
   static final int DEFAULT_SERVER_PORT = 25610;
   //static final int SERVER_MAX_CONNECTIONS = 8;
   
   static final int MAX_CONNECTIONS = 1;
   
   static final int SOCKET_TIMEOUT = 1000;
   
   static final byte RESPONCE_STRING = (byte)'R';//"Received";
   static final byte REQUEST_VERIFY_STRING = (byte)'V';//"Verify Existance";
   
   
   private DatagramSocket socket;
   
   private DatagramPacket currentPacket;
   
   //private InetAddress[] connectionAddress;
   //private int[] connectionPort;
   
   private InetAddress serverAddress;
   private int serverPort;
   
   private NetworkConnection serverConnection;
   
   public PacketHandling(InetAddress serverAddress, int serverPort)
   {
      this.serverAddress = serverAddress;
      this.serverPort = serverPort;
      
      serverConnection = new NetworkConnection(this.serverAddress, this.serverPort);
      
      //Run connection
      Thread connectionThread = new Thread(serverConnection);
      connectionThread.start();
   }
   
   public PacketHandling(InetAddress serverAddress)
   {
      this.serverAddress = serverAddress;
      this.serverPort = DEFAULT_SERVER_PORT;
      
      //Create Connection
      serverConnection = new NetworkConnection(this.serverAddress, this.serverPort);
      
      //Run connection
      Thread connectionThread = new Thread(serverConnection);
      connectionThread.start();
   }
   
   
}