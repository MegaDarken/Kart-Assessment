//File: RaceKart.java
import java.io.Serializable;

public class RaceKart implements Serializable
{
   //Constants
   static final int DIRECTIONS = 16;
   static final double PI_CARDINAL_RATIO = Math.PI*0.125;///8;
   
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

   private final String KART_LIVERY_RED = "Red";
   private final String KART_LIVERY_GREEN = "Green";
   private final String KART_LIVERY_BLUE = "Blue";
   private final String KART_LIVERY_BOT = "Bot";

   static final String DEFAULT_LIVERY = "Bot";
   static final int DEFAULT_WEIGHT = 5;
   static final double DEFAULT_ACCELERATION = 0.5;
   static final double DEFAULT_TOP_SPEED = 5;

   private final int INPUT_FORWARD = 0;
   private final int INPUT_BACKWARD = 1;
   private final int INPUT_LEFT = 2;
   private final int INPUT_RIGHT = 3;
   
   private final byte INPUT_ON_VALUE = 127;
   private final byte INPUT_OFF_VALUE = 0;

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
      this.xVelocity += (this.acceleration * Math.sin(this.Bearing));
      this.yVelocity += (this.acceleration * Math.cos(this.Bearing));
   }
   
   private void TickForwardDecelerate()
   {
      this.xVelocity -= (this.acceleration * Math.sin(this.Bearing));
      this.yVelocity -= (this.acceleration * Math.cos(this.Bearing));
   }
   
   public void TickForward(byte[] controls)
   {
      //Accelerate/Decelerate based on controls
      if (controls[INPUT_FORWARD] > INPUT_OFF_VALUE)
      {
         TickForwardAccelerate();
      }
      else if (controls[INPUT_BACKWARD] > INPUT_OFF_VALUE)
      {
         TickForwardDecelerate();
      }
      
      if (controls[INPUT_LEFT] > INPUT_OFF_VALUE)
      {
         TickForwardAccelerate();
      }
      else if (controls[INPUT_RIGHT] > INPUT_OFF_VALUE)
      {
         TickForwardDecelerate();
      }
   
      TickForwardVelocity();
   }
   
   public float X()
   {
      return this.xPosition;
   }
   
   public float Y()
   {
      return this.yPosition;
   }
   
   public void SetPosition(float x, float y)
   {
      this.xPosition = x;
      this.yPosition = y;
   }

   public String Livery()
   {
      return this.livery;
   }
   
   public void Livery(String value)
   {
      this.livery = value;
   }

   protected double GetVelocity()
   {
      return Math.sqrt((this.xVelocity*this.xVelocity)+(this.yVelocity*this.yVelocity));
   }
   
   public int GetCardinalDirection()
   {
      long cardinalValue = Math.round(Bearing * PI_CARDINAL_RATIO);
   
      int output = (int)(cardinalValue % DIRECTIONS);
      
      return output;
   }
   
   
   
}