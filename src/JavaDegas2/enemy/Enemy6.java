package JavaDegas2.enemy;

import java.awt.*;
import JavaDegas2.*;

// walking enemy
public class Enemy6 extends EnemyCommon implements StatConst
{
  private int cdWalk;  // walking delay counter
  private int cdStop;  // stop delay counter
  private int mdWalk;  // walk delay
  private int cStop;  // stop counter
  private int mStop;  // max number of stop
  private double wSpeed;  // walk speed
  private boolean top;  // flag for top or bottom
  private int imgC;

  public Enemy6()
  {
    super(E6_WIDTH, E6_HEIGHT);
  }
  
  // sx - walking speed
  // ms - max number of stop
  // mw - max walk delay
  // t - true to appear at top
  public void reset(double sx, int ms, int mw, boolean t)
  {
    super.reset();
    wSpeed = sx;
    mStop = ms;
    mdWalk = mw;
    top = t;
    setXY(F_WIDTH, (top ? G_TOP_MERGIN : G_HEIGHT - E6_HEIGHT));
    cStop = cdWalk = cdStop = 0;
    imgC = 0;
  }
  
  // return true if alive
  public boolean move(Ship ship, EFireG efireg, Coord coord)
  {
    if (alive)
    {
      if (dying)
      {
        dyingFire(E6_DYING, efireg, ship);
        return(alive);
      }
      if (movedOut(coord))
        return(false);  // moved out of screen
      
      if (ready)
      {
        if (checkHit(ship, E6_SCORE, coord))
        {
          jdAudio.play(jdAudio.sEnemyHit);
          return(true);  // alive, but dying
        }
      }
      else
        moveIn();

      double tx = getX();
      if (cStop < mStop)
      {  // keep stopping until max stop
        if (cdWalk < mdWalk)
        {  // keep walking
          setXY(tx - wSpeed, getY());
          cdWalk++;
        }
        else if (cdStop < E6_STOP)
        {
          if ((cdStop % 3) == 0)
            efireg.fire(this, ship);
          cdStop++;  // add delay
        }
        else  // done stopping
        {  // too close to the right edge, move left
          if (tx > (F_WIDTH - (E6_WIDTH * 5)))
            wSpeed = Math.abs(wSpeed);
          else  // randomly move
          {
            double d = Math.random();
            if (d > 0.5)
              wSpeed = Math.abs(wSpeed);
            else
              wSpeed = -(Math.abs(wSpeed));
          }
          
          cdStop = cdWalk = 0;
          cStop++;
        }
      }
      else  // done stopping
      {  // just keep moving to the left
        setXY(tx - Math.abs(wSpeed), getY());
      }
      
      if (imgC < (jdImages.e6top.length * 5 - 1))
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
        kaboom(E6_DYING, g, c);
      }
      else
      {
        if (top)
        {
          g.drawImage(jdImages.e6top[imgC / 5], (int) getX(), (int) getY(), c);
        }
        else
        {
          g.drawImage(jdImages.e6bot[imgC / 5], (int) getX(), (int) getY(), c);
        }
      }
    }
  }
  
}
