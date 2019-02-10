package JavaDegas2.enemy;

import JavaDegas2.*;

public class Enemy6G1W1 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 2;
  private double wSpeed;  // walk speed 
  private int eDelay;  // delay between enemy
  private int gDelay;  // delay between groups
  private int mStop;  // max stop
  private int dWalk;  // walk delay
  
  public Enemy6G1W1()
  {
    super();
    eg = new Enemy6G1[MAX_EG];
    for (int i = 0; i < eg.length; i++)
      eg[i] = new Enemy6G1();
  }
  
  public void reset()
  {
    super.reset();
    wSpeed = E6G1W1_WSPEED + ((double) StatVar.level / 2);
    eDelay = E6G1W1_EDELAY - (StatVar.level * 5);
    gDelay = eDelay / 2;
    eNum = E6G1W1_NUM_ENEMY + StatVar.level;
    mStop = E6G1W1_NUM_STOP + StatVar.level;
    dWalk = E6G1W1_WALK - (StatVar.level * 4);
    ((Enemy6G1) eg[0]).reset(wSpeed, mStop, dWalk, false, eNum, eDelay);
    ((Enemy6G1) eg[1]).reset(wSpeed, mStop, dWalk, true, eNum, eDelay);
  }
  
  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      if (((Enemy6G1) eg[0]).move(ship, efg, pCap))
        bo = true;  // at least one is alive
      
      cgDelay++;  // increase delay
      if (cgDelay > gDelay)
      {  // add delay, so enemy appears alternatively
        if (((Enemy6G1) eg[1]).move(ship, efg, pCap))
            bo = true;  // at lease one is alive
      }
      
      if (!bo)
        alive = false;
    }
    return(alive);
  }
  
}
