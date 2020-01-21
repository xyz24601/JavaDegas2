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

import java.awt.Component;
import java.awt.Graphics;

public class WaveCommon
{
  protected boolean alive;
  protected GroupCommon eg[];
  protected int eNum;  // number of enemy in group
  protected int cgDelay;  // current delay counter
  protected int cGroup;  // current group counter
  
  public WaveCommon()
  {
    alive = false;
  }
  
  public void reset()
  {
    alive = true;
    cgDelay = 0;
    cGroup = 1;
  }
  
  public void killThem()
  {
    alive = false;
    for (int i = 0; i < eg.length; i++)
      eg[i].killThem();
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      for (int i = 0; i < eg.length; i++)
        eg[i].paint(g, c);
    }
  }
}
