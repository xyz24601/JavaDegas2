package JavaDegas2.enemy;

import java.awt.*;
import JavaDegas2.*;

public class Boss1Turret1 extends BossCommon implements StatConst
{
  private int fdelay;
  private int mdelay;
  private boolean firework;
  
  public Boss1Turret1()
  {
    super(B1R1_WIDTH, B1R1_HEIGHT);
    firework = false;  // default to single shot
  }
  
  public void reset(double xLoc, double yLoc)
  {
    super.reset(xLoc,  yLoc);
    fdelay = 0;
    mdelay = B1R1_FDELAY / StatVar.level;
  }
  
  // enable to toggle between single shot and burst
  public void setFirework(boolean ib)
  {
    firework = ib;
  }
  
  public void fdcut()
  {
    mdelay = B1R1_FDELAY / (StatVar.level * 2);
  }
  
  public boolean move(Ship ship, EFireG efireg, Coord coord,
                      double xLoc, double yLoc)
  {
    if (alive)
    {
      if (ready)
      {
        if (fdelay < 0)
        {
          if (firework)
          {
            efireg.firework(this, ship, B1R1_BNUM);
            fdelay = (int) (mdelay * 2.5);  // don't fire too much
          }
          else
          {
            efireg.fire(this, ship);
            fdelay = mdelay;
          }
        }
        else
        { fdelay--; }
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
        g.drawImage(jdImages.b1turG, (int) getX(), (int) getY(), c);
      else
        g.drawImage(jdImages.b1tur, (int) getX(), (int) getY(), c);
    }
  }

}
