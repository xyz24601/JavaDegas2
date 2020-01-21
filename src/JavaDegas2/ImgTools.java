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
import java.awt.image.*;

public class ImgTools
{
  public static BufferedImage loadBufImage(String imgName, Component c)
  {
    Image i = c.getToolkit().getImage(imgName);
    waitForIt(i, c);
    BufferedImage bi = new BufferedImage(i.getWidth(c), i.getHeight(c), BufferedImage.TYPE_INT_BGR);
    Graphics2D g2d = bi.createGraphics();
    g2d.drawImage(i, 0, 0, c);
    return(bi);
  }
  
  public static boolean waitForIt(Image image, Component c)
  {
    MediaTracker t = new MediaTracker(c);
    t.addImage(image, 0);
    try
    {
      t.waitForAll();
    }
    catch (Exception e)
    {
      System.err.println(e.getMessage());
    }
    
    return(!t.isErrorAny());
  }
 
  public static BufferedImage[] loadMultiImg(String img[], String imgDir, Component c)
  {
    BufferedImage bi[];
    
    bi = new BufferedImage[img.length];
    for (int x = 0; x< img.length; x++)
      bi[x] = loadBufImage(imgDir + img[x], c);
    return(bi);
  }
}
