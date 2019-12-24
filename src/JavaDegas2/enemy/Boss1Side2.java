package JavaDegas2.enemy;

import java.awt.*;
import JavaDegas2.*;

public class Boss1Side2 extends BossCommon implements StatConst
{
  private boolean top;
  
  public Boss1Side2()
  {
    super(B1S2_WIDTH, B1S2_HEIGHT);
  }
  
  public void reset(double xLoc, double yLoc)
  {
    super.reset(xLoc,  yLoc);
  }
  
  public void setTop(boolean t)
  {
    top = t;
  }
  
  public boolean move(Ship ship, EFireG efireg, Coord coord,
                      double xLoc, double yLoc)
  {
    if (alive)
    {
      if (ready)
      {
        checkHit(ship, 0, coord);
        dying = false;  // ca not be destroyed
      }
      setXY(xLoc, yLoc);
    }
    return(alive);
  }                  
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      if (top)
      {
        if (dying)
          g.drawImage(jdImages.bS2topG, (int) getX(), (int) getY(), c); 
        else
          g.drawImage(jdImages.bS2top, (int) getX(), (int) getY(), c); 
      }
      else
      {
        if (dying)
          g.drawImage(jdImages.bS2botG, (int) getX(), (int) getY(), c);
        else
          g.drawImage(jdImages.bS2bot, (int) getX(), (int) getY(), c);
      }
    } 
  }
}
