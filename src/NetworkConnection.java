//File: NetworkConnection.java
import java.net.*;

class NetworkConnection
{
   public InetAddress host = null;
   
   public NetworkConnection()
   {
      //Connection type to server/client?
      try
      {
      
      }
      catch(UnknownHostException e)
      {
         System.out.println("ERR: Unable to resolve name and address");
      }
   }
}