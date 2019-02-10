package JavaDegas2.enemy;

import JavaDegas2.*;

// class to handle wave of enemy1 groups
// 2 groups appear at the top and bottom at once
public class Enemy1G1W2 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 6;
  private double eSpeed;  // enemy speed
//  private int eNum;  // number of enemy in group
  private int eDelay;  // delay between enemy
  private int gDelay;  // delay between groups
//  private int cgDelay;  // current delay counter
//  private int cGroup;  // current group
  private int mGroup;  // number of groups in wave

  public Enemy1G1W2()
  {
    super();
    eg = new Enemy1G1[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy1G1();
  }
  
  public void reset()
  {
    super.reset();
//    alive = true;
//    cgDelay = 0;
//    cGroup = 1;
    eSpeed = E1G1W2_SPEED + ((double) StatVar.level / 4);
    eDelay = E1G1W2_EDELAY - (StatVar.level / 2);
    gDelay = E1G1W2_GDELAY - (StatVar.level * 2);
    mGroup = E1G1W2_NUM_GROUP + StatVar.level;
    eNum = E1G1W2_NUM_ENEMY + StatVar.level;
    ((Enemy1G1) eg[0]).reset(G_TOP_MERGIN, eSpeed, E1G1W2_XCOUNT, E1G1W2_YCOUNT, eNum, eDelay);
    ((Enemy1G1) eg[1]).reset(G_HEIGHT - E1_HEIGHT, eSpeed, E1G1W2_XCOUNT, E1G1W2_YCOUNT, eNum, eDelay);
  }
  
  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      for (int i = 0; i < MAX_EG; i++)
      {
        if (((Enemy1G1) eg[i]).move(ship, efg, pCap))
          bo = true;  // at lease one is alive
      }
      
      cgDelay++;  // increase delay
      if ((cgDelay > gDelay) && (cGroup < mGroup))
      {  // send in another pair
        for (int i = 0; i < MAX_EG; i += 2)
        {
          if (((Enemy1G1) eg[i]).reset(G_TOP_MERGIN, eSpeed, E1G1W2_XCOUNT, E1G1W2_YCOUNT, eNum, eDelay))
          {
            if (((Enemy1G1) eg[i + 1]).reset(G_HEIGHT - E1_HEIGHT, eSpeed, E1G1W2_XCOUNT, E1G1W2_YCOUNT, eNum, eDelay))
            {
              cGroup++;
              cgDelay = 0;
              break;
            }
            else  // has to appear in a pair
              eg[i].alive = false;
          }
        }
      }
      
      if (!bo && (cGroup >= mGroup))
        alive = false;  // all done
    }
    return(alive);
  }
  
}
