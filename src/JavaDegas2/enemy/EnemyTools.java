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

// class to handle common calculations
public class EnemyTools implements StatConst
{
  // e - enemy
  // ship - ship
  // speed - speed to projectile
  // return Coord with x & y speed
  public static Coord straight(IFO e, IFO ship, double speed)
  {
    double dx, dy;  // horizontal & vertical distances
    double angle;
    boolean xPos, yPos;
    
    double ex = e.getCenterX();
    double ey = e.getCenterY();
    double sx = ship.getCenterX();
    double sy = ship.getCenterY();
    
    dx = ex - sx;
    if (dx >= 0)  // ship is placed left of enemy
      xPos = false;
    else
      xPos = true;
    
    dy = ey - sy;
    if (dy >= 0)  // ship is placed top of enemy
      yPos = false;
    else
      yPos = true;
    
    if (0 == dx)  // protect from division by 0
      angle = Math.atan(Double.MAX_VALUE);
    else
      angle = Math.atan(dy / dx);
    
    Coord xySpeed = new Coord();
    xySpeed.setX((xPos ? -speed : speed) * Math.abs(Math.cos(angle)));
    xySpeed.setY((yPos ? -speed : speed) * Math.abs(Math.sin(angle)));

    return(xySpeed);
  }
  
  // fire in the direction given by radian
  // rad - angle in radian
  // speed - speed of projectile
  // return Coord with x & y speed
  public static Coord radian(double rad, double speed)
  {
    Coord xySpeed = new Coord();
    xySpeed.setX(-speed * Math.cos(rad));
    xySpeed.setY(-speed * Math.sin(rad));

    return(xySpeed);    
  }
  
  // r - radian
  // mh - movement height
  // mc - movement center
  // return calculated sine wave y location
  public static double e2_calcY(double r, int mh, int mc)
  {
    return(Math.sin(r) * mh / 2 + mc);
  }

  // r - radian
  // mh - height
  // return calculated positive sine wave y location
  public static double e3_calcY(double r, int mh)
  {
    return(G_HEIGHT - Math.abs(Math.sin(r) * mh));
  }
}
