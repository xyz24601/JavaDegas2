package JavaDegas2.enemy;

import java.awt.*;
import JavaDegas2.*;

// class to handle boss components
public class Boss1G implements StatConst
{
  protected boolean alive;
  protected boolean intro;
  protected Boss1Core b1core;
  protected Boss1Gate b1gate[];
  protected Boss1Side1 b1s1[];
  protected Boss1Side2 b1s2[];
  protected Boss1Back b1back;
  protected Boss1Turret1 b1tur1[];
  private double speed, aspeed;
  private Coord coord;
  private int mCount;
  private int cgate;  // currently enabled gate
  
  public Boss1G()
  {
    b1core = new Boss1Core();
    b1gate = new Boss1Gate[B1T_NUM];
    for (int i = 0; i < b1gate.length; i++)
    	b1gate[i] = new Boss1Gate();
    
    b1s1 = new Boss1Side1[2];
    for (int i = 0; i < b1s1.length; i++)
      b1s1[i] = new Boss1Side1();
    b1s1[0].setTop(true);  // one is for top
    b1s1[1].setTop(false);  // one is for bottom
    
    b1s2 = new Boss1Side2[4];
    for (int i = 0; i < b1s2.length; i++)
      b1s2[i] = new Boss1Side2();
    b1s2[0].setTop(true);
    b1s2[1].setTop(false);
    b1s2[2].setTop(true);
    b1s2[3].setTop(false);
      
    b1back = new Boss1Back();
    b1tur1 = new Boss1Turret1[6];
    for (int i = 0; i < b1tur1.length; i++)
      b1tur1[i] = new Boss1Turret1();
    
    alive = false;
    intro = true;
    coord = new Coord();  // dummy
    mCount = B1_HMOVE;
  }

  public boolean reset(double iSp)
  {
    if (alive)
      return(false);
    
    speed = iSp;
    aspeed = 0.0;
    cgate = B1T_NUM - 1;
    b1core.reset(F_WIDTH + B1C_WIDTH + 200, (G_HEIGHT - B1C_HEIGHT) / 2);
    for (int i = 0; i < b1gate.length; i++)
      b1gate[i].reset(b1core.getX() - (B1T_SPACE * (i + 1)), b1core.getY());
    b1s1[0].reset(b1core.getX() - B1S1_WIDTH + B1C_WIDTH, b1core.getCenterY() - (B1C_HEIGHT / 2) - B1S1_HEIGHT);
    b1s1[1].reset(b1core.getX() - B1S1_WIDTH + B1C_WIDTH, b1core.getCenterY() + (B1C_HEIGHT / 2));
    
    b1s2[0].reset(b1core.getX() - B1S2_WIDTH + B1C_WIDTH, b1core.getCenterY() - (B1C_HEIGHT / 2) - B1S2_HEIGHT - 50);
    b1s2[1].reset(b1core.getX() - B1S2_WIDTH + B1C_WIDTH, b1core.getCenterY() + (B1C_HEIGHT / 2) + 50);
    b1s2[2].reset(b1core.getX() - B1S2_WIDTH + B1C_WIDTH, b1core.getCenterY() - (B1C_HEIGHT / 2) - B1S2_HEIGHT - 100);
    b1s2[3].reset(b1core.getX() - B1S2_WIDTH + B1C_WIDTH, b1core.getCenterY() + (B1C_HEIGHT / 2) + 100);
    b1back.reset(b1core.getX() + B1C_WIDTH, b1core.getCenterY() - (B1B_HEIGHT / 2));

    // attach turrets to side bars
    b1tur1[0].reset(b1s1[0].getX() - B1R1_WIDTH, b1s1[0].getCenterY() - (B1R1_HEIGHT / 2) + 7);
    b1tur1[1].reset(b1s1[1].getX() - B1R1_WIDTH, b1s1[1].getCenterY() - (B1R1_HEIGHT / 2) - 7);
    b1tur1[2].reset(b1s2[2].getX() - B1R1_WIDTH, b1s2[2].getCenterY() - (B1R1_HEIGHT / 2) + 7);
    b1tur1[3].reset(b1s2[3].getX() - B1R1_WIDTH, b1s2[3].getCenterY() - (B1R1_HEIGHT / 2) - 7);
    b1tur1[4].reset(b1s2[0].getX() - B1R1_WIDTH, b1s2[0].getCenterY() - (B1R1_HEIGHT / 2) + 7);
    b1tur1[5].reset(b1s2[1].getX() - B1R1_WIDTH, b1s2[1].getCenterY() - (B1R1_HEIGHT / 2) - 7);
    
    // make 2 turrets burst
    b1tur1[4].setFirework(true);
    b1tur1[5].setFirework(true);
    
    alive = intro = true;
    return(alive);
  }
  
  public void killThem()
  {
    alive = false;
  }
  
  // return false when destroyed
  public boolean move(Ship ship, EFireG efg)
  {
    if (alive)
    {
      if (intro)
      {  // make boss appear from right
        double cx = b1core.getX();
        if (cx <= F_WIDTH - (B1C_WIDTH * 2))
        {
          intro = false;
          
          // make these ready, so they'll block bullets
          // keep others not-ready, so unable to be destroyed
          for (int i = 0; i < b1s1.length; i++)
            b1s1[i].setReady();
          for (int i = 0; i < b1s2.length; i++)
            b1s2[i].setReady();
          b1back.setReady();
          for (int i = 0; i < b1tur1.length; i++)
            b1tur1[i].setReady();
        }
        else
        {  // make all pieces appear from right
          b1core.move(ship, efg, coord, cx - speed, b1core.getY());
          for (int i = 0; i < b1gate.length; i++)
        	  b1gate[i].move(ship,  efg,  coord, b1gate[i].getX() - speed, b1gate[i].getY());
          for (int i = 0; i < b1s1.length; i++)
            b1s1[i].move(ship, efg, coord, b1s1[i].getX() - speed, b1s1[i].getY());
          for (int i = 0; i < b1s2.length; i++)
            b1s2[i].move(ship, efg, coord, b1s2[i].getX() - speed, b1s2[i].getY());
          b1back.move(ship, efg, coord, b1back.getX() - speed, b1back.getY());
          for (int i = 0; i < b1tur1.length; i++)
            b1tur1[i].move(ship, efg, coord, b1tur1[i].getX() - speed, b1tur1[i].getY());
        }
      }
      else
      {  // move up and down
        if (mCount >= B1_HMOVE)
        {
          if (ship.getCenterY() > b1core.getCenterY())
            speed = Math.abs(speed);  // ship is below
          else
            speed = -Math.abs(speed);  // ship is above
          mCount = 0;
        }
        
        double cy = b1core.getY();
        if (((cy > 100) && (speed < 0)) ||
            ((cy < 500) && (speed > 0)))
        {
          cy += speed;
          aspeed = speed;
        }
        else
        {  aspeed = 0.0;  }

        if (!b1core.isDying())
        {
          // only left most gate can take hits at first
          // keep others protected from laser
          if (cgate >= 0)
            b1gate[cgate].setReady();
 
          for (int i = 0; i < b1s1.length; i++)
            b1s1[i].move(ship, efg, coord, b1s1[i].getX(), b1s1[i].getY() + aspeed);
          for (int i = 0; i < b1s2.length; i++)
            b1s2[i].move(ship, efg, coord, b1s2[i].getX(), b1s2[i].getY() + aspeed);
          b1back.move(ship, efg, coord, b1back.getX(), b1back.getY() + aspeed);
          for (int i = 0; i < b1tur1.length; i++)
            b1tur1[i].move(ship, efg, coord, b1tur1[i].getX(), b1tur1[i].getY() + aspeed);

          for (int i = 0; i < b1gate.length; i++)
          {
            boolean ret = b1gate[i].move(ship,  efg,  coord,  b1gate[i].getX(), cy);
            if ((i == cgate) && !ret)
              cgate--;  // gate destroyed, enable next one
            else if ((i == 0) && !ret)
            {
              b1core.setReady();  // all gates destroyed, enable core
              for (int j = 0; j < b1tur1.length; j++)
                b1tur1[j].fdcut();  // increase firing rate
            }
          }
        }
        else
        {
          // core 1 is in dying state
          // make everything else dying state
          for (int i = 0; i < b1s1.length; i++)
            b1s1[i].setDying(true);
          for (int i = 0; i < b1s2.length; i++)
            b1s2[i].setDying(true);
          b1back.setDying(true);
          for (int i = 0; i < b1tur1.length; i++)
            b1tur1[i].setDying(true);
        }
        alive = b1core.move(ship, efg, coord, b1core.getX(), cy);
        mCount++;
      }
    }
    return(alive);
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      b1core.paint(g, c);
      for (int i = 0; i < b1gate.length; i++)
      	b1gate[i].paint(g,  c);
      for (int i = 0; i < b1s1.length; i++)
        b1s1[i].paint(g, c);
      for (int i = 0; i < b1s2.length; i++)
        b1s2[i].paint(g, c);
      b1back.paint(g, c);
      for (int i = 0; i < b1tur1.length; i++)
        b1tur1[i].paint(g, c);
    }
  }
}
