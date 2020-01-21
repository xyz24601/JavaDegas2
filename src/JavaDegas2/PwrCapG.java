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

// class to handle ALL power capsules
public class PwrCapG implements StatConst
{
  private PwrCap caps[];  // storage for all capsules
  private int cCaps;  // keep track of current capsule
  
  public PwrCapG()
  {  // create all power capsules
    caps = new PwrCap[P_MAX_CAP];
    for (int i = 0; i < caps.length; i++)
      caps[i] = new PwrCap();
    cCaps = 0;
  }
  
  public void deleteAll()
  {
    for (int i = 0; i < caps.length; i++)
      caps[i].alive = false;
    cCaps = 0;
  }
  
  public void move(Ship ship, Scores scores)
  {
    for (int i = 0; i < caps.length; i++)
      if (caps[i].move(ship, scores))
        cCaps--;  // not alive, decrease count
  }
  
  // ix - center of x location
  // iy - center of y location
  // op - flag for option
  public void place(double ix, double iy, boolean op)
  {
    if (cCaps < P_MAX_CAP)
    {
      for (int i = 0; i < P_MAX_CAP; i++)
      {  // keep trying to place until successful
        if (caps[i].place(ix, iy, op))
        {
          cCaps++;
          break;
        }
      }
    }
  }
    
  public void paint(Graphics g, Component c)
  {
    for (int i = 0; i < caps.length; i++)
      caps[i].paint(g, c);
  }

}
