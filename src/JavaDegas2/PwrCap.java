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

package JavaDegas2;

import java.awt.*;

// class to handle power capsule
public class PwrCap extends IFO implements StatConst
{
  private boolean option;
  private int imgC;
  
  public PwrCap()
  {
    super(P_WIDTH, P_HEIGHT);
    alive = false;
  }
  
  // return true if not alive
  public boolean move(Ship ship, Scores scores)
  {
    if (alive)
    {
      if (ship.checkPower(this))  // picked up by ship
      {
        alive = false;  // make it disappear
        scores.add(P_SCORE);
      }
      else
      {
        double tx = getX();
        if (tx < -P_WIDTH) // moved out of screen
          alive = false;
        else
          setXY(tx - SCROLL_SPEED, getY());
        
        // if ship is destroyed
        // option becomes special power cap
        if (option)
        {
          if (imgC < (jdImages.powerO.length * 2 - 1))
            imgC++;
          else
            imgC = 0;
        }
        else
        {
          if (imgC < (jdImages.power.length * 2 - 1))
            imgC++;
          else
            imgC = 0;
        }
      }
    }
    return(!alive);
  }
  
  // ix - center of x location
  // iy - center of y location
  // op - flag for option
  // return true if placed
  public boolean place(double ix, double iy, boolean op)
  {
    if (!alive)
    {  // align the center point
      setXY(ix - P_WIDTH / 2, iy - P_HEIGHT / 2);
      alive = true;
      option = op;
      imgC = 0;
      return(true);
    }
    return(false);
  }
  
  public boolean isOption()
  {
    return(option);
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      if (option)
        g.drawImage(jdImages.powerO[imgC / 2], (int) getX(), (int) getY(), c);
      else
        g.drawImage(jdImages.power[imgC / 2], (int) getX(), (int) getY(), c);
    }
  }
  
}
