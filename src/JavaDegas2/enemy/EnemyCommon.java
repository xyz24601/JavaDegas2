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

import java.awt.*;
import JavaDegas2.*;

// super class for enemy
public abstract class EnemyCommon extends IFO implements StatConst
{
  
  public EnemyCommon(int w, int h)
  {
    super(w, h);
  }

  public void reset()
  {
    super.reset();
  }
 
  // dying and blow out fires
  // md - max delay
  public void dyingFire(int md, EFireG efg, Ship ship)
  {
    dyingDelay(md);
    
    // only fire for higher than level 3
    if ((dDelay == 1) && (StatVar.level > 3))
    {
      // fire random number
      int ff = (int) (Math.random() * 10) + 3;
      efg.firework(this, ship, ff);
    }
  }

  // co - return coordinate
  // return true if moved out of screen
  public boolean movedOut(Coord co)
  {
    if (getX() < -getW())
    {
      alive = false;
      co.setXY(0, 0);
      return(true);
    }
    return(false);
  }
  
  // move enemy to enter screen
  public void moveIn()
  {
    if (getCenterX() < F_WIDTH)
      ready = true;  // entered screen
  }

  // sci - score for the enemy
  // co - return coordinate
  // return true if killed
  public boolean checkHit(Ship ship, int sci, Coord co)
  {
    if (ship.checkStatus(this))  // killed
    {
      dying = true;
      StatVar.scores.add(sci);
      co.setXY(getCenterX(), getCenterY());
      return(true);
    }
    return(false);
  }

  // chc - current hit count
  // mhc - max hit count
  // sci - score for the enemy
  // chd - current hit delay
  // mhd - max hit delay - add delay between hits
  // co - return coordinate
  // return true if killed
  // use double as hit count, beam +1, laser +0.whatever
  public boolean checkHit(Ship ship, double chc[], double mhc,
                          int chd[], int mhd, int sci, Coord co)
  {
    if (ship.checkStatus(this))  // killed
    {
      if (chd[0] > mhd)
      {
        chd[0] = 0;
        if (StatVar.wType == WT_LASER)
          chc[0] += 0.5;
        else  
          chc[0] += 1.0;

        if (chc[0] >= mhc)  // can have multiple hits
        {
          dying = true;
          StatVar.scores.add(sci);
          co.setXY(getCenterX(), getCenterY());
          return(true);
        }
      }
    }
    return(false);
  }

  public void kaboom(Graphics g)
  {
    int tx = (int) getX();
    int ty = (int) getY();
    int tw = getW();
    int th = getH();
    g.setColor(Color.yellow);
    g.drawLine(tx, ty, tx + tw, ty + th);
    g.drawLine(tx, ty + th, tx + tw, ty);
  }

  public void kaboom(int dd, Graphics g, Component c)
  {
    if (dDelay < (dd / 2))
      g.drawImage(jdImages.kab00, (int) getX(), (int) getY(), c);
    else
      g.drawImage(jdImages.kab01, (int) getX(), (int) getY(), c);
  }

  // This method has to be overwritten
  public abstract void paint(Graphics g, Component c);
}
