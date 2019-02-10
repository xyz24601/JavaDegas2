package JavaDegas2.enemy;

import JavaDegas2.*;

// class to handle wave of enemy2 groups
// 2 groups appear at the top and bottom at once 
public class Enemy2G1W1 extends WaveCommon implements StatConst 
{
  private final int MAX_EG = 6;
  // movement center
  private final int M_CENTER =
      (G_HEIGHT - E2_HEIGHT) / 2 + G_TOP_MERGIN / 2;
  // movement height
  private final int M_HEIGHT =
      G_HEIGHT - G_TOP_MERGIN - E2_HEIGHT;
      
  private double xSpeed;  // enemy x speed
  private double rSpeed;  // enemy radian speed
//  private int eNum;  // number of enemy in group
  private int eDelay;  // delay between enemy
  private int gDelay;  // delay between groups
//  private int cgDelay;  // current delay counter
//  private int cGroup;  // current group
  private int mGroup;  // number of groups in wave

  public Enemy2G1W1()
  {
    super();
    eg = new Enemy2G1[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy2G1();
  }
  
  public void reset()
  {
    super.reset();
//    alive = true;
//    cgDelay = 0;
//    cGroup = 1;
    xSpeed = E2G1W1_XSPEED + ((double) StatVar.level / 2);
    rSpeed = E2G1W1_RSPEED + ((double) StatVar.level / 100);
    eDelay = E2G1W1_EDELAY - (StatVar.level / 2);
    gDelay = E2G1W1_GDELAY - (StatVar.level * 5);
    mGroup = E2G1W1_NUM_GROUP + StatVar.level;
    eNum = E2G1W1_NUM_ENEMY + StatVar.level;
    ((Enemy2G1) eg[0]).reset(M_CENTER, M_HEIGHT, -Math.PI / 2, xSpeed, rSpeed, E2_FIRE_BOTTOM, false, false, eNum, eDelay);
    ((Enemy2G1) eg[1]).reset(M_CENTER, M_HEIGHT, Math.PI / 2, xSpeed, rSpeed, E2_FIRE_TOP, false, false, eNum, eDelay);
  }
  
  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      for (int i = 0; i < MAX_EG; i++)
      {
        if (((Enemy2G1) eg[i]).move(ship, efg, pCap))
          bo = true;  // at lease one is alive
      }
      
      cgDelay++;  // increase delay
      if ((cgDelay > gDelay)  && (cGroup < mGroup))
      {  // send in another wave
        for (int i = 0; i < MAX_EG; i += 2)
        {
          if (((Enemy2G1) eg[i]).reset(M_CENTER, M_HEIGHT, -Math.PI / 2, xSpeed, rSpeed, E2_FIRE_BOTTOM, false, false, eNum, eDelay) &&
              ((Enemy2G1) eg[i + 1]).reset(M_CENTER, M_HEIGHT, Math.PI / 2, xSpeed, rSpeed, E2_FIRE_TOP, false, false, eNum, eDelay))
          {
            cGroup++;
            cgDelay = 0;
            break;
          }
        }
      }
      
      if (!bo && (cGroup >= mGroup))
        alive = false;  // all done
    }
    return(alive);
  }

}
