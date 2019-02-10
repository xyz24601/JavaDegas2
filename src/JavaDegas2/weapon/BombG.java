package JavaDegas2.weapon;

import java.awt.*;
import JavaDegas2.*;

// class to handle group of bombs
// actually it only deals with ONE bomb now
public class BombG implements StatConst
{
  private Bomb bomb;
  private int bDelay;  // add delay between bombs
  
  public BombG()
  {
    bomb = new Bomb();
    reset();
  }
  
  public void reset()
  {
    bomb.reset();
    bDelay = 100;  // make so it will drop immediately
  }
  
  public void drop(int x, int y)
  {
    bomb.drop(x, y);
  }
  
  public void move(IFO ifo)
  {
    if (ifo.isAlive() && !ifo.isDying() && StatVar.hasBomb)
    {
      bDelay++;
      
      if (StatVar.keySPACE && (bDelay > BOMB_DELAY))
      {  // drop!
        bDelay = 0;
        drop((int) ifo.getCenterX(), (int) ifo.getY() + ifo.getH());
      }
    }
    
    // move bombs no matter what ship's condition
    bomb.move();
  }
  
  // return true if hit enemy
  public boolean checkHit(IFO ifo)
  {
    if (bomb.checkHit(ifo))
      return(true);
    else
      return(false);
  }
  
  public void paint(Graphics g, Component c)
  {
    bomb.paint(g, c);
  }

}
