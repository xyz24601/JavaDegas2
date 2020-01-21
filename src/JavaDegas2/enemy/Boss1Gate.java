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

public class Boss1Gate extends BossCommon implements StatConst
{
  public Boss1Gate()
  {
    super(B1T_WIDTH, B1T_HEIGHT);
  }
  
  public void reset(double xLoc, double yLoc)
  {
	  super.reset(xLoc,  yLoc);
  }

  public boolean move(Ship ship, EFireG efireg, Coord coord,
		              double xLoc, double yLoc)
  {
	  if (alive)
	  {
	    if (ready)
	    {
		    if (checkHit(ship, hCount, B1T_MHIT, hDelay, B1T_MHDELAY, B1T_SCORE, coord))
		    {
    		  jdAudio.play(jdAudio.sBossHit);
    		  efireg.firework(this, ship, B1T_BNUM);
  	  	  alive = false;
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
        g.drawImage(jdImages.bBarGrey, (int) getX(), (int) getY(), c); 
      else if (ready)
        g.drawImage(jdImages.bBarBlue, (int) getX(), (int) getY(), c); 
      else
        g.drawImage(jdImages.bBarRed, (int) getX(), (int) getY(), c); 
    }
  }

}
