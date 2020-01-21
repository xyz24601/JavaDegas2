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
// 2 groups appear in smaller wave
public class Enemy2G1W2 extends WaveCommon implements StatConst 
{
  private final int MAX_EG = 6;
  // movement centers
  private final int M_CENTER1 =
      (G_HEIGHT - E2_HEIGHT) / 4 + G_TOP_MERGIN / 2;
  private final int M_CENTER2 =
      (G_HEIGHT - E2_HEIGHT) * 3 / 4 + G_TOP_MERGIN / 2;
  // movement height
  private final int M_HEIGHT =
      (G_HEIGHT - G_TOP_MERGIN - E2_HEIGHT) / 2;
  
  private double xSpeed;  // enemy x speed
  private double rSpeed;  // enemy radian speed
  private int eDelay;  // delay between enemy
  private int gDelay;  // delay between groups
  private int mGroup;  // number of groups in wave
  private boolean top;  // flag to switch
  
  public Enemy2G1W2()
  {
    super();
    eg = new Enemy2G1[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy2G1();
  }
  
  public void reset()
  {
    super.reset();
    top = true;
    xSpeed = E2G1W2_XSPEED + ((double) StatVar.level / 2);
    rSpeed = E2G1W2_RSPEED + ((double) StatVar.level / 100);
    eDelay = E2G1W2_EDELAY - (StatVar.level / 2);
    gDelay = E2G1W2_GDELAY - (StatVar.level * 5);
    mGroup = E2G1W2_NUM_GROUP + StatVar.level;
    eNum = E2G1W2_NUM_ENEMY + StatVar.level;
    ((Enemy2G1) eg[0]).reset(M_CENTER1, M_HEIGHT, -Math.PI / 2, xSpeed, rSpeed, E2_FIRE_BOTTOM, false, false, eNum, eDelay);
    ((Enemy2G1) eg[1]).reset(M_CENTER1, M_HEIGHT, Math.PI / 2, xSpeed, rSpeed, E2_FIRE_TOP, false, false, eNum, eDelay);
    top = !top;  // alternate beginning location
  }

  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      for (int i = 0; i < MAX_EG; i++)
      {
        if (((Enemy2G1) eg[i]).move(ship, efg, pCap))
          bo = true;  // at lease one is alive
      }
      
      cgDelay++;  // increase delay
      if ((cgDelay > gDelay)  && (cGroup < mGroup))
      {  // send in another wave
        for (int i = 0; i < MAX_EG; i += 2)
        {
          int center;
          if (top)
            center = M_CENTER1;
          else
            center = M_CENTER2;
          
          if (((Enemy2G1) eg[i]).reset(center, M_HEIGHT, -Math.PI / 2, xSpeed, rSpeed, E2_FIRE_BOTTOM, false, false, eNum, eDelay) &&
              ((Enemy2G1) eg[i + 1]).reset(center, M_HEIGHT, Math.PI / 2, xSpeed, rSpeed, E2_FIRE_TOP, false, false, eNum, eDelay))
          {
            cGroup++;
            cgDelay = 0;
            top = !top;
            break;
          }
        }
      }
      
      if (!bo && (cGroup >= mGroup))
        alive = false;  // all done
    }
    return(alive);
  }
}
