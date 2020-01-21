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

// class to handle group of bombs
// actually it only deals with ONE bomb now
public class BombG implements StatConst
{
  private Bomb bomb;
  private int bDelay;  // add delay between bombs
  
  public BombG()
  {
    bomb = new Bomb();
    reset();
  }
  
  public void reset()
  {
    bomb.reset();
    bDelay = 100;  // make so it will drop immediately
  }
  
  public void drop(int x, int y)
  {
    bomb.drop(x, y);
  }
  
  public void move(IFO ifo)
  {
    if (ifo.isAlive() && !ifo.isDying() && StatVar.hasBomb)
    {
      bDelay++;
      
      if (StatVar.keySPACE && (bDelay > BOMB_DELAY))
      {  // drop!
        bDelay = 0;
        drop((int) ifo.getCenterX(), (int) ifo.getY() + ifo.getH());
      }
    }
    
    // move bombs no matter what ship's condition
    bomb.move();
  }
  
  // return true if hit enemy
  public boolean checkHit(IFO ifo)
  {
    if (bomb.checkHit(ifo))
      return(true);
    else
      return(false);
  }
  
  public void paint(Graphics g, Component c)
  {
    bomb.paint(g, c);
  }

}
