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

// class to handle all weapons for ship and option
public class Weapons implements StatConst
{
  private BeamG beamg;
  private DiagBeamG dbeamg;
  private LaserG laserg;
  private BombG bombg;
  
  public Weapons()
  {  // create all weapons
    beamg = new BeamG();
    dbeamg = new DiagBeamG();
    laserg = new LaserG();
    bombg = new BombG();
  }
  
  public void reset()
  {
    StatVar.wType = WT_BEAM;  // reset to beam
    StatVar.hasBomb = false;
    
    beamg.reset();
    dbeamg.reset();
    laserg.reset();
    bombg.reset();
  }
  
  public void move(IFO ifo)
  {
    beamg.move(ifo);
    dbeamg.move(ifo);
    laserg.move(ifo);
    bombg.move(ifo);
  }
  
  public boolean checkHit(IFO ifo)
  {
    if (beamg.checkHit(ifo) || dbeamg.checkHit(ifo) ||
        laserg.checkHit(ifo) || bombg.checkHit(ifo))
      return(true);
    else
      return(false);
  }

  public void paint(Graphics g, Component c)
  {
    beamg.paint(g);
    dbeamg.paint(g);
    laserg.paint(g);
    bombg.paint(g, c);
  }
}
