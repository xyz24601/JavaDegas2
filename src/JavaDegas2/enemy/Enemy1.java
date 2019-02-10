package JavaDegas2.enemy;

import java.awt.*;
import JavaDegas2.*;

// class to handle enemy 1
// enemy one moves straight from right to left
// then moves back diagonally, then forward again
public class Enemy1 extends EnemyCommon implements StatConst
{
  private double speed;
  private int xcMax;  // max horizontal movement
  private int ycMax;  // max vertical movement
  private int xc;  // horizontal movement counter
  private int yc;  // vertical movement counter
  private double ySpeed;  // vertical movement speed
  private int imgC;
 
  public Enemy1()
  {
    super(E1_WIDTH, E1_HEIGHT);
  }

  // yLoc - starting y location
  // speed - speed
  // xcM - horizontal movement count
  // ycM - vertical movement count
  public void reset(double yLoc, double speed, int xcM, int ycM)
  {
    super.reset();
    setXY(F_WIDTH, yLoc);  // place it right of screen
    this.speed = ySpeed = speed;
    xc = yc = 0;
    xcMax = xcM;
    ycMax = ycM;
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
        dyingFire(E1_DYING, efireg, ship);
        return(alive);
      }
      if (movedOut(coord))
        return(false);  // gone out of screen

      if (ready)
      {
        if (checkHit(ship, E1_SCORE, coord))
        {
          jdAudio.play(jdAudio.sEnemyHit);
          return(true);  // alive, but dying
        }
      }
      else
        moveIn();

      double centerY = getCenterY();
      double shipY = ship.getCenterY();
      double tx = getX();
      double ty = getY();

      if (xc < xcMax)  // horizontal movement
      {
        tx -= speed;
        xc++;
        if (xc == xcMax)
        {  // decide horizontal direction
          if (shipY > centerY)  // move toward ship
            ySpeed = speed;
          else
            ySpeed = -speed;
          
          if (StatVar.level > 1)  // do not fire at level 1
            efireg.fire(this, ship);
        }
      }
      else  // diagonal movement
      {
        tx += speed;
        ty += ySpeed;

        yc++;
      }
      setXY(tx, ty);
    
      if (yc > ycMax)  // reset both counters
        xc = yc = 0;

      if (imgC < (jdImages.e1.length * 2 - 1))
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
        kaboom(E1_DYING, g, c);
      }
      else
      {
        g.drawImage(jdImages.e1[imgC / 2], (int) getX(), (int) getY(), c);
      }
    }
  }
}
