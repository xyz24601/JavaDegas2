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

// appears all together lined up vertically
public class Enemy7G1 extends GroupCommon implements StatConst
{
  private final int M_CENTER =
      (G_HEIGHT - E2_HEIGHT) / 2 + G_TOP_MERGIN / 2;

  private boolean power;

  public Enemy7G1()
  {
    super();
    enemy = new Enemy7[E7G1_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy7();
  }

  // is - speed
  // ix - x location
  // fd - fire delay
  // p - true for power enemy
  // eNum - number of enemy in this group
  public boolean reset(double is, int ix, int fd, boolean p, int eNum)
  {
    if (alive)
      return(false);
    
    super.reset();
    power = p;
    
    switch (eNum)
    {
      case 3:  init1(ix, is, fd, p);
               init2(ix, is, fd, p);
               break;
      default: init5(ix, is, fd, p);
    }
    
    return(true);
  }
  
  // initialize center enemy
  void init1(int ix, double is, int fd, boolean p)
  {
    ((Enemy7) enemy[0]).reset(ix, M_CENTER, is, fd, p);
  }
  
  // initialize 2 enemy group
  void init2(int ix, double is, int fd, boolean p)
  {
    ((Enemy7) enemy[1]).reset(ix, M_CENTER - E7_HEIGHT * 2, is, fd, p);
    ((Enemy7) enemy[2]).reset(ix, M_CENTER + E7_HEIGHT * 2, is, fd, p);
  }
  
  // initialize 5 enemy group
  void init5(int ix, double is, int fd, boolean p)
  {
    init1(ix, is, fd, p);
    init2(ix, is, fd, p);
    ((Enemy7) enemy[3]).reset(ix, M_CENTER - E7_HEIGHT * 4, is, fd, p);
    ((Enemy7) enemy[4]).reset(ix, M_CENTER + E7_HEIGHT * 4, is, fd, p);
  }
  
  // return false when all ships are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      alive = false;  // for now
      for (int i = 0; i < enemy.length; i++)
      {
        if (((Enemy7) enemy[i]).move(ship, efg, coord))
        {
          if (!alive)
            alive = true;
        }
        else
        {
          if (power && (0.0 != coord.getX()))
          {
            pCap.place(coord.getX(), coord.getY(), false);
          }
          coord.setX(0.0);  // to avoid placing multiple
        }
      }
    }
    return(alive);
  }
}
