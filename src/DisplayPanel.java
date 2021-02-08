//File: DisplayPanel
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayPanel extends JPanel implements ActionListener
{
   private final int maximumImageLabels = 100;//Arbitary
   
   private final int windowStartingPositionX = 640;
   private final int windowStartingPositionY = 480;
   private final int windowStartingSizeX = 640;
   private final int windowStartingSizeY = 480;
   
   private JLabel[] imageLabels;
   private Timer timer;
   
   public DisplayPanel()
   {
        setLayout(null);//Disable Grid
        
        JFrame frame = new JFrame();
        frame.setBounds(windowStartingPositionX, windowStartingPositionY, windowStartingSizeX, windowStartingSizeY);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        //Keybinds
        // Keymap frameMap = frame.getKeymap();
//         
//         KeyStroke moveForwards = KeyStroke.getKeyStroke(KeyEvent.VK_W);//,InputEvent.SHIFT_MASK
//         KeyStroke moveBackwards = KeyStroke.getKeyStroke(KeyEvent.VK_S);
//         KeyStroke moveLeft = KeyStroke.getKeyStroke(KeyEvent.VK_A);
//         KeyStroke moveRight = KeyStroke.getKeyStroke(KeyEvent.VK_D);
//         
//         frameMap.addActionForKeyStroke(moveForwards, new actionForwards());
//         frameMap.addActionForKeyStroke(moveBackwards, new actionBackwards());
//         frameMap.addActionForKeyStroke(moveLeft, new actionLeft());
//         frameMap.addActionForKeyStroke(moveRight, new actionRight());
        
   }
   
   
   public void actionPerformed(ActionEvent e)
   {
      
         if(e.getSource() == timer)
         {
            //handleImageAnimation();
            
            repaint();
         }
      
   }
   
   // private void actionForwards()
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


}