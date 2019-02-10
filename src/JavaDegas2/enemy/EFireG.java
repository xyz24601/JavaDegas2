package JavaDegas2.enemy;

import java.awt.*;

import JavaDegas2.*;

// class to handle group of enemy fires
public class EFireG implements StatConst
{
  private EFire efire[];
  private int fNum;  // number of fire on screen
  private double speed;
  
  public EFireG()
  {
    efire = new EFire[EF_MAX_NUM];
    for (int i = 0; i < EF_MAX_NUM; i++)
      efire[i] = new EFire();
  }
  
  public void reset()
  {
    speed = EF_SPEED;
    for (int i = 0; i < EF_MAX_NUM; i++)
      efire[i].setAlive(false);
  }
  
  public void move(Ship ship)
  {
    for (int i = 0; i < EF_MAX_NUM; i++)
    {
      if (efire[i].move(ship))
        fNum--;  // fire is out of screen or hit ship
    }
  }
  
  // fire at the ship
  // e - enemy
  public void fire(IFO e, IFO ship)
  {
    if (ship.isAlive() && (fNum < EF_MAX_NUM))
    {
      for (int i = 0; i < EF_MAX_NUM; i++)
      {
        if (efire[i].fire(e, ship, speed + StatVar.level))
        {
          fNum++;
          break;
        }
      }
    }
  }
  
  // fire like fireworks 
  // e - enemy
  // num - number of fires
  public void firework(IFO e, IFO ship, int num)
  {
    double rad = 0.0;
    int i = 0;
    int j = 0;
    while (ship.isAlive() && (fNum < EF_MAX_NUM) &&
           (i < EF_MAX_NUM) && (j < num))
    {
      if (efire[i].fireRadian(e, rad, speed + StatVar.level))
      {
        fNum++;  j++;  // valid fire
        rad += Math.PI * 2 / num;  // change angle
      }
      i++;  // keep trying
    }
  }
  
  public void paint(Graphics g)
  {
    for (int i = 0; i < EF_MAX_NUM; i++)
      efire[i].paint(g);
  }

}
