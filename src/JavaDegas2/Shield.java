package JavaDegas2;

import java.awt.*;

// class to handle shield
public class Shield extends IFO implements StatConst
{
  private int cHit;  // current hit count
  private int imgC;
  
  public Shield()
  {
    super(SH_WIDTH, SH_HEIGHT);
    alive = ready = dying = false;
  }
  
  public void add(IFO ship)
  {
    alive = true;
    dying = false;
    cHit = 0;
    StatVar.hasShield = true;
    
    // place it in front of ship
    setXY(ship.getX() + S_WIDTH,
          ship.getY() - (SH_HEIGHT - S_HEIGHT) / 2);
  }
  
  public void reset()
  {
    alive = false;
  }
  
  public void hit()
  {
    if (cHit > SH_MAX_HIT)
    {
      alive = false;
      StatVar.hasShield = false;
    }
    else if (cHit == SH_MAX_HIT)  // one more hit
      dying = true;
    cHit++;
  }
  
  public void move(IFO ship)
  {
    if (alive)
    {
      setXY(ship.getX() + S_WIDTH,
            ship.getY() - (SH_HEIGHT - S_HEIGHT) / 2);
    
      if (imgC < (jdImages.shield.length * 2 - 1))
        imgC++;
      else
        imgC = 0;
    }
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      if (dying)
        g.drawImage(jdImages.shieldX[imgC / 2], (int) getX(), (int) getY(), c);
      else
        g.drawImage(jdImages.shield[imgC / 2], (int) getX(), (int) getY(), c);
    }
  }
}
