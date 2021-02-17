//File: DisplayPanel
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayPanel extends JPanel implements ActionListener
{
   //Constants
   private final int MAXIMUM_IMAGE_ICONS = 64;
   private final int MAXIMUM_IMAGE_LABELS = 16;//Arbitary Magic
   
   private final int windowStartingPositionX = 640;
   private final int windowStartingPositionY = 480;
   private final int windowStartingSizeX = 640;
   private final int windowStartingSizeY = 480;
   
   private final int KART_DIRECTIONS = 16;
   
   private final String IMAGE_DIRECTORY = "./Graphics/";
   private final String IMAGE_KART_FILENAME = "Kart";
   private final String IMAGE_FILE_EXTENSION = ".jpg";
   
   private final String IMAGE_LIVERY_RED = "Red";
   private final String IMAGE_LIVERY_GREEN = "Green";
   private final String IMAGE_LIVERY_BLUE = "Blue";
   private final String IMAGE_LIVERY_BOT = "Bot";
   
   
   //Window Elements
   private ImageIcon[] imageIcons;
   
   private JFrame frame;
   private JLabel[] imageLabels;
   private Timer timer;
   
   //Constructor
   public DisplayPanel()
   {
   
        
   
        setLayout(null);//Disable Grid
        
        //Initalise Frame
        frame = new JFrame();
        frame.setBounds(windowStartingPositionX, windowStartingPositionY, windowStartingSizeX, windowStartingSizeY);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        //Initalize images
        imageIcons = new ImageIcon[MAXIMUM_IMAGE_ICONS];
        imageLabels = new JLabel[MAXIMUM_IMAGE_LABELS];
        
        //Load Resources
        LoadKartImages();
        
        //Keybinds
        JPanel framePanel = (JPanel) frame.getContentPane();
        
        InputMap frameInputMap = framePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        ActionMap frameActionMap = framePanel.getActionMap();
        
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "moveForwards");//,InputEvent.SHIFT_MASK
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "moveBackwards");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "moveLeft");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "moveRight");
        
        frameActionMap.put("moveForwards", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            actionForwards();
         }
        });
        
        frameActionMap.put("moveBackwards", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            actionBackwards();
         }
        });
        
        frameActionMap.put("moveLeft", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            actionLeft();
         }
        });
        
        frameActionMap.put("moveRight", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            actionRight();
         }
        });
        
        
   }
   
   
   public void actionPerformed(ActionEvent e)
   {
      
         if(e.getSource() == timer)
         {
            //handleImageAnimation();
            
            repaint();
         }
      
   }
   
   
   //Action Functions
   private void actionForwards()
   {
   
   }
   
   private void actionBackwards()
   {
   
   }
   
   private void actionLeft()
   {
   
   }
   
   private void actionRight()
   {
   
   }

   //Loading
   private void LoadKartImages()
   {
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
         else if (currentKart.Livery().equals(IMAGE_LIVERY_GREEN))
         {
            index += (KART_DIRECTIONS * 2);
         }
         else if (currentKart.Livery().equals(IMAGE_LIVERY_GREEN))
         {
            index += (KART_DIRECTIONS * 3);
         }
         
         
         return index;
      }
      
      return -1;
   }
   
   private ImageIcon SelectKartImage(RaceKart currentKart)
   {
      return imageIcons[SelectKartImageIndex(currentKart)];
   }
   
}