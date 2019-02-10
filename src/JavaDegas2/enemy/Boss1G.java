package JavaDegas2.enemy;

import java.awt.*;
import JavaDegas2.*;

// class to handle boss components
public class Boss1G implements StatConst
{
  protected boolean alive;
  protected boolean intro;
  protected Boss1Core b1c;
  private double speed;
  private Coord coord;
  private int mCount;
  
  public Boss1G()
  {
    b1c = new Boss1Core();
    alive = false;
    intro = true;
    coord = new Coord();  // dummy
    mCount = B1_HMOVE;
  }

  public boolean reset(double iSp)
  {
    if (alive)
      return(false);
    
    speed = iSp;
    b1c.reset(F_WIDTH + B1C_WIDTH, (G_HEIGHT - B1C_HEIGHT) / 2);

    alive = intro = true;
    return(alive);
  }
  
  public void killThem()
  {
    alive = false;
  }
  
  // return false when destroyed
  public boolean move(Ship ship, EFireG efg)
  {
    if (alive)
    {
      if (intro)
      {  // make boss appear from right
        double cx = b1c.getX();
        if (cx <= F_WIDTH - (B1C_WIDTH * 5))
        {
          intro = false;
          b1c.setReady();
        }
        else
          alive = b1c.move(ship, efg, coord, cx - speed, b1c.getY());
      }
      else
      {  // move up and down
        if (mCount >= B1_HMOVE)
        {
          if (ship.getCenterY() > b1c.getCenterY())
            speed = Math.abs(speed);  // ship is below
          else
            speed = -Math.abs(speed);  // ship is above
          mCount = 0;
        }
        
        double cy = b1c.getY();
        if (((cy > 100) && (speed < 0)) ||
            ((cy < 500) && (speed > 0)))
          cy += speed;

        alive = b1c.move(ship, efg, coord, b1c.getX(), cy);
        mCount++;
      }
    }
    return(alive);
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      b1c.paint(g, c);
    }
  }
}
