//File: AssessMode.java
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

public class AssessMode
{
   //Constants
   static final String ARGUMENT_SERVER = "-server";

   
   
   //Static Vars
   static boolean AsServer;
   
   static GameWorld world;
   
   static PacketHandling connectionHandle;
   
   //Vars
   static private ClientFrame frame;
   
   static public ClientFrame GetFrame()
   {
      return frame;
   } 

   



   public static void main(String[] args)
   {
      System.out.println("Starting Assessment Client");
      
      //Determine running mode
      //AsServer = Arrays.asList(args).contains(ARGUMENT_SERVER);
      
      //Get Server address
      InetAddress serverAddress = null;
      int serverPort = -1;
      
      //Address arg?
      if (args.length > 0)
      {
         try
         {
            serverAddress = InetAddress.getByName(args[0]);
            
            //Port arg?
            if (args.length > 1)
            {
               serverPort = Integer.parseInt(args[1]);
               
               connectionHandle = new PacketHandling(serverAddress, serverPort);
            }
            else
            {
               connectionHandle = new PacketHandling(serverAddress);
            }
         }
         catch (UnknownHostException e)
         {
            System.out.println("Unknown host:" + args[0]);
         }
         catch (Exception e)
         {
            System.out.println(e);
         }
      }
      else
      {
         //No Address...
         
         //Request from user
         Scanner scan = new Scanner(System.in);
         String userInput = "";
         
         try
         {
            System.out.println("Server Address:");
            userInput = scan.nextLine();  // Read user input
            
            serverAddress = InetAddress.getByName(userInput);
            
            connectionHandle = new PacketHandling(serverAddress);
         }
         catch (UnknownHostException e)
         {
            System.out.println("Unknown host:" + userInput);
         }
         catch (Exception e)
         {
            System.out.println(e);
         }
         

      }
      
      //connectionHandle = new PacketHandling();
      
      world = new GameWorld();
      
      world.SetClientKart(1);//DEBUG
      
      
      
      //if (!AsServer)
      //{
      System.out.println("Client, Opening game window...");
   
      //Launch Window
      frame = new ClientFrame();
      frame.setVisible(true);
      
      //}
      
   }
}