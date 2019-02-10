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
