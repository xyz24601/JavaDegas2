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

import JavaDegas2.*;

// each enemy appears one by one
public class Enemy3G1W1 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 1;
  private double xSpeed;  // x speed
  private double rSpeed;  // radian speed
  private int mBounce;  // max bounce
  private int eDelay;  // delay between enemy
  
  public Enemy3G1W1()
  {
    super();
    eg = new Enemy3G1[MAX_EG];
    eg[0] = new Enemy3G1();
  }
  
  public void reset()
  {
    super.reset();
    xSpeed = E3G1W1_XSPEED + ((double) StatVar.level / 2);
    rSpeed = E3G1W1_RSPEED + ((double) StatVar.level / 100);
    mBounce = E3G1W1_MAX_BOUNCE + StatVar.level;
    eDelay = E3G1W1_EDELAY - (StatVar.level / 2);
    eNum = E3G1W1_NUM_ENEMY;
    ((Enemy3G1) eg[0]).reset(G_HEIGHT * 3 / 4, G_HEIGHT / 4, xSpeed, rSpeed, mBounce, false, eNum, eDelay);
  }
  
  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      if (!((Enemy3G1) eg[0]).move(ship, efg, pCap))
        alive = false;
    }
    return(alive);
  }

}
