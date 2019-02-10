package JavaDegas2.enemy;

import JavaDegas2.*;

// follow the leader kind of group
// but delay between each enemy is large
public class Enemy6G1 extends FollowGroup implements StatConst
{
  public Enemy6G1()
  {
    super();
    enemy = new Enemy6[E6G1_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy6();
  }
  
  // sp - walking speed
  // ms - max number of stop
  // mw - max walk delay
  // t - true to appear at top
  // eNum - number of enemy in this group
  // iDelay - delay between enemy
  public boolean reset(double sp, int ms, int mw, boolean t, int eNum, int iDelay)
  {
    if (alive)
      return(false);
    
    super.reset(iDelay);
    mEnum = (eNum > E6G1_MAXNUM ? E6G1_MAXNUM : eNum);
    
    for (int i = 0; i < mEnum; i++)
    {
      ((Enemy6) enemy[i]).reset(sp, ms, mw, t);
    }
    
    return(true);
  }
  
  // return false when all enemy are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      addEnemy(E6G1_MAXNUM);
      
      alive = false;  // for now
      for (int i = 0; i < cEnum; i++)
      {
        if (((Enemy6) enemy[i]).move(ship, efg, coord) ||
            (cEnum < mEnum))
        {
          if (!alive)  // at least one live enemy
            alive = true;
        }
      }
    }
    return(alive);
  }

}
