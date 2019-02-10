package JavaDegas2.weapon;

import java.awt.*;
import JavaDegas2.*;

// class to handle laser
public class Laser extends IFO implements StatConst
{
  private int lpDelay;
  private double rightLoc;  // keep track of right location
  private double leftLoc;  // and left location
  private boolean detached;  // detached from ship?
  
  public Laser()
  {
    super(0, LASER_HEIGHT);
    reset();
  }

  public void reset()
  {
    alive = false;
    detached = true;
    lpDelay = 0;
    leftLoc = rightLoc = 0.0;
  }
  
  // return true if fired
  public boolean fire(int x, int y)
  {
    if (!alive)
    {
      jdAudio.play(jdAudio.sLaserFire);
      alive = true;
      detached = false;
      setXYWH(x, y, (int) LASER_SPEED, LASER_HEIGHT);
      rightLoc = x + LASER_SPEED;
      return(true);
    }
    return(false);
  }
  
  // true if moved out of screen
  public boolean move(IFO ifo)
  {
    if (alive)
    {
      if (StatVar.keySPACE && (StatVar.wType == WT_LASER) &&
          (lpDelay < LASER_P_DELAY) && !detached &&
          ifo.isAlive() && !ifo.isDying())
      {  // extend laser
        leftLoc = ifo.getX() + ifo.getW();
        lpDelay++;
      }
      else
      {  // detach from ship and keep moving
        detached = true;
        leftLoc += LASER_SPEED;
        
        if (leftLoc > F_WIDTH)
        {
          reset();
          return(true);
        }
      }
      rightLoc += LASER_SPEED;
      setXYWH(leftLoc, ifo.getCenterY(),
              (int) (rightLoc - leftLoc), LASER_HEIGHT);
    }
    
    return(false);
  }
  
  // return true if hit enemy
  public boolean checkHit(IFO ifo)
  {
    if (alive && area.intersects(ifo.getArea()))
    {  // hit enemy
      return(true);
    }
    return(false);
  }
  
  public void paint(Graphics g)
  {
    if (alive)
    {
      g.setColor(Color.blue);
      g.fillRect((int) getX(), (int) getY(), getW(), getH());
    }
  }
  
  public boolean isDetached()
  {
    return(detached);
  }
}
