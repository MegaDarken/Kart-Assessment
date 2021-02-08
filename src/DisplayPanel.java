//File: DisplayPanel
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayPanel extends JPanel implements ActionListener
{
   private final int maximumImageLabels
   
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
        dp.setBounds(windowStartingPositionX, windowStartingPositionY, windowStartSizeX, windowStartSizeY);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        
        //Keymap 
   }
   
   
   public void actionPerformed(ActionEvent e)
   {
      
         if(e.getSource() == timer)
         {
            handleImageAnimation();
            
            repaint();
         }
      
   }

}