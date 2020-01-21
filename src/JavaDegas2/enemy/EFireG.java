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

// class to handle group of enemy fires
public class EFireG implements StatConst
{
  private EFire efire[];
  private int fNum;  // number of fire on screen
  private double speed;
  
  public EFireG()
  {
    efire = new EFire[EF_MAX_NUM];
    for (int i = 0; i < EF_MAX_NUM; i++)
      efire[i] = new EFire();
  }
  
  public void reset()
  {
    speed = EF_SPEED;
    for (int i = 0; i < EF_MAX_NUM; i++)
      efire[i].setAlive(false);
  }
  
  public void move(Ship ship)
  {
    for (int i = 0; i < EF_MAX_NUM; i++)
    {
      if (efire[i].move(ship))
        fNum--;  // fire is out of screen or hit ship
    }
  }
  
  // fire at the ship
  // e - enemy
  public void fire(IFO e, IFO ship)
  {
    if (ship.isAlive() && (fNum < EF_MAX_NUM))
    {
      for (int i = 0; i < EF_MAX_NUM; i++)
      {
        if (efire[i].fire(e, ship, speed + StatVar.level))
        {
          fNum++;
          break;
        }
      }
    }
  }
  
  // fire like fireworks 
  // e - enemy
  // num - number of fires
  public void firework(IFO e, IFO ship, int num)
  {
    double rad = 0.0;
    int i = 0;
    int j = 0;
    while (ship.isAlive() && (fNum < EF_MAX_NUM) &&
           (i < EF_MAX_NUM) && (j < num))
    {
      if (efire[i].fireRadian(e, rad, speed + StatVar.level))
      {
        fNum++;  j++;  // valid fire
        rad += Math.PI * 2 / num;  // change angle
      }
      i++;  // keep trying
    }
  }
  
  public void paint(Graphics g)
  {
    for (int i = 0; i < EF_MAX_NUM; i++)
      efire[i].paint(g);
  }

}
