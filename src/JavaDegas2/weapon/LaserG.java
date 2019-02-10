package JavaDegas2.weapon;

import java.awt.*;
import JavaDegas2.*;

// class to handle group of lasers
public class LaserG implements StatConst
{
  private Laser laser[];
  private int lNum;  // current number of lasers
  private int lDelay;
  
  public LaserG()
  {  // create all lasers
    laser = new Laser[MAX_LASER];
    for (int i = 0; i < laser.length; i++)
      laser[i] = new Laser();
    reset();
  }
  
  public void reset()
  {
    for (int i = 0; i < laser.length; i++)
      laser[i].reset();
    lNum = 0;
    lDelay = 500;  // make it so it will fire immediately
  }

  public void fire(int x, int y)
  {
    if (lNum < MAX_LASER)
    {
      for (int i = 0; i < MAX_LASER; i++)
      {
        if (!laser[i].isDetached())
          break;  // still firing
        
        if (laser[i].isDetached())
        {
          if (laser[i].fire(x, y))
          {
            lNum++;
            break;
          }
        }
      }
    }
  }
  
  // ifo - ship or option
  public void move(IFO ifo)
  {  // if ship is hit, can't fire any more
    if (ifo.isAlive() && !ifo.isDying() && StatVar.wType == WT_LASER)
    {
      lDelay++;
      
      if ((StatVar.keySPACE) && (lDelay > LASER_DELAY))
      {
        lDelay = 0;
        int lPos;
        lPos = (int) ifo.getX() + ifo.getW();
        fire(lPos, (int) ifo.getCenterY());
      }
    }
    
    for (int i = 0; i < laser.length; i++)
    {
      if (laser[i].move(ifo))
        lNum--;
    }
  }
  
  // return true if hit enemy
  public boolean checkHit(IFO ifo)
  {
    for (int i = 0; i < laser.length; i++)
    {
      if (laser[i].checkHit(ifo))
      {
        lNum--;
        return(true);
      }
    }
    return(false);
  }
  
  public void paint(Graphics g)
  {
    for (int i = 0; i < laser.length; i++)
      laser[i].paint(g);
  }
}
