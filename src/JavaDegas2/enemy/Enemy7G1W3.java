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

// appears vertically lined up
public class Enemy7G1W3 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 4;
  
  private double speed;
  private int gDelay;  // delay between groups
  private int mGroup;  // number of groups in wave
  private int xLoc1 = F_WIDTH / 7;
  private int xLoc2 = F_WIDTH * 6 / 7;
  private int fd;  // fire delay
  
  public Enemy7G1W3()
  {
    super();
    eg = new Enemy7G1[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy7G1();
  }
  
  public void reset()
  {
    super.reset();
    speed = E7G1W1_SPEED + ((double) StatVar.level / 2);
    gDelay = E7G1W1_GDELAY - (StatVar.level * 5);
    mGroup = E7G1W1_NUM_GROUP + StatVar.level;
    eNum = E7G1W1_NUM_ENEMY + StatVar.level;
    fd = E7G1_FDELAY - (StatVar.level * 25);
    ((Enemy7G1) eg[0]).reset(speed, xLoc1, fd, false, eNum);
    ((Enemy7G1) eg[1]).reset(speed, xLoc2, fd, false, eNum);
  }

  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      for (int i = 0; i < eg.length; i++)
      {
        if (((Enemy7G1) eg[i]).move(ship, efg, pCap))
          bo = true;  // at least one is alive
      }
      
      cgDelay++;  // increase delay between groups
      if ((cgDelay > gDelay) && (cGroup < mGroup))
      {  // send in another group
        for (int i = 0; i < eg.length; i += 2)
        {  // every 3rd group is power
          if (((Enemy7G1) eg[i]).reset(speed, xLoc1, fd, (cGroup % 3) == 0 ? true : false, eNum))
          {
            if (((Enemy7G1) eg[i + 1]).reset(speed, xLoc2, fd, (cGroup % 3) == 0 ? true : false, eNum))
            {
              cGroup++;
              cgDelay = 0;
              break;
            }
            else  // has to appear in a pair
              eg[i].alive = false;
          }
        }
      }
      
      if (!bo && (cGroup >= mGroup))
        alive = false;  // all done
    }
    return(alive);
  }
  
}
