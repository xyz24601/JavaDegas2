package JavaDegas2.enemy;

import JavaDegas2.*;

// appears all together lined up vertically
public class Enemy7G2 extends FollowGroup implements StatConst
{
//  private final int M_CENTER =
//      (G_HEIGHT - E2_HEIGHT) / 2 + G_TOP_MERGIN / 2;

  private boolean power;

  public Enemy7G2()
  {
    super();
    enemy = new Enemy7[E7G2_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy7();
  }

  // is - speed
  // fd - fire delay
  // p - true for power enemy
  // eNum - number of enemy in this group
  public boolean reset(double is, int fd, boolean p, int eNum)
  {
    if (alive)
      return(false);
    
    super.reset();
    power = p;
    
    mEnum = (eNum > E7G2_MAXNUM ? E7G2_MAXNUM : eNum);

    for (int i = 0; i < mEnum; i++)
    {  // randomly place enemy
      int ix = (int) (Math.random() * (F_WIDTH - E7_WIDTH));
      int iy = (int) (Math.random() * (F_HEIGHT - E7_HEIGHT * 3)) + E7_HEIGHT;
      ((Enemy7) enemy[i]).reset(ix, iy, is, fd, p);
    }
    
    return(true);
  }
  
  // return false when all ships are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      alive = false;  // for now
      for (int i = 0; i < enemy.length; i++)
      {
        if (((Enemy7) enemy[i]).move(ship, efg, coord))
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
