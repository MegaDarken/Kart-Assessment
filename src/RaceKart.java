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

   static final String DEFAULT_LIVERY = "Bot";
   static final int DEFAULT_WEIGHT = 5;
   static final double DEFAULT_ACCELERATION = 0.5;
   static final double DEFAULT_TOP_SPEED = 5;


   static final int CONTROLS_FWD_BWD = 0;

   //Attributes that should stay the same
   private String livery;
   
   private int weight;
   private double acceleration;// U/T/T
   private double top_speed;// U/T
   
   //Attributes that should change
   private float xPosition;
   private float yPosition;
   
   private float xVelocity;
   private float yVelocity;
   
   private float Bearing;
   
   
   public RaceKart(String livery, int weight, double acceleration, double top_speed)
   {
      this.livery = livery;
      this.weight = weight;
      this.acceleration = acceleration;
      this.top_speed = top_speed;
      
      this.xPosition = 0;
      this.yPosition = 0;
      
      this.xVelocity = 0;
      this.yVelocity = 0;
   }
   
   public RaceKart()
   {
      this.livery = DEFAULT_LIVERY;
      this.weight = DEFAULT_WEIGHT;
      this.acceleration = DEFAULT_ACCELERATION;
      this.top_speed = DEFAULT_TOP_SPEED;
      
      this.xPosition = 0;
      this.yPosition = 0;
      
      this.xVelocity = 0;
      this.yVelocity = 0;

   }
   
   private void TickForwardVelocity()
   {
      this.xPosition += this.xVelocity;
      this.yPosition += this.yVelocity;
   }
   
   private void TickForwardAccelerate()
   {
      this.xVelocity += this.acceleration;
      this.yVelocity += this.acceleration;
   }
   
   private void TickForwardDecelerate()
   {
      this.xVelocity -= this.acceleration;
      this.yVelocity -= this.acceleration;
   }
   
   public void TickForward(byte[] controls)
   {
      //Accelerate/Decelerate based on controls
      if (controls[CONTROLS_FWD_BWD] > 0)
      {
         TickForwardAccelerate();
      }
      else if (controls[CONTROLS_FWD_BWD] < 0)
      {
         TickForwardDecelerate();
      }
      
   
      TickForwardVelocity();
   }
   
   
   private int getCardinalDirection()
   {
      long cardinalValue = Math.round(Bearing * PI_CARDINAL_RATIO);
   
      int output = (int)(cardinalValue % DIRECTIONS);
      
      return output;
   }
}