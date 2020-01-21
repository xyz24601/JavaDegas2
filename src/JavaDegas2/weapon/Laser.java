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

package JavaDegas2.weapon;

import java.awt.*;
import JavaDegas2.*;

// class to handle laser
public class Laser extends IFO implements StatConst
{
  private int lpDelay;
  private double rightLoc;  // keep track of right location
  private double leftLoc;  // and left location
  private boolean detached;  // detached from ship?
  
  public Laser()
  {
    super(0, LASER_HEIGHT);
    reset();
  }

  public void reset()
  {
    alive = false;
    detached = true;
    lpDelay = 0;
    leftLoc = rightLoc = 0.0;
  }
  
  // return true if fired
  public boolean fire(int x, int y)
  {
    if (!alive)
    {
      jdAudio.play(jdAudio.sLaserFire);
      alive = true;
      detached = false;
      setXYWH(x, y, (int) LASER_SPEED, LASER_HEIGHT);
      rightLoc = x + LASER_SPEED;
      return(true);
    }
    return(false);
  }
  
  // true if moved out of screen
  public boolean move(IFO ifo)
  {
    if (alive)
    {
      if (StatVar.keySPACE && (StatVar.wType == WT_LASER) &&
          (lpDelay < LASER_P_DELAY) && !detached &&
          ifo.isAlive() && !ifo.isDying())
      {  // extend laser
        leftLoc = ifo.getX() + ifo.getW();
        lpDelay++;
      }
      else
      {  // detach from ship and keep moving
        detached = true;
        leftLoc += LASER_SPEED;
        
        if (leftLoc > F_WIDTH)
        {
          reset();
          return(true);
        }
      }
      rightLoc += LASER_SPEED;
      setXYWH(leftLoc, ifo.getCenterY(),
              (int) (rightLoc - leftLoc), LASER_HEIGHT);
    }
    
    return(false);
  }
  
  // return true if hit enemy
  public boolean checkHit(IFO ifo)
  {
    if (alive && area.intersects(ifo.getArea()))
    {  // hit enemy
      return(true);
    }
    return(false);
  }
  
  public void paint(Graphics g)
  {
    if (alive)
    {
      g.setColor(Color.blue);
      g.fillRect((int) getX(), (int) getY(), getW(), getH());
    }
  }
  
  public boolean isDetached()
  {
    return(detached);
  }
}
