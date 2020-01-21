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

// class to handle group of beams
public class BeamG implements StatConst
{
  private Beam beam[];
  private int bNum;  // current number of beams
  private int bDelay;  // add delay between beams
  
  public BeamG()
  {  // create all beams
    beam = new Beam[MAX_BEAM];
    for (int i = 0; i < beam.length; i++)
      beam[i] = new Beam();
    reset();
  }
  
  public void reset()
  {
    for (int i = 0; i < beam.length; i++)
      beam[i].reset();
    bNum = 0;
    bDelay = 100;  // make so it will fire immediately
  }
  
  // x - x location of firing position
  // y - y location of firing position
  public void fire(int x, int y)
  {
    if (bNum < MAX_BEAM)
    {
      for (int i = 0; i < MAX_BEAM; i++)
      {  // keep trying until valid fire
        if (beam[i].fire(x, y))
        {
          bNum++;
          break;
        }
      }
    }
  }
  
  // ifo - ship or option
  public void move(IFO ifo)
  {  // if ship is hit, can't fire any more
    if (ifo.isAlive() && !ifo.isDying() && StatVar.wType == WT_BEAM)
    {
      bDelay++;

      if ((StatVar.keySPACE) && (bDelay > BEAM_DELAY))
      {  // fire!
        bDelay = 0;
        int lPos;
        lPos = (int) ifo.getX() + ifo.getW();
        fire(lPos, (int) ifo.getCenterY());
      }
    }
    
    // move beams no matter what ship's condition
    for (int i = 0; i < beam.length; i++)
    {
      if (beam[i].move())
        bNum--;
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
    }
    return(false);
  }
  
  public void paint(Graphics g)
  {
    for (int i = 0; i < beam.length; i++)
      beam[i].paint(g);
  }
}
