//File: RaceKart.java


public class RaceKart
{
   //Attributes that should stay the same
   private String livery;
   private int weight;
   
   //Attributes that should change
   private float xPosition;
   private float yPosition;
   
   private float xVelocity;
   private float yVelocity;
   
   private float Bearing;
   
   
   public RaceKart()
   {
   
   }
   
   private void TickForwardVelocity()
   {
      xPosition += xVelocity;
      yPosition += yVelocity;
   }
}