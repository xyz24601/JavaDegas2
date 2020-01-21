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

// handle boss1
public class Boss1W implements StatConst
{
  private boolean alive;
  private Boss1G b1g;
  private double bSpeed;
  
  public Boss1W()
  {
    alive = false;
    b1g = new Boss1G();
  }
  
  public void reset()
  {
    alive = true;
    bSpeed = B1W_SPEED + StatVar.level;
    b1g.reset(bSpeed); 
  }
  
  public void killThem()
  {
    alive = false;
    b1g.killThem();
  }
  
  // return true if aliave
  public boolean move(Ship ship, EFireG efg)
  {
    if (alive)
    {
      alive = b1g.move(ship, efg); 
    }
    return(alive);
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      b1g.paint(g, c);
    }
  }
}
