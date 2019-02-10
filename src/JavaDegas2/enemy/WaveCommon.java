package JavaDegas2.enemy;

import java.awt.Component;
import java.awt.Graphics;

public class WaveCommon
{
  protected boolean alive;
  protected GroupCommon eg[];
  protected int eNum;  // number of enemy in group
  protected int cgDelay;  // current delay counter
  protected int cGroup;  // current group counter
  
  public WaveCommon()
  {
    alive = false;
  }
  
  public void reset()
  {
    alive = true;
    cgDelay = 0;
    cGroup = 1;
  }
  
  public void killThem()
  {
    alive = false;
    for (int i = 0; i < eg.length; i++)
      eg[i].killThem();
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      for (int i = 0; i < eg.length; i++)
        eg[i].paint(g, c);
    }
  }
}
