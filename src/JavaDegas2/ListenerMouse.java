package JavaDegas2;

import java.awt.event.*;

// class to handle mouse input
public class ListenerMouse extends MouseAdapter
{
  public void mouseClicked(MouseEvent e)
  {
    e.consume();
    StatVar.paused = !StatVar.paused;
  }
}
