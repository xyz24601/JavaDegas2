package JavaDegas2.enemy;

import JavaDegas2.*;

// generic class for follow the leader kind of group
public class FollowGroup extends GroupCommon
{
  protected int cEnum;  // current number of enemy
  protected int mEnum;  // current max number of enemy
  protected int cDelay;  // current delay
  protected int eDelay;  // delay between enemy
  
  public FollowGroup()
  {
    super();
  }
  
  // iDelay - delay between enemy
  public void reset(int iDelay)
  {
    super.reset();
    cEnum = 1;
    cDelay = 0;
    eDelay = iDelay;
  }
  
  // eAbsMax - absolute maximum number of enemy
  public void addEnemy(int eAbsMax)
  {
    if (cDelay < eDelay)
      cDelay++;  // add delay between
    else if (cEnum < eAbsMax)
    {
      cEnum++;  // add another enemy
      cDelay = 0;
    }
  }
  
  public void placePower(PwrCapG pCap)
  {
    if (!alive && (0.0 != coord.getX()))
    {  // placer power capsule
      pCap.place(coord.getX(), coord.getY(), false);
    }
  }
}
