//File: PacketHandling.java
import java.net.*;
import java.io.*;

public class PacketHandling 
{
   static final int SERVER_PORT = 25610;
   
   private DatagramSocket socket;
   
   private DatagramPacket currentPacket;
   
   
   public PacketHandling(boolian isServer)
   {
      if (isServer)
      {
         socket = new DatagramSocket(SERVER_PORT);
      }
      else
      {
         socket = new DatagramSocket();
      }
      
   }
   
}