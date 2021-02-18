//File: DisplayPanel
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayPanel extends JPanel implements ActionListener
{
   //Constants
   //private final int TICKS_PER_SECOND = 30;
   
   private final int MAXIMUM_IMAGE_ICONS = 64;//16 Karts * 4 Liverys
   private final int MAXIMUM_IMAGE_LABELS = 16;//Arbitary Magic
   
   private final int windowStartingPositionX = 640;
   private final int windowStartingPositionY = 480;
   private final int windowStartingSizeX = 640;
   private final int windowStartingSizeY = 480;
   
   private final int KART_DIRECTIONS = 16;
   
   //Image(s)
   private final String IMAGE_DIRECTORY = "./Graphics/";
   private final String IMAGE_KART_FILENAME = "Kart";
   private final String IMAGE_FILE_EXTENSION = ".jpg";
   
   private final String IMAGE_LIVERY_RED = "Red";
   private final String IMAGE_LIVERY_GREEN = "Green";
   private final String IMAGE_LIVERY_BLUE = "Blue";
   private final String IMAGE_LIVERY_BOT = "Bot";
   
   //Input Related
   private final int INPUT_KEY_MATRIX_SIZE = 4;
   
   private final int INPUT_FORWARD = 0;
   private final int INPUT_BACKWARD = 1;
   private final int INPUT_LEFT = 2;
   private final int INPUT_RIGHT = 3;
   
   private final byte INPUT_ON_VALUE = 127;
   private final byte INPUT_OFF_VALUE = 0;
   
   //Attributes
      
   
   //input
   private byte[] inputKeyMatrix;
   
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
        inputKeyMatrix = new byte[INPUT_KEY_MATRIX_SIZE];
        
        JPanel framePanel = (JPanel) frame.getContentPane();
        
        InputMap frameInputMap = framePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        ActionMap frameActionMap = framePanel.getActionMap();
        
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "moveForwards");//,InputEvent.SHIFT_MASK
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "moveBackwards");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "moveLeft");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "moveRight");
        
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "stopForwards");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "stopBackwards");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "stopLeft");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "stopRight");
        
        //Key press
        frameActionMap.put("moveForwards", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            inputKeyMatrix[INPUT_FORWARD] = INPUT_ON_VALUE;
            //actionForwards();
         }
        });
        
        frameActionMap.put("moveBackwards", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            inputKeyMatrix[INPUT_BACKWARD] = INPUT_ON_VALUE;
            //actionBackwards();
         }
        });
        
        frameActionMap.put("moveLeft", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            inputKeyMatrix[INPUT_LEFT] = INPUT_ON_VALUE;
            //actionLeft();
         }
        });
        
        frameActionMap.put("moveRight", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            inputKeyMatrix[INPUT_RIGHT] = INPUT_ON_VALUE;
            //actionRight();
         }
        });
        
        //Key Release
        frameActionMap.put("stopForwards", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            inputKeyMatrix[INPUT_FORWARD] = INPUT_OFF_VALUE;
         }
        });
        
        frameActionMap.put("stopBackwards", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            inputKeyMatrix[INPUT_BACKWARD] = INPUT_OFF_VALUE;
         }
        });
        
        frameActionMap.put("stopLeft", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            inputKeyMatrix[INPUT_LEFT] = INPUT_OFF_VALUE;
         }
        });
        
        frameActionMap.put("stopRight", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            inputKeyMatrix[INPUT_RIGHT] = INPUT_OFF_VALUE;
         }
        });
        
        //Timer
        timer = new Timer(1000 / TICKS_PER_SECOND, this);
        timer.start();
   }
   
   
   public void actionPerformed(ActionEvent e)
   {
      
         if(e.getSource() == timer)
         {
            //handleImageAnimation();
            
            repaint();
         }
      
   }
   
   
   // //Action Functions
//    private void actionForwards()
//    {
//    
//    }
//    
//    private void actionBackwards()
//    {
//    
//    }
//    
//    private void actionLeft()
//    {
//    
//    }
//    
//    private void actionRight()
//    {
//    
//    }

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

   private void MoveKarts()
   {
      //For each kart in GameWorld
      
      //Get Kart Image
      
      //Get Corrisonding label
      
      // Apply Image to label
      
   }

}