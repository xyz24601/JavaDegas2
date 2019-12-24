package JavaDegas2.enemy;

import java.awt.*;
import JavaDegas2.*;

public class Boss1Gate extends BossCommon implements StatConst
{
  public Boss1Gate()
  {
    super(B1T_WIDTH, B1T_HEIGHT);
  }
  
  public void reset(double xLoc, double yLoc)
  {
	  super.reset(xLoc,  yLoc);
  }

  public boolean move(Ship ship, EFireG efireg, Coord coord,
		              double xLoc, double yLoc)
  {
	  if (alive)
	  {
	    if (ready)
	    {
		    if (checkHit(ship, hCount, B1T_MHIT, hDelay, B1T_MHDELAY, B1T_SCORE, coord))
		    {
    		  jdAudio.play(jdAudio.sEnemyHit);
    		  efireg.firework(this, ship, B1T_BNUM);
  	  	  alive = false;
		    }
	    }
		  
	    setXY(xLoc, yLoc);	
	    hDelay[0]++;
	  }
	  return(alive);
  }
  
  
  public void paint(Graphics g, Component c)
  {
	  if (alive)
    {
      if (dying)
        g.drawImage(jdImages.bBarGrey, (int) getX(), (int) getY(), c); 
      else if (ready)
        g.drawImage(jdImages.bBarBlue, (int) getX(), (int) getY(), c); 
      else
        g.drawImage(jdImages.bBarRed, (int) getX(), (int) getY(), c); 
    }
  }

}
