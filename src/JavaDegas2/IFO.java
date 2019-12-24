package JavaDegas2;

import java.awt.Rectangle;

// super class for moving object
public class IFO extends Coord
{
  protected Rectangle area;
  protected boolean alive, ready, dying;
  protected int dDelay;  // dying delay
  protected int rDelay;  // ready delay
  
  public IFO(int w, int h)
  {
    super();
    area = new Rectangle(w, h);
  }
  
  public void setXY(double ix, double iy)
  {
    super.setXY(ix, iy);  // be sure to move area
    area.setLocation((int) ix, (int) iy);
  }
  
  public void setXYWH(double x, double y, int w, int h)
  {
    this.setXY(x, y);  // not super, need to move area
    area.setSize(w, h);
  }
  
  public void setXY(int ix, int iy)
  {
    super.setXY(ix, iy);
    area.setLocation(ix, iy);
  }
  
  public void setAlive(boolean a)
  {
    alive = a;
  }
  
  public void setDying(boolean a)
  {
    dying = a;
  }
  
  public boolean isAlive() { return(alive); }
  
  public boolean isDying() { return(dying); }
  
  public double getCenterX()
  {
    return(getX() + (area.width / 2));
  }
  
  public double getCenterY()
  {
    return(getY() + (area.height / 2));
  }
  
  public int getW()
  {
    return(area.width);
  }
  
  public int getH()
  {
    return(area.height);
  }
  
  public void reset()
  {
    alive = true;
    ready = dying = false;
    dDelay = 0;
    rDelay = 0;
  }
  
  public Rectangle getArea() { return(area); }
  
  // add delay for explosion before completely disappearing
  public void dyingDelay(int maxDelay)
  {
    if (dDelay > maxDelay)
      alive = false;
    else
      dDelay++;
  }
  
  // add delay before it can be killed
  public void readyDelay(int maxDelay)
  {
    if (rDelay > maxDelay)
      ready = true;
    else
      rDelay++;
  }
}
