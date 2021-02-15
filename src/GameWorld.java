//File: GameWorld.java


public class GameWorld
{
   //Constants
   private final int MAXIMUM_RACEKARTS = 4;


   //Game Objects
   private RaceKart[] Karts;
   
   public GameWorld()
   {
      if (AssessMode.AsServer)
      {
      
      }
      
      Karts = new RaceKart[MAXIMUM_RACEKARTS];
   }
   
   
}