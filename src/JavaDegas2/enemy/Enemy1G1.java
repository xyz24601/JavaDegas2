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

// class to handle group of enemy 1
// follow the leader kind of group
public class Enemy1G1 extends FollowGroup implements StatConst
{
  public Enemy1G1()
  {
    super();
    enemy = new Enemy1[E1G1_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy1();
  }
  
  // yLoc - starting y location
  // xcM - horizontal movement count
  // ycM - vertical movement count
  // eNum - number of enemy in this group
  // iDelay - delay between enemy
  // return if initialized
  public boolean reset(double yLoc, double speed, int xcM, int ycM,
                       int eNum, int iDelay)
  {
    if (alive)
      return(false);
    
    super.reset(iDelay);
    mEnum = (eNum > E1G1_MAXNUM ? E1G1_MAXNUM: eNum);

    for (int i = 0; i < mEnum; i++)
    {
      ((Enemy1) enemy[i]).reset(yLoc, speed, xcM, ycM);
    }
    
    return(true);
  }
  
  // return false when all enemy1s are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      addEnemy(E1G1_MAXNUM);
      
      alive = false;  // for now
      for (int i = 0; i < cEnum; i++)
      {
        if (((Enemy1) enemy[i]).move(ship, efg, coord) ||
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
