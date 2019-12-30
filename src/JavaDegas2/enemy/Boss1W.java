package JavaDegas2.enemy;

import java.awt.*;
import JavaDegas2.*;

// handle boss1
public class Boss1W implements StatConst
{
  private boolean alive;
  private Boss1G b1g;
  private double bSpeed;
  
  public Boss1W()
  {
    alive = false;
    b1g = new Boss1G();
  }
  
  public void reset()
  {
    alive = true;
    bSpeed = B1W_SPEED + StatVar.level;
    b1g.reset(bSpeed); 
  }
  
  public void killThem()
  {
    alive = false;
    b1g.killThem();
  }
  
  // return true if aliave
  public boolean move(Ship ship, EFireG efg)
  {
    if (alive)
    {
      alive = b1g.move(ship, efg); 
    }
    return(alive);
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      b1g.paint(g, c);
    }
  }
}
