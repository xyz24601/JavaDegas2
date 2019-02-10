package JavaDegas2.enemy;

import JavaDegas2.*;

// appears vertically lined up
public class Enemy7G1W2 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 5;
  
  private double speed;
  private int gDelay;  // delay between groups
  private int mGroup;  // number of groups in wave
  private int xLoc = F_WIDTH / 5;
  private int fd;  // fire delay
  
  public Enemy7G1W2()
  {
    super();
    eg = new Enemy7G1[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy7G1();
  }
  
  public void reset()
  {
    super.reset();
    speed = E7G1W1_SPEED + ((double) StatVar.level / 2);
    gDelay = E7G1W1_GDELAY - (StatVar.level * 5);
    mGroup = E7G1W1_NUM_GROUP + StatVar.level;
    eNum = E7G1W1_NUM_ENEMY + StatVar.level;
    fd = E7G1_FDELAY - (StatVar.level * 25);
    ((Enemy7G1) eg[0]).reset(speed, xLoc, fd, false, eNum);
  }

  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      for (int i = 0; i < eg.length; i++)
      {
        if (((Enemy7G1) eg[i]).move(ship, efg, pCap))
          bo = true;  // at least one is alive
      }
      
      cgDelay++;  // increase delay between groups
      if ((cgDelay > gDelay) && (cGroup < mGroup))
      {  // send in another group
        for (int i = 0; i < eg.length; i++)
        {  // every 3rd group is power
          if (((Enemy7G1) eg[i]).reset(speed, xLoc, fd, (cGroup % 3) == 0 ? true : false, eNum))
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
