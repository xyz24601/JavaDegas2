package JavaDegas2.enemy;

import JavaDegas2.*;

public class Enemy4G1W1 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 5;

  private double speed;  // base speed
  private int gDelay;  // delay between groups
  private int mGroup;  // number of groups in wave
  
  public Enemy4G1W1()
  {
    super();
    eg = new Enemy4G1[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy4G1();
  }
  
  public void reset()
  {
    super.reset();
    speed = E4G1W1_SPEED + ((double) StatVar.level / 2);
    gDelay = E4G1W1_GDELAY - (StatVar.level * 5);
    mGroup = E4G1W1_NUM_GROUP + StatVar.level;
    eNum = E4G1W1_NUM_ENEMY + StatVar.level;
    ((Enemy4G1) eg[0]).reset(speed, false, eNum);
  }
  
  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      for (int i = 0; i < eg.length; i++)
      {
        if (((Enemy4G1) eg[i]).move(ship, efg, pCap))
          bo = true; // at lease one is alive
      }
      
      cgDelay++;  // increase delay between groups
      if ((cgDelay > gDelay) && (cGroup < mGroup))
      {  // send in another group
        for (int i = 0; i < eg.length; i++)
        {  // every 3rd group is power
          if (((Enemy4G1) eg[i]).reset(speed, (cGroup % 3) == 0 ? true : false, eNum))
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
