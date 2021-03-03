//File: AssessMode.java
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class AssessMode extends JFrame
{
   //Constants
   static final String ARGUMENT_SERVER = "-server";

   
   
   //Static Vars
   static boolean AsServer;
   
   static GameWorld world;
   
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
      AsServer = Arrays.asList(args).contains(ARGUMENT_SERVER);
      
      world = new GameWorld();
      
      world.SetClientKart(1);
      
      if (!AsServer)
      {
         System.out.println("Client, Opening game window...");
      
         //Launch Window
         frame = new ClientFrame();
         frame.setVisible(true);
      
      }
      else
      {
         System.out.println("Server, Starting...");
         
         
      }
   }
}