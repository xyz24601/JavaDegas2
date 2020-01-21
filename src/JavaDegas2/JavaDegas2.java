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

// main class, but doesn't happen much here
public class JavaDegas2 implements StatConst
{
  private static jdFrame jdf;
  
  public static void main(String[] args)
  {
    jdf = new jdFrame();
    
    while (true)
    {
      try { Thread.sleep(M_DELAY); }
      catch (InterruptedException ie) { }

      if (!StatVar.paused)
        jdf.update();
      
      jdf.paint();
    }
  }
  
}
