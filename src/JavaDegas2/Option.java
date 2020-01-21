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
import JavaDegas2.weapon.*;

// class to handle option
public class Option extends IFO implements StatConst
{
  private Weapons weapons;  // option also fires
  private int imgC;
  
  public Option()
  {
    super(O_WIDTH, O_HEIGHT);
    weapons = new Weapons();
    reset();
  }

  public void move(double dx, double dy)
  {
    if (alive)
    {
      setXY(dx, dy);
      
      if (imgC < (jdImages.option.length * 2 - 1))
        imgC++;
      else
        imgC = 0;
    }

    weapons.move(this);
  }
  
  public void reset()
  {
    alive = false;
    weapons.reset();
  }
  
  public void add(double x, double y)
  {
    alive = true;
    setXY(x, y);
  }
  
  public void delete()
  {
    alive = false;
  }
  
  public Weapons getWeapons()
  {
    return(weapons);
  }
  
  public void paint(Graphics g, Component c)
  {
    weapons.paint(g, c);
    
    if (alive)
    {
      g.drawImage(jdImages.option[imgC / 2], (int) getX(), (int) getY(), c);
    }
  }
}
