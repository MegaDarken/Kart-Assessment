//File: RaceKart.java


public class RaceKart
{
   //Constants
   static final int DIRECTIONS = 16;
   static final double PI_CARDINAL_RATIO = Math.PI/16;
   
   static final int NORTH = 0;
   static final int NORTH_NORTH_EAST = 1;
   static final int NORTH_EAST = 2;
   static final int NORTH_EAST_EAST = 3;
   static final int EAST = 4;
   static final int SOUTH_EAST_EAST = 5;
   static final int SOUTH_EAST = 6;
   static final int SOUTH_SOUTH_EAST = 7;
   static final int SOUTH = 8;
   static final int SOUTH_SOUTH_WEST = 9;
   static final int SOUTH_WEST = 10;
   static final int SOUTH_WEST_WEST = 11;
   static final int WEST = 12;
   static final int NORTH_WEST_WEST = 13;
   static final int NORTH_WEST = 14;
   static final int NORTH_NORTH_WEST = 15;


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
   
   
   private int getCardinalDirection()
   {
      int cardinalValue = (int)Math.round(Bearing * PI_CARDINAL_RATIO);
   
      cardinalValue = cardinalValue % DIRECTIONS;
      
      
      
      return cardinalValue;
   }
}