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

public class Enemy4G1W1 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 5;

  private double speed;  // base speed
  private int gDelay;  // delay between groups
  private int mGroup;  // number of groups in wave
  
  public Enemy4G1W1()
  {
    super();
    eg = new Enemy4G1[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy4G1();
  }
  
  public void reset()
  {
    super.reset();
    speed = E4G1W1_SPEED + ((double) StatVar.level / 2);
    gDelay = E4G1W1_GDELAY - (StatVar.level * 5);
    mGroup = E4G1W1_NUM_GROUP + StatVar.level;
    eNum = E4G1W1_NUM_ENEMY + StatVar.level;
    ((Enemy4G1) eg[0]).reset(speed, false, eNum);
  }
  
  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      for (int i = 0; i < eg.length; i++)
      {
        if (((Enemy4G1) eg[i]).move(ship, efg, pCap))
          bo = true; // at lease one is alive
      }
      
      cgDelay++;  // increase delay between groups
      if ((cgDelay > gDelay) && (cGroup < mGroup))
      {  // send in another group
        for (int i = 0; i < eg.length; i++)
        {  // every 3rd group is power
          if (((Enemy4G1) eg[i]).reset(speed, (cGroup % 3) == 0 ? true : false, eNum))
          {
            cGroup++;
            cgDelay = 0;
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
