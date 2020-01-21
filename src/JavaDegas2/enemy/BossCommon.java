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

public abstract class BossCommon extends EnemyCommon
{
  protected double hCount[];  // hit count
  protected int hDelay[];  // add delay between hits
  
  public BossCommon(int w, int h)
  {
    super(w, h);
    hCount = new double[1];
    hDelay = new int[1];
  }
  
  public void reset(double xl, double yl)
  {
    super.reset();
    setXY(xl, yl);
    hCount[0] = 0.0;
    hDelay[0] = 100;
  }
  
  public void setReady()
  { ready = true; }
 
}
