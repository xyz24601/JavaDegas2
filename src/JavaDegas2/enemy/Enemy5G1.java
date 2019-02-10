package JavaDegas2.enemy;

import JavaDegas2.*;

// move straight to the left
public class Enemy5G1 extends FollowGroup implements StatConst
{
  public Enemy5G1()
  {
    super();
    enemy = new Enemy5[E5G1_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy5();
    mEnum = E5G1_MAXNUM;
  }

  // yl - starting y location
  // sp - speed
  // id - delay between enemy
  // return true if initialized
  public boolean reset(int yl, double sp, int id)
  {
    if (alive)
      return(false);
    
    super.reset(id);
    for (int i = 0; i < mEnum; i++)
    {
      ((Enemy5) enemy[i]).reset(yl, sp);
    }
    return(true);
  }
  
  // return false when all enemy5s are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      addEnemy(E5G1_MAXNUM);
      
      alive = false;  // for now
      for (int i = 0; i < cEnum; i++)
      {
        if (((Enemy5) enemy[i]).move(ship, efg, coord) ||
            (cEnum < mEnum))
        {  // as long as one alive, the group is alive
          if (!alive)
            alive = true;
        }
      }
      // last enemy killed
      placePower(pCap);
    }
    return(alive);
  }
}
