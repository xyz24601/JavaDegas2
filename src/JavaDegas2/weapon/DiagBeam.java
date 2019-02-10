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
