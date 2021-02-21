//File: GameWorld.java


public class GameWorld
{
   //Constants
   private final int MAXIMUM_RACEKARTS = 4;


   //Game Objects
   private RaceKart[] Karts;
   private int clientKart;
   
   public GameWorld()
   {
      if (AssessMode.AsServer)
      {
      
      }
      
      Karts = new RaceKart[MAXIMUM_RACEKARTS];
   }
   
   public int GetClientKart()
   {
      return this.clientKart;
   }
   
   public void SetClientKart(int value)
   {
      this.clientKart = value;
   }
   
   private void MoveKarts()
   {
      //For each kart in GameWorld
      
      //Get Kart Image
      
      //Get Corrisonding label
      
      // Apply Image to label
      
   }
}