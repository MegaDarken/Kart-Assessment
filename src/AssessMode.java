//File: AssessMode.java
import java.util.*;

class AssessMode
{
   //Constants
   static final String ARGUMENT_SERVER = "-server";

   static final String PROGRAM_NAME = "Kart Assignment";

   //Vars
   static boolean AsServer;
   
   static GameWorld world;
   
   //


   public static void main(String[] args)
   {
      System.out.println("Starting " + PROGRAM_NAME);
      
      //Determine running mode
      AsServer = Arrays.asList(args).contains(ARGUMENT_SERVER);
      
      world = new GameWorld();
      
      if (!AsServer)
      {
      //Launch Window
      DisplayPanel window = new DisplayPanel();
      window.setVisible(true);
      
      }
   }
}