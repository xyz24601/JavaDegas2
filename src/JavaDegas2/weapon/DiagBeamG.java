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

package JavaDegas2.weapon;

import java.awt.*;
import JavaDegas2.*;

// class to handle diagonal beam AND regular beam
public class DiagBeamG implements StatConst
{
  private Beam beam[];
  private DiagBeam dBeam[];
  private int bNum;  // current number of beams
  private int dNum;  // current number of diag beams
  private int bDelay;  // add delay between beams
  private int dDelay;  // add delay between diag beams
  
  public DiagBeamG()
  {  // create all beams
    beam = new Beam[MAX_DBEAM];
    dBeam = new DiagBeam[MAX_DBEAM];
    for (int i = 0; i < beam.length; i++)
    {
      beam[i] = new Beam();
      dBeam[i] = new DiagBeam();
    }
    reset();
  }
  
  public void reset()
  {
    for (int i = 0; i < beam.length; i++)
    {
      beam[i].reset();
      dBeam[i].reset();
    }
    bNum = dNum = 0;
    bDelay = dDelay = 100;  // make it so it will fire immediately
  }
  
  // x - x location of firing position
  // y - y location of firing position
  public void fireForward(int x, int y)
  {
    if (bNum < MAX_DBEAM)
    {
      for (int i = 0; i < MAX_DBEAM; i++)
      {
        if (beam[i].fire(x, y))
        {
          bNum++;
          break;
        }
      }
    }
  }
  
  // x - x location of firing position
  // y - y location of firing position
  public void fireDiagonal(int x, int y)
  {
    if (dNum < MAX_DBEAM)
    {
      for (int i = 0; i < MAX_DBEAM; i++)
      {
        if (dBeam[i].fire(x, y))
        {
          dNum++;
          break;
        }
      }
    }
  }
  
  // ifo - ship or option
  public void move(IFO ifo)
  {  // if ship is hit, can't fire any more
    if (ifo.isAlive() && !ifo.isDying() && StatVar.wType == WT_DOUBLE)
    {
      bDelay++;
      dDelay++;
      int lPos = (int) ifo.getX() + ifo.getW();
      
      // fire forward beam
      if ((StatVar.keySPACE) && (bDelay > DBEAM_DELAY))
      {  // fire!
        bDelay = 0;
        fireForward(lPos, (int) ifo.getCenterY());
      }
      
      // fire diagonal beam
      if ((StatVar.keySPACE)&& (dDelay > DBEAM_DELAY))
      {  // fire diagonal
        dDelay = 0;
        fireDiagonal(lPos, (int) ifo.getCenterY());
      }
    }
    
    // move beams no matter what ship's condition
    for (int i = 0; i < beam.length; i++)
    {
      if (beam[i].move())
        bNum--;
      if (dBeam[i].move())
        dNum--;
    }
  }
  
  // return true if hit enemy
  public boolean checkHit(IFO ifo)
  {
    for (int i = 0; i < beam.length; i++)
    {
      if (beam[i].checkHit(ifo))
      {
        bNum--;
        return(true);
      }
      if (dBeam[i].checkHit(ifo))
      {
        dNum--;
        return(true);
      }
    }
    return(false);
  }
  
  public void paint(Graphics g)
  {
    for (int i = 0; i < beam.length; i++)
    {
      beam[i].paint(g);
      dBeam[i].paint(g);
    }
  }

}
