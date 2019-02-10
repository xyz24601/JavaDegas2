package JavaDegas2.enemy;

import java.awt.Component;
import java.awt.Graphics;

import JavaDegas2.*;

// move straight to left
public class Enemy5 extends EnemyCommon implements StatConst
{
  private double xSpeed;  // x speed
  private boolean fired;  // to avoid duplicate fire
  private int fi;  // fire interval
  private int fp;  // next fire place
  private int imgC;
  
  public Enemy5()
  {
    super(E5_WIDTH, E5_HEIGHT);
  }
  
  // iy - initial y coordinate
  // is - speed
  public void reset(int iy, double is)
  {
    super.reset();
    setXY(F_WIDTH, iy);
    xSpeed = is;
    fired = false;
    fi = F_WIDTH / StatVar.level;  // firing interval
    fp = F_WIDTH - fi;
    imgC = 0;
  }
  
  // coord is used to return x, y coordinate
  // return true if alive
  public boolean move(Ship ship, EFireG efireg, Coord coord)
  {
    if (alive)
    {
      if (dying)
      {
        dyingFire(E5_DYING, efireg, ship);
        return(alive);
      }
      if (movedOut(coord))
        return(false);  // gone out of screen
      
      if (ready)
      {
        if (checkHit(ship, E5_SCORE, coord))
        {
          jdAudio.play(jdAudio.sEnemyHit);
          return(true);  // alive, but dying
        }
      }
      else
        moveIn();
      
      double cx = getX();
      
      if ((cx < fp) && !fired)
      {
        efireg.fire(this,  ship);
        fired = true;
        fp -= fi;
      }
      else
        fired = false;
      
      setXY(cx - xSpeed, getY());
      
      if (imgC < (jdImages.e5.length * 2 - 1))
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
        kaboom(E5_DYING, g, c);
      }
      else
      {
        g.drawImage(jdImages.e5[imgC / 2], (int) getX(), (int) getY(), c);
      }
    }
  }
  
}
