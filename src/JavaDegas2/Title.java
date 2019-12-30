package JavaDegas2;

import java.awt.*;

public class Title
{

  public void paint(Graphics g, Component c)
  {
    g.drawImage(jdImages.ttl, 150, 25, c);
    
    g.drawImage(jdImages.ttlUp, 295, 330, c);
    g.drawImage(jdImages.ttlDown, 275, 425, c);
    g.drawImage(jdImages.ttlLeft, 75, 375, c);
    g.drawImage(jdImages.ttlRight, 475, 375, c);

    g.drawImage(jdImages.shipSide[1], 355, 395, c);
    
    g.drawImage(jdImages.ttlFire, 75, 500, c);
    g.drawImage(jdImages.ttlPower, 475, 500, c);
  }
}
