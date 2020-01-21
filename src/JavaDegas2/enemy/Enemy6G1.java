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

// follow the leader kind of group
// but delay between each enemy is large
public class Enemy6G1 extends FollowGroup implements StatConst
{
  public Enemy6G1()
  {
    super();
    enemy = new Enemy6[E6G1_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy6();
  }
  
  // sp - walking speed
  // ms - max number of stop
  // mw - max walk delay
  // t - true to appear at top
  // eNum - number of enemy in this group
  // iDelay - delay between enemy
  public boolean reset(double sp, int ms, int mw, boolean t, int eNum, int iDelay)
  {
    if (alive)
      return(false);
    
    super.reset(iDelay);
    mEnum = (eNum > E6G1_MAXNUM ? E6G1_MAXNUM : eNum);
    
    for (int i = 0; i < mEnum; i++)
    {
      ((Enemy6) enemy[i]).reset(sp, ms, mw, t);
    }
    
    return(true);
  }
  
  // return false when all enemy are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      addEnemy(E6G1_MAXNUM);
      
      alive = false;  // for now
      for (int i = 0; i < cEnum; i++)
      {
        if (((Enemy6) enemy[i]).move(ship, efg, coord) ||
            (cEnum < mEnum))
        {
          if (!alive)  // at least one live enemy
            alive = true;
        }
      }
    }
    return(alive);
  }

}
