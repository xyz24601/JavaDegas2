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
