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

// class to handle enemy fire
public class EFire extends IFO implements StatConst
{
  private Coord xySpeed;  // reused to store speed
  
  public EFire()
  {
    super(EF_WIDTH, EF_HEIGHT);
    alive = ready = false;
  }
  
  // return true if goes out of screen or hit ship
  public boolean move(Ship ship)
  {
    if (alive)
    {
      double cx = getX();
      double cy = getY();
      
      if (ship.checkCrash(this) || ship.checkShield(this) ||
          (cx < -EF_WIDTH) || (cx > F_WIDTH) ||
          (cy < -EF_HEIGHT) || (cy > G_HEIGHT))
      {
        alive = false;
        return(true);
      }
      setXY(cx - xySpeed.getX(), cy - xySpeed.getY());
    }
    
    return(false);
  }
  
  // fire at the ship
  // e - enemy
  // ship - ship
  // speed - speed of projectile
  // return true if fired
  public boolean fire(IFO e, IFO ship, double speed)
  {
    if (!alive)
    {
      xySpeed = EnemyTools.straight(e, ship, speed);
      alive = true;
      setXY(e.getCenterX(), e.getCenterY());
      return(true);
    }
    return(false);
  }
  
  // fire in the direction given by radian
  // e - enemy
  // rad - angle in radian
  // speed - speed of projectile
  // return true if fired
  public boolean fireRadian(IFO e, double rad, double speed)
  {
    if (!alive)
    {
      xySpeed = EnemyTools.radian(rad, speed);
      alive = true;
      setXY(e.getCenterX(), e.getCenterY());
      return(true);
    }
    return(false);
  }
  
  public void paint(Graphics g)
  {
    if (alive)
    {
      g.setColor(Color.white);
      g.fillOval((int) getX(), (int) getY(), getW(), getH());
    }
  }

}
