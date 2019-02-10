package JavaDegas2.weapon;

import java.awt.*;
import JavaDegas2.*;

// class to handle a bomb
public class Bomb extends IFO implements StatConst
{
  public Bomb()
  {
    super(BOMB_WIDTH, BOMB_HEIGHT);
    reset();
  }
  
  public void reset()
  {
    alive = false;
  }
  
  // return true if dropped
  public boolean drop(int x, int y)
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
      double ty = getY();
      if (ty > G_HEIGHT)  // gone out of screen
        reset();
      else
      {
        ty += BOMB_SPEED;
        setXY(getX(), ty);
      }
    }
    return(!alive);
  }
  
  // return true if hit enemy
  public boolean checkHit(IFO ifo)
  {
    if (alive && area.intersects(ifo.getArea()))
    {  // hit enemy
      reset();  // remove bomb
      return(true);
    }
    return(false);
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      g.drawImage(jdImages.bomb, (int) getX(), (int) getY(), c);
    }
  }

}
