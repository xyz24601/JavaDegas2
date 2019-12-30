package JavaDegas2;

import java.awt.*;

public class GameOver
{
  private int goDelay;  // current delay
  private static final int GDELAY = 200;
 
  public boolean move()
  {
    if (goDelay < GDELAY)
    {
      goDelay++;
      StatVar.goDelay++;
      return(true);
    }
    else
    {
      goDelay = 0;
      StatVar.gameOver = false;  // back to title screen
      StatVar.titleScreen = true;
      return(false);
    }
  }
  
  public void paint(Graphics g, Component c)
  {
    g.drawImage(jdImages.gOver, 50, 225, c);
  }
}
