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

// boss 1 core section
public class Boss1Core extends BossCommon implements StatConst 
{
  public Boss1Core()
  {
    super(B1C_WIDTH, B1C_HEIGHT);
  }
  
  public void reset(double xLoc, double yLoc)
  {
    super.reset(xLoc, yLoc);
  }
  
  // return true if alive
  public boolean move(Ship ship, EFireG efireg, Coord coord,
                      double xLoc, double yLoc)
  {
    if (alive)
    {
      if (dying)
      {
        dyingDelay(B1_DYING);
        return(alive);
      }
      
      if (ready)
      {
        if (checkHit(ship, hCount, B1C_MHIT, hDelay, B1C_MHDELAY, B1C_SCORE, coord))
        {
          dying = true;
          jdAudio.play(jdAudio.sBossExp);
          return(alive);  // alive, but dying
        }
      }
      setXY(xLoc, yLoc);
      hDelay[0]++;
    }
    return(alive);
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      if (dying)
        g.drawImage(jdImages.bcGrey, (int) getX(), (int) getY(), c); 
      else if (ready)
        g.drawImage(jdImages.bcBlue, (int) getX(), (int) getY(), c); 
      else
        g.drawImage(jdImages.bcRed, (int) getX(), (int) getY(), c); 
    }
  }
}
