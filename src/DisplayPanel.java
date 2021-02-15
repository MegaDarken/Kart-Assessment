//File: DisplayPanel
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayPanel extends JPanel implements ActionListener
{
   private final int MAXIMUM_IMAGE_LABELS = 16;//Arbitary Magic
   
   private final int windowStartingPositionX = 640;
   private final int windowStartingPositionY = 480;
   private final int windowStartingSizeX = 640;
   private final int windowStartingSizeY = 480;
   
   private JFrame frame;
   private JLabel[] imageLabels;
   private Timer timer;
   
   public DisplayPanel()
   {
        setLayout(null);//Disable Grid
        
        frame = new JFrame();
        frame.setBounds(windowStartingPositionX, windowStartingPositionY, windowStartingSizeX, windowStartingSizeY);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        imageLabels = new JLabel[MAXIMUM_IMAGE_LABELS];
        
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


}