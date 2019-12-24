package JavaDegas2.enemy;

import java.awt.*;
import JavaDegas2.*;

// boss 1 core section
public class Boss1Core extends BossCommon implements StatConst 
{
//  private double hCount[];  // hit count
//  private int hDelay[];  // add delay between hit

  public Boss1Core()
  {
    super(B1C_WIDTH, B1C_HEIGHT);
  }
  
  public void reset(double xLoc, double yLoc)
  {
    super.reset(xLoc, yLoc);
  }
  
  // return true if alive
  public boolean move(Ship ship, EFireG efireg, Coord coord,
                      double xLoc, double yLoc)
  {
    if (alive)
    {
      if (dying)
      {
        dyingDelay(B1_DYING);
        return(alive);
      }
      
      if (ready)
      {
        if (checkHit(ship, hCount, B1C_MHIT, hDelay, B1C_MHDELAY, B1C_SCORE, coord))
        {
          dying = true;
          return(alive);  // alive, but dying
        }
      }
      setXY(xLoc, yLoc);
      hDelay[0]++;
    }
    return(alive);
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      if (dying)
        g.drawImage(jdImages.bcGrey, (int) getX(), (int) getY(), c); 
      else if (ready)
        g.drawImage(jdImages.bcBlue, (int) getX(), (int) getY(), c); 
      else
        g.drawImage(jdImages.bcRed, (int) getX(), (int) getY(), c); 
    }
  }
}
