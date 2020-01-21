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

package JavaDegas2.star;

import java.awt.*;
import JavaDegas2.*;

// class to handle group of stars
public class StarG implements StatConst
{
  private Star star[] = new Star[STAR_NUM];
  private boolean west, east, north, south;
  
  public StarG()
  {
    for (int i = 0; i < STAR_NUM; i++)
    {
      // initialize them in random location
      star[i] = new Star((int) Math.round(Math.random() * STAR_MAX_SIZE) + STAR_MIN_SIZE,
                         (Math.random() * STAR_MAX_SPEED) + 0.1);
    }
    stop();
  }
  
  public void paint(Graphics g)
  {
    for (int i = 0; i < STAR_NUM; i++)
      star[i].paint(g);
  }
  
  public void move()
  {
    for (int i = 0; i < STAR_NUM; i++)
      star[i].move(west, east, north, south);
  }
  
  public void stop()
  {
    west = east = north = south = false;
  }
  
  public void goWest()
  {
    west = true;
    east = north = south = false;
  }
  
  public void goEast()
  {
    east = true;
    west = north = south = false;
  }
  
  public void goNorth()
  {
    north = true;
    west = east = south = false;
  }
  
  public void goSouth()
  {
    south = true;
    west = east = north = false;
  }
  
}  