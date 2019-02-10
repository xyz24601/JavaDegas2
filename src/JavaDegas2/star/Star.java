package JavaDegas2.star;

import java.awt.*;
import JavaDegas2.*;

// class to handle a star
public class Star implements StatConst
{
  private int size;
  private double speed;
  private int colorI;
  private Coord coord;
  private int delayC;
  
  public Star(int size, double speed)
  {
    this.speed = speed;
    this.size = size;
    
    // randomize initial color
    colorI = (int) Math.round(Math.random() * (CNG_COLORS - 1)); 
    
    coord = new Coord();
    
    // randomize initial location
    coord.setXY(Math.random() * F_WIDTH,
                Math.random() * G_HEIGHT);
    
    delayC = 0;
  }
  
  public void move(boolean west, boolean east, boolean north, boolean south)
  {
    if (west || east || north || south)
    {
      double x = coord.getX();
      double y = coord.getY();
      
      if (west)
      {
        x -= speed;
        if (x < -size)  // moved west of screen
          x = F_WIDTH;  // re-appear from the east
      }
      if (east)
      {
        x += speed;
        if (x > F_WIDTH)  // moved east of screen
          x = -size;      // re-appear from west
      }
      if (north)
      {
        y -= speed;
        if (y < -size)  // moved north of screen
          y = F_HEIGHT; // re-appear from south
      }
      if (south)
      {
        y += speed;
        if (y > F_HEIGHT)  // moved south of screen
          y = -size;       // re-appear from north
      }
      coord.setXY(x, y);
    }
    
    if (colorI < (CNG_COLORS - 1))
      colorI++;
    else
      colorI = 0;
    
    if (0 == delayC)
    {
      if (Math.random() > 0.99)
        delayC = 1;  // star is shining
    }
    else if (delayC > STAR_SHINE)
      delayC = 0;
    else
      delayC++;
  }

  public void paint(Graphics g)
  {
    int cx = (int) coord.getX();
    int cy = (int) coord.getY();
    
    g.setColor(ColorChange.gColors[colorI]);
    g.fillOval(cx, cy, size, size);
    
    if (0 != delayC)
    {
      g.drawLine(cx - size / 2, cy + size / 2,
                 cx + size * 3 / 2, cy + size / 2);
      g.drawLine(cx + size / 2, cy - size / 2,
                 cx + size / 2, cy + size * 3 / 2);
    }
  }
}
