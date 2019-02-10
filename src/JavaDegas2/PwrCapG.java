package JavaDegas2;

import java.awt.*;

// class to handle ALL power capsules
public class PwrCapG implements StatConst
{
  private PwrCap caps[];  // storage for all capsules
  private int cCaps;  // keep track of current capsule
  
  public PwrCapG()
  {  // create all power capsules
    caps = new PwrCap[P_MAX_CAP];
    for (int i = 0; i < caps.length; i++)
      caps[i] = new PwrCap();
    cCaps = 0;
  }
  
  public void deleteAll()
  {
    for (int i = 0; i < caps.length; i++)
      caps[i].alive = false;
    cCaps = 0;
  }
  
  public void move(Ship ship, Scores scores)
  {
    for (int i = 0; i < caps.length; i++)
      if (caps[i].move(ship, scores))
        cCaps--;  // not alive, decrease count
  }
  
  // ix - center of x location
  // iy - center of y location
  // op - flag for option
  public void place(double ix, double iy, boolean op)
  {
    if (cCaps < P_MAX_CAP)
    {
      for (int i = 0; i < P_MAX_CAP; i++)
      {  // keep trying to place until successful
        if (caps[i].place(ix, iy, op))
        {
          cCaps++;
          break;
        }
      }
    }
  }
    
  public void paint(Graphics g, Component c)
  {
    for (int i = 0; i < caps.length; i++)
      caps[i].paint(g, c);
  }

}
