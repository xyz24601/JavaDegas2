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

// generic class for follow the leader kind of group
public class FollowGroup extends GroupCommon
{
  protected int cEnum;  // current number of enemy
  protected int mEnum;  // current max number of enemy
  protected int cDelay;  // current delay
  protected int eDelay;  // delay between enemy
  
  public FollowGroup()
  {
    super();
  }
  
  // iDelay - delay between enemy
  public void reset(int iDelay)
  {
    super.reset();
    cEnum = 1;
    cDelay = 0;
    eDelay = iDelay;
  }
  
  // eAbsMax - absolute maximum number of enemy
  public void addEnemy(int eAbsMax)
  {
    if (cDelay < eDelay)
      cDelay++;  // add delay between
    else if (cEnum < eAbsMax)
    {
      cEnum++;  // add another enemy
      cDelay = 0;
    }
  }
  
  public void placePower(PwrCapG pCap)
  {
    if (!alive && (0.0 != coord.getX()))
    {  // placer power capsule
      pCap.place(coord.getX(), coord.getY(), false);
    }
  }
}
