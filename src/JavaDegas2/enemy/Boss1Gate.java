package JavaDegas2.enemy;

import java.awt.Component;
import java.awt.Graphics;

public class Boss1Gate extends EnemyCommon implements JavaDegas2.StatConst
{
  private double hCount[];
  private int hDelay[];

  public Boss1Gate()
  {
    super(B1G_WIDTH, B1G_HEIGHT);
    hCount = new double[1];
    hDelay = new int[1];
  }
  
  public void reset(double xLoc, double yLoc)
  {
    super.reset();
    setXY(xLoc, yLoc);
    hCount[0] = 0.0;
    hDelay[0] = 100;
  }
  
  public void setReady()
  {
    ready = true;
  }
  
  
  public void paint(Graphics g, Component c)
  {}

}
