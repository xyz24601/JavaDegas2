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

// class to handle diagonal beam
public class DiagBeam extends IFO implements StatConst
{
  private double dSpeed;
  
  public DiagBeam()
  {
    super(DBEAM_WIDTH, DBEAM_HEIGHT);
    
    // remember old trig class?
    // calculate x, y directional speed
    dSpeed = Math.sqrt(BEAM_SPEED * BEAM_SPEED / 2);
    reset();
  }
  
  public void reset()
  {
    alive = false;
  }
  
  // return true if fired
  public boolean fire(int x, int y)
  {
    if (!alive)
    {
      alive = true;
      setXY(x, y);
      return(true);
    }
    return(false);
  }
  
  // return true if moved out of screen
  public boolean move()
  {
    if (alive)
    {
      double tx = getX();
      double ty = getY();
      if ((tx > F_WIDTH) || (ty < G_TOP_MERGIN))
        reset();  // gone out of screen
      else
      {
        tx += dSpeed;  // move 45 degree up right
        ty -= dSpeed;
        setXY(tx, ty);
      }
    }
    return(!alive);
  }
  
  // return true if hit enemy
  public boolean checkHit(IFO ifo)
  {
    if (alive && area.intersects(ifo.getArea()))
    {  // hit enemy
      reset();  // remove beam
      return(true);
    }
    return(false);
  }
  
  public void paint(Graphics g)
  {
    if (alive)
    {
      g.setColor(Color.white);
      g.fillOval((int) getX(), (int) getY() + DBEAM_HEIGHT / 2, DBEAM_WIDTH / 2 + 1, DBEAM_HEIGHT / 2);
      g.fillOval((int) getX() + DBEAM_WIDTH / 2, (int) getY(), DBEAM_WIDTH / 2, DBEAM_HEIGHT / 2 + 1);
    }
  }
}
