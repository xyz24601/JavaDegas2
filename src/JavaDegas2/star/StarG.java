package JavaDegas2.star;

import java.awt.*;
import JavaDegas2.*;

// class to handle group of stars
public class StarG implements StatConst
{
  private Star star[] = new Star[STAR_NUM];
  private boolean west, east, north, south;
  
  public StarG()
  {
    for (int i = 0; i < STAR_NUM; i++)
    {
      // initialize them in random location
      star[i] = new Star((int) Math.round(Math.random() * STAR_MAX_SIZE) + STAR_MIN_SIZE,
                         (Math.random() * STAR_MAX_SPEED) + 0.1);
    }
    stop();
  }
  
  public void paint(Graphics g)
  {
    for (int i = 0; i < STAR_NUM; i++)
      star[i].paint(g);
  }
  
  public void move()
  {
    for (int i = 0; i < STAR_NUM; i++)
      star[i].move(west, east, north, south);
  }
  
  public void stop()
  {
    west = east = north = south = false;
  }
  
  public void goWest()
  {
    west = true;
    east = north = south = false;
  }
  
  public void goEast()
  {
    east = true;
    west = north = south = false;
  }
  
  public void goNorth()
  {
    north = true;
    west = east = south = false;
  }
  
  public void goSouth()
  {
    south = true;
    west = east = north = false;
  }
  
}  