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

// move straight to the left
public class Enemy5G1 extends FollowGroup implements StatConst
{
  public Enemy5G1()
  {
    super();
    enemy = new Enemy5[E5G1_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy5();
    mEnum = E5G1_MAXNUM;
  }

  // yl - starting y location
  // sp - speed
  // id - delay between enemy
  // return true if initialized
  public boolean reset(int yl, double sp, int id)
  {
    if (alive)
      return(false);
    
    super.reset(id);
    for (int i = 0; i < mEnum; i++)
    {
      ((Enemy5) enemy[i]).reset(yl, sp);
    }
    return(true);
  }
  
  // return false when all enemy5s are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      addEnemy(E5G1_MAXNUM);
      
      alive = false;  // for now
      for (int i = 0; i < cEnum; i++)
      {
        if (((Enemy5) enemy[i]).move(ship, efg, coord) ||
            (cEnum < mEnum))
        {  // as long as one alive, the group is alive
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
