package JavaDegas2;

import java.awt.event.*;

// class to handle window event
public class ListenerWinExit extends WindowAdapter
{
  public void windowClosing(WindowEvent event)
  {
    System.out.println("Thank you for playing!");
    System.out.println("Have a nice day.");
    System.exit(0);
  }

}
