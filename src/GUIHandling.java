//File: GUIHandling.java
import javax.swing.*;

class GUIHandling implements Runnable
{
   //Constants
   private final int MAXIMUM_IMAGE_ICONS = 64;//16 Karts * 4 Liverys
   
   private final int KART_DIRECTIONS = 16;
      
   private final int KART_IMAGE_WIDTH = 50;
   private final int KART_IMAGE_HEIGHT = 50;
   
   //Image(s)
   private final String IMAGE_DIRECTORY = "../src/Graphics/";
   private final String IMAGE_KART_FILENAME = "Kart";
   private final String IMAGE_FILE_EXTENSION = ".png";
   
   private final String IMAGE_LIVERY_RED = "Red";
   private final String IMAGE_LIVERY_GREEN = "Green";
   private final String IMAGE_LIVERY_BLUE = "Blue";
   private final String IMAGE_LIVERY_BOT = "Bot";

   //Attributes
   private ImageIcon[] imageIcons;
   private JLabel[] imageLabels;

   public GUIHandling(JLabel[] imageLabels)
   {
      this.imageIcons = new ImageIcon[MAXIMUM_IMAGE_ICONS];
      
      this.imageLabels = imageLabels;
      
      //Load Resources
      LoadKartImages();
      
      
   }

   //Loading
   private void LoadKartImages()
   {
      System.out.println(System.getProperty("user.dir"));
      
      for (int i = 0; i < KART_DIRECTIONS; i++)
      {
         imageIcons[i] = new ImageIcon(getClass().getResource(IMAGE_DIRECTORY + IMAGE_KART_FILENAME + IMAGE_LIVERY_RED + i + IMAGE_FILE_EXTENSION));
         imageIcons[i + (KART_DIRECTIONS)] = new ImageIcon(getClass().getResource(IMAGE_DIRECTORY + IMAGE_KART_FILENAME + IMAGE_LIVERY_GREEN + i + IMAGE_FILE_EXTENSION));
         imageIcons[i + (KART_DIRECTIONS * 2)] = new ImageIcon(getClass().getResource(IMAGE_DIRECTORY + IMAGE_KART_FILENAME + IMAGE_LIVERY_BLUE + i + IMAGE_FILE_EXTENSION));
         imageIcons[i + (KART_DIRECTIONS * 3)] = new ImageIcon(getClass().getResource(IMAGE_DIRECTORY + IMAGE_KART_FILENAME + IMAGE_LIVERY_BOT + i + IMAGE_FILE_EXTENSION));
      }
   }
   
   private int SelectKartImageIndex(RaceKart currentKart)
   {
      if (currentKart != null)
      {
         //Kart Direction
         int index = currentKart.GetCardinalDirection();
         
         //Which Livery
         if (currentKart.Livery().equals(IMAGE_LIVERY_GREEN))
         {
            index += (KART_DIRECTIONS);
         }
         else if (currentKart.Livery().equals(IMAGE_LIVERY_BLUE))
         {
            index += (KART_DIRECTIONS * 2);
         }
         else if (currentKart.Livery().equals(IMAGE_LIVERY_BOT))
         {
            index += (KART_DIRECTIONS * 3);
         }
         
         //System.out.println(index);
         return index;
      }
      
      return -1;
   }
   
   private ImageIcon SelectKartImage(RaceKart currentKart)
   {
      return imageIcons[SelectKartImageIndex(currentKart)];
   }

   public void UpdateKartImages(RaceKart[] Karts)
   {
      for(int i = 0; i < Karts.length; i++)
      {
         imageLabels[i].setIcon(SelectKartImage(Karts[i]));
      
         imageLabels[i].setBounds(Math.round(Karts[i].X()), Math.round(Karts[i].Y()), KART_IMAGE_WIDTH, KART_IMAGE_HEIGHT);
      }
   }

   public void run()
   {
      //Redraw 
   
      UpdateKartImages(AssessMode.world.GetKarts());
      
   }
   

}
