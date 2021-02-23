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
      
      //Debug
      Karts[0] = new RaceKart("Red", 1, 1, 1);
      Karts[0].SetPosition(10, 10);
      
      Karts[1] = new RaceKart("Green", 1, 1, 1);
      Karts[1].SetPosition(110, 10);
      
      Karts[2] = new RaceKart("Blue", 1, 1, 1);
      Karts[2].SetPosition(10, 110);
      
      Karts[3] = new RaceKart("Red", 1, 1, 1);
      Karts[3].SetPosition(110, 110);
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
      for(int kart = 0; kart < MAXIMUM_RACEKARTS; kart++)
      {
         
         Karts[kart].TickForward(new byte[2]);
         
         //Get Kart Image
         
         //Get Corrisonding label
         
         // Apply Image to label
         
      }
      
      world.UpdateKartImages(Karts);
   }
}