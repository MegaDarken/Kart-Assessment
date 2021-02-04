//File: DisplayPanel
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DisplayPanel extends JPanel implements ActionListener
{
   private final int maximumImageLabels
   
   private JLabel[] imageLabels;
   private Timer timer;
   
   public DisplayPanel()
   {
   
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