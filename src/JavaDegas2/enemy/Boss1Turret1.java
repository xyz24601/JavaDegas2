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

public class Boss1Turret1 extends BossCommon implements StatConst
{
  private int fdelay;
  private int mdelay;
  private boolean firework;
  
  public Boss1Turret1()
  {
    super(B1R1_WIDTH, B1R1_HEIGHT);
    firework = false;  // default to single shot
  }
  
  public void reset(double xLoc, double yLoc)
  {
    super.reset(xLoc,  yLoc);
    fdelay = 0;
    mdelay = B1R1_FDELAY / StatVar.level;
  }
  
  // enable to toggle between single shot and burst
  public void setFirework(boolean ib)
  {
    firework = ib;
  }
  
  public void fdcut()
  {
    mdelay = B1R1_FDELAY / (StatVar.level * 2);
  }
  
  public boolean move(Ship ship, EFireG efireg, Coord coord,
                      double xLoc, double yLoc)
  {
    if (alive)
    {
      if (ready)
      {
        if (fdelay < 0)
        {
          if (firework)
          {
            efireg.firework(this, ship, B1R1_BNUM);
            fdelay = (int) (mdelay * 2.5);  // don't fire too much
          }
          else
          {
            efireg.fire(this, ship);
            fdelay = mdelay;
          }
        }
        else
        { fdelay--; }
      }
      setXY(xLoc, yLoc);
    }
  
    return(alive);
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      if (dying)
        g.drawImage(jdImages.b1turG, (int) getX(), (int) getY(), c);
      else
        g.drawImage(jdImages.b1tur, (int) getX(), (int) getY(), c);
    }
  }

}
