//File: DisplayPanel
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayPanel extends JPanel implements ActionListener
{
   //Constants
   private final int TICKS_PER_SECOND = 30;
   
   private final int MAXIMUM_IMAGE_ICONS = 64;//16 Karts * 4 Liverys
   private final int MAXIMUM_IMAGE_LABELS = 16;//Arbitary Magic
   
   private final int windowStartingPositionX = 640;
   private final int windowStartingPositionY = 480;
   private final int windowStartingSizeX = 640;
   private final int windowStartingSizeY = 480;
   
   private final int KART_DIRECTIONS = 16;
   
   private final int KART_IMAGE_WIDTH = 50;
   private final int KART_IMAGE_HEIGHT = 50;
   
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
   
   //Getters and Setters
   public byte[] GetInputKeyMatrix()
   {
      return inputKeyMatrix;
   }
   
   
   //Constructor
   public DisplayPanel()
   {
   
        
   
        setLayout(null);//Disable Grid
        
        //Initalise Frame
        //frame = new JFrame();
        //frame.setBounds(windowStartingPositionX, windowStartingPositionY, windowStartingSizeX, windowStartingSizeY);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        //Initalize images
        imageIcons = new ImageIcon[MAXIMUM_IMAGE_ICONS];
        imageLabels = new JLabel[MAXIMUM_IMAGE_LABELS];
        
        
        //Load Resources
        LoadKartImages();
        
        for (int i = 0; i < MAXIMUM_IMAGE_LABELS; i++)
        {
            imageLabels[i] = new JLabel(imageIcons[i]);
            
            add(imageLabels[i]);
        }
        
        //Keybinds
        inputKeyMatrix = new byte[INPUT_KEY_MATRIX_SIZE];
        
        //JPanel framePanel = (JPanel) frame.getContentPane();
        
        InputMap frameInputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        
        ActionMap frameActionMap = this.getActionMap();
        
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "moveForwards");//,InputEvent.SHIFT_MASK
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "moveBackwards");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "moveLeft");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "moveRight");
        
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "stopForwards");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "stopBackwards");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "stopLeft");
        frameInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "stopRight");
        
        //Key press
        frameActionMap.put("moveForwards", actionForwardsMove);
        frameActionMap.put("moveBackwards", actionBackwardsMove);
        
        frameActionMap.put("moveLeft", actionLeftMove);
        frameActionMap.put("moveRight", actionRightMove);
        
        //Key Release
        frameActionMap.put("stopForwards", new AbstractAction()
        {
         @Override
         public void actionPerformed(ActionEvent e)
         {  
            inputKeyMatrix[INPUT_FORWARD] = INPUT_OFF_VALUE;
            //System.out.println("Release: Forwards");
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
            AssessMode.world.MoveKarts();
            
            //paintComponent();
            
            repaint();
         }
      
   }
   
   
   // //Action Functions
   Action actionForwardsMove = new AbstractAction()
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {  
         inputKeyMatrix[INPUT_FORWARD] = INPUT_ON_VALUE;
         //System.out.println("Press: Forwards");
      }
   };
   
   Action actionBackwardsMove = new AbstractAction()
  {
      @Override
      public void actionPerformed(ActionEvent e)
      {  
         inputKeyMatrix[INPUT_BACKWARD] = INPUT_ON_VALUE;
      }
  };
           
   Action actionLeftMove = new AbstractAction()
  {
      @Override
      public void actionPerformed(ActionEvent e)
      {  
         inputKeyMatrix[INPUT_LEFT] = INPUT_ON_VALUE;
      }
  };
           
   Action actionRightMove = new AbstractAction()
  {
      @Override
      public void actionPerformed(ActionEvent e)
      {  
         inputKeyMatrix[INPUT_RIGHT] = INPUT_ON_VALUE;
         //actionRight();
      }
  };

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
         else if (currentKart.Livery().equals(IMAGE_LIVERY_BLUE))
         {
            index += (KART_DIRECTIONS * 2);
         }
         else if (currentKart.Livery().equals(IMAGE_LIVERY_BOT))
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

   public void UpdateKartImages(RaceKart[] Karts)
   {
      for(int i = 0; i < Karts.length; i++)
      {
         imageLabels[i].setIcon(SelectKartImage(Karts[i]));
      
         imageLabels[i].setBounds(Math.round(Karts[i].X()), Math.round(Karts[i].Y()), KART_IMAGE_WIDTH, KART_IMAGE_HEIGHT);
      }
   }

}