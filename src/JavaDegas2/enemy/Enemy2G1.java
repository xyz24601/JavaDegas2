package JavaDegas2.enemy;

import JavaDegas2.*;

// class to handle group of enemy 2
// follow the leader kind of group
public class Enemy2G1 extends FollowGroup implements StatConst
{
  public Enemy2G1()
  {
    super();
    enemy = new Enemy2[E2G1_MAXNUM];
    for (int i = 0; i < enemy.length; i++)
      enemy[i] = new Enemy2();
  }
  
  // mc - movement center
  // mh - movement height
  // ir - initial y coordinate in radian
  // sx - x speed
  // sry - y radian speed
  // fl - firing location, top, bottom, both
  // p - true for power enemy
  // r - true for reverse
  // eNum - number of enemy in this group
  // iDelay - delay between enemy
  public boolean reset(int mc, int mh, double ir, double sx, double sry,
                       int fl, boolean p, boolean r, int eNum, int iDelay)
  {
    if (alive)
      return(false);
    
    super.reset(iDelay);
    mEnum = (eNum > E2G1_MAXNUM ? E2G1_MAXNUM : eNum);

    for (int i = 0; i < mEnum; i++)
    {
      ((Enemy2) enemy[i]).reset(mc, mh, ir, sx, sry, fl, p, r);
    }
    
    return(true);
  }
  
  // return false when all enemy2s are gone
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      addEnemy(E2G1_MAXNUM);
      
      alive = false;  // for now
      for (int i = 0; i < cEnum; i++)
      {
        if (((Enemy2) enemy[i]).move(ship, efg, coord) ||
             (cEnum < mEnum))
        {
          // as long as at lease one enemy is alive
          // set whole group to be alive
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
