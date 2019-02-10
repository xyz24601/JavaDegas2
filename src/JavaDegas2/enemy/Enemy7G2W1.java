package JavaDegas2.enemy;

import JavaDegas2.*;

// appears randomly
public class Enemy7G2W1 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 5;
  
  private double speed;
  private int gDelay;  // delay between groups
  private int mGroup;  // number of groups in wave
  private int fd;  // fire delay
  
  public Enemy7G2W1()
  {
    super();
    eg = new Enemy7G2[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy7G2();
  }
  
  public void reset()
  {
    super.reset();
    speed = E7G1W1_SPEED + ((double) StatVar.level / 2);
    gDelay = E7G2W1_GDELAY - (StatVar.level * 5);
    mGroup = E7G2W1_NUM_GROUP + StatVar.level;
    eNum = E7G2W1_NUM_ENEMY + StatVar.level;
    fd = E7G2_FDELAY - (StatVar.level * 50);
    ((Enemy7G2) eg[0]).reset(speed, fd, false, eNum);
  }

  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      for (int i = 0; i < eg.length; i++)
      {
        if (((Enemy7G2) eg[i]).move(ship, efg, pCap))
          bo = true;  // at least one is alive
      }
      
      cgDelay++;  // increase delay between groups
      if ((cgDelay > gDelay) && (cGroup < mGroup))
      {  // send in another group
        for (int i = 0; i < eg.length; i++)
        {  // every 3rd group is power
          if (((Enemy7G2) eg[i]).reset(speed, fd, (cGroup % 3) == 0 ? true : false, eNum))
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
