package JavaDegas2.enemy;

import java.awt.*;
import JavaDegas2.*;

// class to handle enemy 2
// moves in sine wave from right to left
public class Enemy2 extends EnemyCommon implements StatConst
{
  private int moveCenter;  // center of sine wave
  private int moveHeight;  // height of sine wave
  private double rad;  // keep track on radian
  private double xSpeed;  // horizontal speed
  private double yRSpeed;  // vertical radian speed
  private boolean power;  // flag for power
  private boolean fired;  // flag to avoid multiple fire
  private int fireLoc;  // fireing location
  private boolean reverse;  // turn back?
  private boolean inScreen;  // flag for reverse to work
  private int imgC;
  
  public Enemy2()
  {
    super(E2_WIDTH, E2_HEIGHT);
  }
  
  // mc - movement center
  // mh - movement height
  // ir - initial y coordinate in radian
  // sx - x speed
  // sry - y radian speed
  // fl - firing location, top, bottom, both
  // p - true for power enemy
  // r - true for reverse
  public void reset(int mc, int mh, double ir, double sx, double sry,
                    int fl, boolean p, boolean r)
  {
    super.reset();
    setXY(F_WIDTH, EnemyTools.e2_calcY(ir, mh, mc));
    moveCenter = mc;
    moveHeight = mh;
    rad = ir;
    xSpeed = sx;
    yRSpeed = sry;
    fireLoc = fl;
    power = p;
    reverse = r;
    inScreen = false;
    fired = false;  // so it will fire next round
    imgC = 0;
  }
  
  // coord is used to return x, y coordinate
  // return true if alive
  public boolean move(Ship ship, EFireG efireg,
                      Coord coord)
  {
    if (alive)
    {
      if (dying)
      {
        dyingFire(E2_DYING, efireg, ship);
        return(alive);
      }
      
      if (!reverse && movedOut(coord))
        return(false);  // gone out of screen

      if (ready)
      {
        if (checkHit(ship, E2_SCORE, coord))
        {
          jdAudio.play(jdAudio.sEnemyHit);
          return(true);  // alive, but dying
        }
      }
      else
        moveIn();
      
      double tx = getX();

      if (reverse)
      {  // make it so it will stay in screen
        if ((tx < 0) ||
            (inScreen && (tx > (F_WIDTH - getW()))))
        {
          xSpeed = -xSpeed;
          inScreen = true;  // oh well
        }
      }
      
      rad += yRSpeed;
      if (rad > Math.PI * 2)  // reset
        rad = 0.0;
      
      if (StatVar.level > 1)  // do not fire at level 1
      {
        if (!fired && (fireLoc != E2_FIRE_BOTTOM) &&
            (Math.abs(rad - (Math.PI * 3 / 2)) <= yRSpeed))
        {
          efireg.fire(this, ship);
          fired = true;
        }
        else if (!fired && (fireLoc != E2_FIRE_TOP) &&
                 (Math.abs(rad - Math.PI / 2) <= yRSpeed))
        {
          efireg.fire(this, ship);
          fired = true;
        }
        else if (fired)
          fired = false;
      }
      
      setXY(tx - xSpeed, EnemyTools.e2_calcY(rad, moveHeight, moveCenter));
      
      if (imgC < (jdImages.e2.length * 2 - 1))
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
        kaboom(E2_DYING, g, c);
      }
      else
      {
        if (power)
          g.drawImage(jdImages.e2p[imgC / 2], (int) getX(), (int) getY(), c);
        else
          g.drawImage(jdImages.e2[imgC / 2], (int) getX(), (int) getY(), c);
      }
    }
  }

}
