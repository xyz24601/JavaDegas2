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

// class to handle wave of enemy2 groups
// each enemy appear taking turn and remains on screen
public class Enemy2G1W3 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 2;
  // movement center
  private final int M_CENTER =
      (G_HEIGHT - E2_HEIGHT) / 2 + G_TOP_MERGIN / 2;
  // movement height
  private final int M_HEIGHT =
      G_HEIGHT - G_TOP_MERGIN - E2_HEIGHT;
      
  private double xSpeed;  // enemy x speed
  private double rSpeed;  // enemy radian speed
  private int eDelay;  // delay between enemy
  private int gDelay;  // delay between groups
  
  public Enemy2G1W3()
  {
    super();
    eg = new Enemy2G1[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy2G1();
  }
  
  public void reset()
  {
    super.reset();
    xSpeed = E2G1W3_XSPEED + ((double) StatVar.level / 2);
    rSpeed = E2G1W3_RSPEED + ((double) StatVar.level / 100);
    eDelay = E2G1W3_EDELAY - (StatVar.level / 2);
    gDelay = eDelay / 2;
    eNum = E2G1W3_NUM_ENEMY;
    ((Enemy2G1) eg[0]).reset(M_CENTER, M_HEIGHT, -Math.PI * 3 / 4, xSpeed, rSpeed, E2_FIRE_BOTH, false, true, eNum, eDelay);
    ((Enemy2G1) eg[1]).reset(M_CENTER, M_HEIGHT, Math.PI / 4, xSpeed, rSpeed, E2_FIRE_BOTH, false, true, eNum, eDelay);
  }

  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      if (((Enemy2G1) eg[0]).move(ship, efg, pCap))
          bo = true;  // at lease one is alive
      
      cgDelay++;  // increase delay
      if (cgDelay > gDelay)
      {  // add delay, so enemy appear alternatively
        if (((Enemy2G1) eg[1]).move(ship, efg, pCap))
          bo = true;  // at lease one is alive
      }
      
      if (!bo)
        alive = false;
    }
    return(alive);
  }

}
