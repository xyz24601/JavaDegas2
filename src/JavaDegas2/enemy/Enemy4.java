/*
    JavaDegas2 v1.0 --- Space Shooting Game Classic
    Copyright (C) 2020  Shinji Umeki (shinji@umeki.org)
    
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
    
    See the file, COPYING, for more details.
*/

package JavaDegas2.enemy;

import java.awt.Component;
import java.awt.Graphics;

import JavaDegas2.*;

// slowly align, then straight to crash
public class Enemy4 extends EnemyCommon implements StatConst
{
  private double speed;  // base speed
  private boolean power;  // flag for power
  private boolean fired;  // to avoid duplicate fire
  private int imgC;
  
  public Enemy4()
  {
    super(E4_WIDTH, E4_HEIGHT);
  }
  
  // iy - initial y coordinate
  // is - base speed
  // p - true for power
  public void reset(int iy, double is, boolean p)
  {
    super.reset();
    setXY(F_WIDTH, iy);
    speed = is;
    power = p;
    fired = true;
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
        dyingFire(E4_DYING, efireg, ship);
        return(alive);
      }
      if (movedOut(coord))
        return(false);  // gone out of screen
      
      if (ready)
      {
        if (checkHit(ship, E4_SCORE, coord))
        {
          jdAudio.play(jdAudio.sEnemyHit);
          return(true);  // alive, but dying
        }
      }
      else
        moveIn();
      
      double sy = ship.getCenterY();
      double cx = getX();
      double cy = getY();
      double cyh = cy + getH();
      if ((sy < cyh) && (sy > cy))
      {  // aligned
        cx -= speed * 2;  // double the speed
        fired = false;  // ready to fire
      }
      else
      {
        cx -= speed;
        if (cy < sy)  // above ship
          cy += speed / 3;
        else
          cy -= speed / 3;
        
        if ((StatVar.level > 2) && !fired)  // do not fire at level 2
        {
          efireg.fire(this, ship);
          fired = true;
        }
      }
      setXY(cx, cy);  // update location
      
      if (imgC < (jdImages.e4.length * 2 - 1))
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
        kaboom(E4_DYING, g, c);
      }
      else
      {
        if (power)
          g.drawImage(jdImages.e4p[imgC / 2], (int) getX(), (int) getY(), c);
        else
          g.drawImage(jdImages.e4[imgC / 2], (int) getX(), (int) getY(), c);
      }
    }
  }

}
