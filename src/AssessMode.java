//File: AssessMode.java
import java.util.*;

class AssessMode
{
   //Constants
   static final String ARGUMENT_SERVER = "-server";

   static final String PROGRAM_NAME = "Kart Assignment";

   public static void main(String[] args)
   {
      System.out.println("Starting " + PROGRAM_NAME);
      
      //Determine running mode
      boolean asServer = Arrays.asList(args).contains(ARGUMENT_SERVER);
      
      
   }
}