package JavaDegas2.enemy;

import java.awt.*;

import JavaDegas2.*;

// class to handle enemy fire
public class EFire extends IFO implements StatConst
{
  private Coord xySpeed;  // reused to store speed
  
  public EFire()
  {
    super(EF_WIDTH, EF_HEIGHT);
    alive = ready = false;
  }
  
  // return true if goes out of screen or hit ship
  public boolean move(Ship ship)
  {
    if (alive)
    {
      double cx = getX();
      double cy = getY();
      
      if (ship.checkCrash(this) || ship.checkShield(this) ||
          (cx < -EF_WIDTH) || (cx > F_WIDTH) ||
          (cy < -EF_HEIGHT) || (cy > G_HEIGHT))
      {
        alive = false;
        return(true);
      }
      setXY(cx - xySpeed.getX(), cy - xySpeed.getY());
    }
    
    return(false);
  }
  
  // fire at the ship
  // e - enemy
  // ship - ship
  // speed - speed of projectile
  // return true if fired
  public boolean fire(IFO e, IFO ship, double speed)
  {
    if (!alive)
    {
      xySpeed = EnemyTools.straight(e, ship, speed);
      alive = true;
      setXY(e.getCenterX(), e.getCenterY());
      return(true);
    }
    return(false);
  }
  
  // fire in the direction given by radian
  // e - enemy
  // rad - angle in radian
  // speed - speed of projectile
  // return true if fired
  public boolean fireRadian(IFO e, double rad, double speed)
  {
    if (!alive)
    {
      xySpeed = EnemyTools.radian(rad, speed);
      alive = true;
      setXY(e.getCenterX(), e.getCenterY());
      return(true);
    }
    return(false);
  }
  
  public void paint(Graphics g)
  {
    if (alive)
    {
      g.setColor(Color.white);
      g.fillOval((int) getX(), (int) getY(), getW(), getH());
    }
  }

}
