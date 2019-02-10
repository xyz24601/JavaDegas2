package JavaDegas2.enemy;

import java.awt.*;

import JavaDegas2.*;

// appear and crash
public class Enemy7 extends EnemyCommon implements StatConst
{
  private Coord xySpeed;  // reused to store speed
  private double speed;
  private boolean power;
  private int fDelay;  // fire delay
  private int cFDelay;  // current fire delay
  
  public Enemy7()
  {
    super(E7_WIDTH, E7_HEIGHT);
  }
  
  // ix - initial x coordinate
  // iy - initial y coordinate
  // is - speed
  // fd - fire delay;
  // p - true for power enemy
  public void reset(int ix, int iy, double is, int fd, boolean p)
  {
    super.reset();
    speed = is;
    fDelay = fd;
    power = p;
    setXY(ix, iy);
    cFDelay = 0;
  }

  // return true if alive
  public boolean move(Ship ship, EFireG efireg, Coord coord)
  {
    if (alive)
    {
      if (dying)
      {
        dyingFire(E7_DYING, efireg, ship);
        return(alive);
      }
      
      double tx = getX();
      double ty = getY();
      
      if ((tx < -E7_WIDTH) || (tx > F_WIDTH) ||
          (ty < -E7_HEIGHT) || (ty > G_HEIGHT))
      {
        alive = false;
        return(false);  // moved out of screen
      }
      
      if (ready)
      {
        if (checkHit(ship, E7_SCORE, coord))
        {
          jdAudio.play(jdAudio.sEnemyHit);
          return(true);  // alive, but dying
        }
        
        setXY(tx - xySpeed.getX(), ty - xySpeed.getY());
        cFDelay++;  // add delay between fire
        if (fDelay == cFDelay)
        {  // fire
          efireg.fire(this, ship);
          cFDelay = 0;  // reset counter
        }
      }
      else if (rDelay < E7_RDELAY)
      {  // not ready, just float to left
        setXY(tx - SCROLL_SPEED, ty);
        rDelay++;
      }
      else if (rDelay == E7_RDELAY)
      {  // reuse same calculation as straight bullet
        xySpeed = EnemyTools.straight(this, ship, speed);
        ready = true;
      }
    }
    return(alive);
  }
  
  public void paint(Graphics g, Component c)
  {
	  
    if (alive)
    {
      if (dying)
      {
        kaboom(E7_DYING, g, c);
      }
      else
      {
        if (rDelay != 0)
        {
          if (rDelay < (E7_RDELAY / 4))
            g.drawImage(jdImages.e7[0], (int) getX(), (int) getY(), c);
          else if (rDelay < (E7_RDELAY / 2))
            g.drawImage(jdImages.e7[1], (int) getX(), (int) getY(), c);
          else if (rDelay < (E7_RDELAY * 3 / 4))
            g.drawImage(jdImages.e7[2], (int) getX(), (int) getY(), c);
          else if (rDelay < E7_RDELAY)
            g.drawImage(jdImages.e7[3], (int) getX(), (int) getY(), c);
          else
          {
            if (power)
              g.drawImage(jdImages.e7pow, (int) getX(), (int) getY(), c);
            else
              g.drawImage(jdImages.e7reg, (int) getX(), (int) getY(), c);
          }
        }
      }
    }
  }
}
