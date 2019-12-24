package JavaDegas2.enemy;

import java.awt.*;
import JavaDegas2.*;

public class Boss1Back extends BossCommon implements StatConst
{
  public Boss1Back()
  {
    super(B1B_WIDTH, B1B_HEIGHT);
  }

  public void reset(double xLoc, double yLoc)
  {
    super.reset(xLoc,  yLoc);
  }
  
  public boolean move(Ship ship, EFireG efireg, Coord coord,
      double xLoc, double yLoc)
  {
    if (alive)
    {
      if (ready)
      {
        checkHit(ship, 0, coord);
        dying = false;  // can not be destroyed
      }
      setXY(xLoc, yLoc);
    }
    return(alive);
  }

  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      if (dying)
        g.drawImage(jdImages.b1backG, (int) getX(), (int) getY(), c); 
      else
        g.drawImage(jdImages.b1back, (int) getX(), (int) getY(), c); 
    }
  }

}
