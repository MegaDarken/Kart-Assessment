//File: ClientFrame.java

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class ClientFrame extends JFrame
{
   //Constants
   static final String PROGRAM_NAME = "Kart Assignment";

   static final int WINDOW_X_START = 100;
   static final int WINDOW_Y_START = 100;
   
   static final int WINDOW_WIDTH = 500;
   static final int WINDOW_HEIGHT = 500;
   
   //Vars
   private DisplayPanel panel;
   
   public DisplayPanel GetPanel()
   {
      return panel;
   }
   
   //Constructor
   public ClientFrame()
   {
      setTitle(PROGRAM_NAME);               // JFrame title
   	setBounds(WINDOW_X_START, WINDOW_Y_START, WINDOW_WIDTH, WINDOW_HEIGHT);        // JFrame location within screen: Position xleft, ydown, width, height
   	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
   	Container cp = getContentPane();   // Need Content Pane so can add to JFrame       
   	cp.setLayout(null);                // Suppress default layout
   
   	panel = new DisplayPanel();              // Create panel containing interface (could just use more than one panel)
   	panel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);      // Location within JFrame x, y, w, h
   
   	cp.add(panel);	                     // Add the Panel to the JFrame

   }
}