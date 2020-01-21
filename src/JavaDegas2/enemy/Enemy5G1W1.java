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

public class Enemy5G1W1 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 6;
  private double eSpeed;  // enemy speed
  private int eDelay;  // delay between enemy
  private int gDelay;  // delay between groups
  private int yl1, yl2;  // y location for each groups

  public Enemy5G1W1()
  {
    super();
    eg = new Enemy5G1[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy5G1();
  }
  
  public void reset()
  {
    super.reset();
    eSpeed = E5G1W1_SPEED + ((double) StatVar.level / 4);
    eDelay = E5G1W1_EDELAY - (StatVar.level / 5);
    gDelay = E5G1W1_GDELAY - (StatVar.level * 5);
    eNum = E5G1_MAXNUM;
    yl1 = G_TOP_MERGIN + E5_HEIGHT;
    yl2 = G_HEIGHT - (E5_HEIGHT * 2);
    ((Enemy5G1) eg[0]).reset(yl1, eSpeed, eDelay);
    ((Enemy5G1) eg[1]).reset(yl2, eSpeed, eDelay);
    updateY();
  }

  private void updateY()
  {
    yl1 += E5_HEIGHT * 2;
    yl2 -= E5_HEIGHT * 2;
  }
  
  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      for (int i = 0; i < MAX_EG; i++)
      {
        if (((Enemy5G1) eg[i]).move(ship, efg, pCap))
          bo = true;
      }
      
      cgDelay++;  // increase delay
      if ((cgDelay > gDelay) && (yl2 > (G_TOP_MERGIN + E5_HEIGHT)))
      {  // send in another pair  
        for (int i = 0; i < MAX_EG; i += 2)
        {
          if (((Enemy5G1) eg[i]).reset(yl1, eSpeed, eDelay))
          {
            if (((Enemy5G1) eg[i + 1]).reset(yl2, eSpeed, eDelay))
            {
              cgDelay = 0;
              updateY();
              break;
            }
            else  // has to appear in a pair
              eg[i].alive = false;
          }
        }
      }
      
      if (!bo && (yl2 < (G_TOP_MERGIN + E5_HEIGHT)))
        alive = false;  // all done
    }
    return(alive);
  }
}
