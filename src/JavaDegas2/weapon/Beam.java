package JavaDegas2.weapon;

import java.awt.*;
import JavaDegas2.*;

// class to handle a beam
public class Beam extends IFO implements StatConst
{
  public Beam()
  {
    super(BEAM_WIDTH, BEAM_HEIGHT);
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
      jdAudio.play(jdAudio.sBeamFire);
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
      if (tx > F_WIDTH)  // gone out of screen
        reset();
      else
      {
        tx += BEAM_SPEED;  // move to right
        setXY(tx, getY());
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
      g.fillOval((int) getX(), (int) getY(), BEAM_WIDTH / 2, BEAM_HEIGHT);
      g.fillOval((int) getX() + BEAM_WIDTH / 2, (int) getY(), BEAM_WIDTH / 2, BEAM_HEIGHT);
    }
  }
}
