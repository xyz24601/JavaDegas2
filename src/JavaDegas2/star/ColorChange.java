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

package JavaDegas2.star;

import java.awt.*;
import JavaDegas2.*;

// class to handle changing colors for the star
public class ColorChange implements StatConst
{
  static public Color gColors[] = new Color[CNG_COLORS];
  
  static
  {
    double d = 0.0;
    for (int i = 0; i < CNG_COLORS; i++, d += (1.0 / CNG_COLORS))
      gColors[i] = Color.getHSBColor((float)d, 1.0F, 1.0F); 
  }

}
