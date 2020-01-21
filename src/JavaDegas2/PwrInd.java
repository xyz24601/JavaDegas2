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

package JavaDegas2;

import java.awt.*;

import JavaDegas2.weapon.WeapConst;

// class to handle power indicator at the bottom of the screen
public class PwrInd implements StatConst
{
  private int cPwr; // current power location
  
  public PwrInd()
  {
    cPwr = 0;
  }
  
  // reset is called when ship is destroyed
  public void reset()
  {
    if (0 != cPwr)  // if power is accumulated,
      cPwr = 1;  // bring back to 1st position
  }
  
  // zero is called when power is used
  public void zero()
  {
    cPwr = 0;
  }
  
  public void pickedPower()
  {
    cPwr++;
    if (cPwr > PI_MAX)  // too much power
      cPwr = 1;  // bring back to 1st position
  }
  
  public int getCurrentLoc()
  {
    return(cPwr);
  }
  
  public void paint(Graphics g, Component c)
  {
    final int GAP = 5;  // used to fine tune the location
    final int VGAP = 10;
    for (int i = 0; i < jdImages.pInd.length; i++)
    {
      g.drawImage(jdImages.pInd[i], (PIP_WIDTH * i) + (GAP * (i + 2)), PIP_TOP - VGAP, c);
    }

    // blank out if already in use
    if (StatVar.hasBomb)
      g.drawImage(jdImages.pIndBlank, PIP_WIDTH + (GAP * 3), PIP_TOP - VGAP, c);
    
    if (StatVar.wType == WeapConst.WT_DOUBLE)
      g.drawImage(jdImages.pIndBlank, (PIP_WIDTH * 2) + (GAP * 4), PIP_TOP - VGAP, c);
    else if (StatVar.wType == WeapConst.WT_LASER)
      g.drawImage(jdImages.pIndBlank, (PIP_WIDTH * 3) + (GAP * 5), PIP_TOP - VGAP, c);

    if (StatVar.maxOption)
      g.drawImage(jdImages.pIndBlank, (PIP_WIDTH * 4) + (GAP * 6), PIP_TOP - VGAP, c);
      
    if (StatVar.hasShield)
      g.drawImage(jdImages.pIndBlank, (PIP_WIDTH * 5) + (GAP * 7), PIP_TOP - VGAP, c);

    // highlight current power location
    if (cPwr != 0)
    {
      g.drawImage(jdImages.pIndLit[cPwr - 1], (int) (PIP_WIDTH * (cPwr - 1)) + (GAP * (cPwr + 1)), PIP_TOP - VGAP, c);
    
      // blank out if already in use
      if ((cPwr == 2) && StatVar.hasBomb)
        g.drawImage(jdImages.pIndBlankLit, PIP_WIDTH + (GAP * 3), PIP_TOP - VGAP, c);
      else if ((cPwr == 3) &&
               (StatVar.wType == WeapConst.WT_DOUBLE))
        g.drawImage(jdImages.pIndBlankLit, (PIP_WIDTH * 2) + (GAP * 4), PIP_TOP - VGAP, c);
      else if ((cPwr == 4) &&
               (StatVar.wType == WeapConst.WT_LASER))
        g.drawImage(jdImages.pIndBlankLit, (PIP_WIDTH * 3) + (GAP * 5), PIP_TOP - VGAP, c);
      else if ((cPwr == 5) && StatVar.maxOption)
        g.drawImage(jdImages.pIndBlankLit, (PIP_WIDTH * 4) + (GAP * 6), PIP_TOP - VGAP, c);
      else if ((cPwr == 6) && StatVar.hasShield)
        g.drawImage(jdImages.pIndBlankLit, (PIP_WIDTH * 5) + (GAP * 7), PIP_TOP - VGAP, c);
    }
  }
}
