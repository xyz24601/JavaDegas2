package JavaDegas2.enemy;

import JavaDegas2.*;

// class to handle wave of enemy2 groups
// each enemy appear taking turn and remains on screen
public class Enemy2G1W3 extends WaveCommon implements StatConst
{
  private final int MAX_EG = 2;
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
  
  public Enemy2G1W3()
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
    xSpeed = E2G1W3_XSPEED + ((double) StatVar.level / 2);
    rSpeed = E2G1W3_RSPEED + ((double) StatVar.level / 100);
    eDelay = E2G1W3_EDELAY - (StatVar.level / 2);
    gDelay = eDelay / 2;
    eNum = E2G1W3_NUM_ENEMY;
    ((Enemy2G1) eg[0]).reset(M_CENTER, M_HEIGHT, -Math.PI * 3 / 4, xSpeed, rSpeed, E2_FIRE_BOTH, false, true, eNum, eDelay);
    ((Enemy2G1) eg[1]).reset(M_CENTER, M_HEIGHT, Math.PI / 4, xSpeed, rSpeed, E2_FIRE_BOTH, false, true, eNum, eDelay);
  }

  // return true if any enemy is alive
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    if (alive)
    {
      boolean bo = false;  // for now
      if (((Enemy2G1) eg[0]).move(ship, efg, pCap))
          bo = true;  // at lease one is alive
      
      cgDelay++;  // increase delay
      if (cgDelay > gDelay)
      {  // add delay, so enemy appear alternatively
        if (((Enemy2G1) eg[1]).move(ship, efg, pCap))
          bo = true;  // at lease one is alive
      }
      
      if (!bo)
        alive = false;
    }
    return(alive);
  }

}
