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

// class to handle group of enemy 2
// follow the leader kind of group
public class Enemy2G1 extends FollowGroup implements StatConst
{
  public Enemy2G1()
  {
    super();
    enemy = new Enemy2[E2G1_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy2();
  }
  
  // mc - movement center
  // mh - movement height
  // ir - initial y coordinate in radian
  // sx - x speed
  // sry - y radian speed
  // fl - firing location, top, bottom, both
  // p - true for power enemy
  // r - true for reverse
  // eNum - number of enemy in this group
  // iDelay - delay between enemy
  public boolean reset(int mc, int mh, double ir, double sx, double sry,
                       int fl, boolean p, boolean r, int eNum, int iDelay)
  {
    if (alive)
      return(false);
    
    super.reset(iDelay);
    mEnum = (eNum > E2G1_MAXNUM ? E2G1_MAXNUM : eNum);

    for (int i = 0; i < mEnum; i++)
    {
      ((Enemy2) enemy[i]).reset(mc, mh, ir, sx, sry, fl, p, r);
    }
    
    return(true);
  }
  
  // return false when all enemy2s are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      addEnemy(E2G1_MAXNUM);
      
      alive = false;  // for now
      for (int i = 0; i < cEnum; i++)
      {
        if (((Enemy2) enemy[i]).move(ship, efg, coord) ||
             (cEnum < mEnum))
        {
          // as long as at lease one enemy is alive
          // set whole group to be alive
          if (!alive)
            alive = true;
        }
      }
      
      // last enemy killed
      placePower(pCap);
    }
    return(alive);
  }
  
}
