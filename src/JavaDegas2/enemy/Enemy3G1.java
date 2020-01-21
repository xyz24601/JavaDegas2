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

import JavaDegas2.*;

// appears one by one
public class Enemy3G1 extends FollowGroup implements StatConst
{
  private boolean power;

  public Enemy3G1()
  {
    super();
    enemy = new Enemy3[E3G1_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy3();
  }
  
  // mh - max bounce height
  // nh - min bounce height
  // sx - xspeed
  // sry - y radian speed
  // mb - max number of bounces
  // p - true for power enemy
  // eNum - number of enemy in this group
  // iDelay - delay between enemy
  public boolean reset(int mh, int nh, double sx, double sry,
                       int mb, boolean p, int eNum, int iDelay)
  {
    if (alive)
      return(false);
    
    super.reset(iDelay);
    power = p;
    
    mEnum = (eNum > E3G1_MAXNUM ? E3G1_MAXNUM : eNum);
    
    for (int i = 0; i < mEnum; i++)
    {  // random bounce height between min and max
      int ih = (int) (((mh - nh) * Math.random()) + nh); 
      ((Enemy3) enemy[i]).reset(ih, sx, sry, mb, p);
    }
    
    return(true);
  }
  
  // return false when all enemy3s are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      addEnemy(E3G1_MAXNUM);
      
      alive = false;  // for now
      for (int i = 0; i < cEnum; i++)
      {
        if (((Enemy3) enemy[i]).move(ship, efg, coord) ||
            (cEnum < mEnum))
        {
          // as long as at lease one is alive
          if (!alive)
            alive = true;
        }
        else
        {
          if (power && (0.0 != coord.getX()))
          {
            pCap.place(coord.getX(), coord.getY(), false);
            coord.setX(0.0);  // to avoid placing multiple
          }
        }
      }
    }
    return(alive);
  }

}
