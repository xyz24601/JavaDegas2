package JavaDegas2.enemy;

import java.awt.Component;
import java.awt.Graphics;

import JavaDegas2.*;

// bounce enemy
public class Enemy3 extends EnemyCommon implements StatConst
{
  private int cBounce;  // bounce counter
          // after so many bounces, leave screen
  private int bHeight;  // bouce height
  private double xSpeed;  // horizontal speed
  private double yRSpeed;  // vertical radian speed
  private double rad;  // keep track on radian
  private boolean power;  // flag for power
  private int mBounce;  // max bounce
  private boolean fired;  // flag to avoid multiple fire
  private int imgC;
  
  public Enemy3()
  {
    super(E3_WIDTH, E3_HEIGHT);
  }

  // ih - height of bounce
  // sx - x speed
  // sry - y radian speed
  // mb - max number of bounce
  // p - true for power enemy
  public void reset(int ih, double sx, double sry, int mb, boolean p)
  {
    super.reset();
    rad = 0.0;  // this starting location works
    setXY(F_WIDTH, EnemyTools.e3_calcY(rad, ih) - E3_HEIGHT);
    bHeight = ih;
    xSpeed = sx;
    yRSpeed = sry;
    mBounce = mb;
    power = p;
    fired = false;  // so it will fire next round
    cBounce = 0;
    imgC = 0;
  }
  
  // return true if alive
  public boolean move(Ship ship, EFireG efireg, Coord coord)
  {
    if (alive)
    {
      if (dying)
      {
        dyingFire(E3_DYING, efireg, ship);
        return(alive);
      }
      
      if (ready)
      {
        if (checkHit(ship, E3_SCORE, coord))
        {
          jdAudio.play(jdAudio.sEnemyHit);
          return(true);  // alive, but dying}
        }
      }
      else
        moveIn();
      
      if (!fired && (rad > (Math.PI / 2)))
      {
        efireg.firework(this, ship, 10 + (StatVar.level * 3));
        fired = true;
      }

      double tx = getX();

      if (tx < -getW())  // out of screen
      {        // but is it done bouncing?
        if (cBounce >= mBounce)
        {       // yes it is done bouncing
          alive = false;
          coord.setXY(0, 0);
          return(alive);
        }
      }

      rad += yRSpeed;
      if (rad > Math.PI)  // bounce completed
      {
        rad = 0.0;
        fired = false;
        cBounce++;
        
        // always bounce toward the ship
        if ((ship.getX() > tx) && (cBounce < mBounce))
          xSpeed = -(Math.abs(xSpeed));
        else
          xSpeed = Math.abs(xSpeed);
      }
      setXY(tx - xSpeed, EnemyTools.e3_calcY(rad, bHeight) - E3_HEIGHT);
      
      if (imgC < (jdImages.e3.length * 5 - 1))
        imgC++;
      else
        imgC = 0;
    }
    return(alive);
  }

  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      if (dying)
      {
        kaboom(E3_DYING, g, c);
      }
      else
      {
        if (power)
          g.drawImage(jdImages.e3p[imgC / 5], (int) getX(), (int) getY(), c);         
        else
          g.drawImage(jdImages.e3[imgC / 5], (int) getX(), (int) getY(), c);         
      }
    }
  }
    
}
