package JavaDegas2;

import java.awt.*;
import JavaDegas2.weapon.*;

// class to handle option
public class Option extends IFO implements StatConst
{
  private Weapons weapons;  // option also fires
  private int imgC;
  
  public Option()
  {
    super(O_WIDTH, O_HEIGHT);
    weapons = new Weapons();
    reset();
  }

  public void move(double dx, double dy)
  {
    if (alive)
    {
      setXY(dx, dy);
      
      if (imgC < (jdImages.option.length * 2 - 1))
        imgC++;
      else
        imgC = 0;
    }

    weapons.move(this);
  }
  
  public void reset()
  {
    alive = false;
    weapons.reset();
  }
  
  public void add(double x, double y)
  {
    alive = true;
    setXY(x, y);
  }
  
  public void delete()
  {
    alive = false;
  }
  
  public Weapons getWeapons()
  {
    return(weapons);
  }
  
  public void paint(Graphics g, Component c)
  {
    weapons.paint(g, c);
    
    if (alive)
    {
      g.drawImage(jdImages.option[imgC / 2], (int) getX(), (int) getY(), c);
    }
  }
}
