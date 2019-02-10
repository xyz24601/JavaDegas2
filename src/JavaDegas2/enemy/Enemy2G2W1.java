package JavaDegas2.enemy;

import JavaDegas2.*;

// class to handle wave of enemy2 groups
// the all appear vertically
public class Enemy2G2W1 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 5;
  
  private double xSpeed;  // enemy x speed
  private double rSpeed;  // enemy radian speed
  private int gDelay;  // delay between groups
  private int mGroup;  // number of groups in wave
  
  public Enemy2G2W1()
  {
    super();
    eg = new Enemy2G2[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy2G2();
  }
  
  public void reset()
  {
    super.reset();
    xSpeed = E2G2W1_XSPEED + ((double) StatVar.level / 2);
    rSpeed = E2G2W1_RSPEED + ((double) StatVar.level / 100);
    gDelay = E2G2W1_GDELAY - (StatVar.level * 5);
    mGroup = E2G2W1_NUM_GROUP + StatVar.level;
    eNum = E2G2W1_NUM_ENEMY + StatVar.level;
    ((Enemy2G2) eg[0]).reset(xSpeed, rSpeed, E2_FIRE_BOTH, false, eNum);
  }
  
  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      for (int i = 0; i < eg.length; i++)
      {
        if (((Enemy2G2) eg[i]).move(ship, efg, pCap))
          bo = true;  // at lease one is alive
      }
      
      cgDelay++;  // increase delay between groups
      if ((cgDelay > gDelay) && (cGroup < mGroup))
      {  // send in another group
        for (int i = 0; i < eg.length; i++)
        {  // every 3rd group is power
          if (((Enemy2G2) eg[i]).reset(xSpeed, rSpeed, E2_FIRE_BOTH, (cGroup % 3) == 0 ? true : false, eNum))
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
