package JavaDegas2.enemy;

import JavaDegas2.*;

// class to handle group of enemy 2
// appears all together lined up vertically
public class Enemy2G2 extends GroupCommon implements StatConst 
{
  // movement center is middle of the screen
  private final int M_CENTER =
      (G_HEIGHT - E2_HEIGHT) / 2 + G_TOP_MERGIN / 2;
  private final int M_HEIGHT = E2_HEIGHT * 4;
  private final double INT_R = Math.PI / 2;
  private boolean power;
  
  public Enemy2G2()
  {
    super();
    enemy = new Enemy2[E2G2_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy2();
  }

  // sx - x speed
  // sry - y radian speed
  // fl - firing location
  // p - true for power enemy
  // eNum - number of enemy in this group
  public boolean reset(double sx, double sry,
                       int fl, boolean p, int eNum)
  {
    if (alive)
      return(false);
    
    super.reset();
    power = p;
    
    switch (eNum)
    {
      case 5:
      case 4:  init5(sx, sry, fl, p);
               break;
      case 3:  init1(sx, sry, fl, p);
               init2(sx, sry, fl, p);
               break;
      default: init2(sx, sry, fl, p);
    }
    
    return(true);
  }

  // initialize center enemy
  void init1(double sx, double sry, int fl, boolean p)
  {
    ((Enemy2) enemy[0]).reset(M_CENTER, M_HEIGHT, INT_R, sx, sry, E2_FIRE_BOTH, p, false);
  }
  
  // initialize 2 enemy group
  void init2(double sx, double sry, int fl, boolean p)
  {
    ((Enemy2) enemy[1]).reset(M_CENTER - E2_HEIGHT * 2, M_HEIGHT, INT_R, sx, sry, E2_FIRE_BOTH, p, false);
    ((Enemy2) enemy[2]).reset(M_CENTER + E2_HEIGHT * 2, M_HEIGHT, INT_R, sx, sry, E2_FIRE_BOTH, p, false);
  }
  
  // initialize 5 enemy group
  void init5(double sx, double sry, int fl, boolean p)
  {
    init1(sx, sry, fl, p);
    init2(sx, sry, fl, p);
    ((Enemy2) enemy[3]).reset(M_CENTER - E2_HEIGHT * 4, M_HEIGHT, INT_R, sx, sry, E2_FIRE_BOTH, p, false);
    ((Enemy2) enemy[4]).reset(M_CENTER + E2_HEIGHT * 4, M_HEIGHT, INT_R, sx, sry, E2_FIRE_BOTH, p, false);
  }
  
  // return false when all ships are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      alive = false;  // for now
      for (int i = 0; i < enemy.length; i++)
      {
        if (((Enemy2) enemy[i]).move(ship, efg, coord))
        {
          if (!alive)
            alive = true;
        }
        else
        {
          if (power && (0.0 != coord.getX()))
          {
            pCap.place(coord.getX(), coord.getY(), false);
          }
          coord.setX(0.0);  // to avoid placing multiple
        }
      }
    }
    return(alive);
  }
  
}
