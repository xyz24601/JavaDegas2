package JavaDegas2.enemy;

import JavaDegas2.*;

// appears all together lined up vertically
public class Enemy4G1 extends GroupCommon implements StatConst
{
  private final int CENTER =
      (G_HEIGHT - E4_HEIGHT) / 2 + G_TOP_MERGIN / 2;
  private boolean power;
  
  public Enemy4G1()
  {
    super();
    enemy = new Enemy4[E4G1_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy4();
  }
  
  // sp - base speed
  // p - true for power enemy
  // eNum - number of enemy in this group
  public boolean reset(double sp, boolean p, int eNum)
  {
    if (alive)
      return(false);
    
    super.reset();
    power = p;
    
    switch (eNum)
    {
      case 5:
      case 4: init5(sp, p);
              break;
      case 3: init1(sp, p);
              init2(sp, p);
              break;
      default: init2(sp, p);
    }
    return(true);    
  }
  
  // initialize center enemy
  void init1(double sp, boolean p)
  {
    ((Enemy4) enemy[0]).reset(CENTER, sp, p);
  }

  // initialize 2 enemy group
  void init2(double sp, boolean p)
  {
    ((Enemy4) enemy[1]).reset(CENTER - E4_HEIGHT * 2, sp, p);
    ((Enemy4) enemy[2]).reset(CENTER + E4_HEIGHT * 2, sp, p);
  }
  
  // initialize 5 enemy group
  void init5(double sp, boolean p)
  {
    init1(sp, p);
    init2(sp, p);
    ((Enemy4) enemy[1]).reset(CENTER - E4_HEIGHT * 4, sp, p);
    ((Enemy4) enemy[2]).reset(CENTER + E4_HEIGHT * 4, sp, p);
  }
  
  // return false when all ships are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      alive = false;  // for now
      for (int i = 0; i < enemy.length; i++)
      {
        if (((Enemy4) enemy[i]).move(ship, efg, coord))
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
