package JavaDegas2;

import java.awt.*;

import JavaDegas2.enemy.*;

public class Stage01
{
  private AllEnemy ae;
  private boolean alive;
  private boolean w1, w2, w3, w4, w5, w6, w7;
  
  public Stage01(AllEnemy iae)
  {
    ae = iae;
  }

  public void reset()
  {
    alive = true;
    w1 = true;
    w2 = w3 = w4 = w5 = w6 = w7 = false;
//  w1 = w2 = w3 = w4 = w5 = w6 = w7 = false;
//  w6 = true;
    
    ae.reset();
  }
  
  public boolean move(Ship ship, EFireG efg, PwrCapG pCap)
  {
    boolean r1, r2, r3;
    
    if (alive)
    {
      if (w1)  // line attack
      {
        if (!ae.e1g1w1.move(ship, efg, pCap))
        {  // wave 1 complete
          w1 = false;
          w2 = true;
          ae.e1g1w1.reset();  // send it in one more time
        }
      }
      
      if (w2)  // line and sine wave attack
      {
        r1 = ae.e1g1w1.move(ship, efg, pCap);  // line
        r2 = ae.e2g2w1.move(ship, efg, pCap);  // sine
        if (!r1 && !r2)
        {  // wave 2 complete
          w2 = false;
          w3 = true;
        } 
      }
      
      if (w3)  // huge line and crowd sine wave
      {
        r1 = ae.e1g1w2.move(ship, efg, pCap);  // line
        r2 = ae.e2g1w2.move(ship, efg, pCap);  // sine
        if (!r1 && !r2)
        {
          w3 = false;
          w4 = true;
        }
      }
      
      if (w4)  // walker and crash
      {
        r1 = ae.e6g1w1.move(ship, efg, pCap);  // walker
        r2 = ae.e4g1w1.move(ship, efg, pCap);  // crash
        
        // walker remains, send in more crash
        if (r1 && !r2)
        {  ae.e4g1w1.reset(); }
        
        if (!r1 && !r2)
        {
          w4 = false;
          w5 = true;
        }
      }
      
      if (w5)  // bouncer, zabu, sine bounce
      {
        r1 = ae.e3g1w1.move(ship, efg, pCap);  // bouncer
        r2 = ae.e7g1w3.move(ship, efg, pCap);  // zabu
        r3 = ae.e2g1w3.move(ship, efg, pCap);  // sine

        // keep sending until bounder is all gone        
        if (r1 && !r2)
        {  ae.e7g1w3.reset(); }
        if (r1 && !r3)
        {  ae.e2g1w3.reset(); }
        
        if (!r1 && !r2 && !r3)
        {
          w5 = false;
          w6 = true;
        }
      }
      
      if (w6)  // straight to left
      {
        if (!ae.e5g1w1.move(ship, efg, pCap))
        {
          w6 = false;
          w7 = true;
        }
      }
        
      if (w7)
      {
        r1 = ae.b1w.move(ship, efg);
        r2 = ae.e7g2w1.move(ship, efg, pCap);
        
        if (r1 && !r2)  // keep sending zabu
        {  ae.e7g2w1.reset(); }
  
        if (!r1)  // boss destroyed
        {
          ae.e7g2w1.killThem();
          w7 = false;
          alive = false;      
        }
      }
    
    }
    return(alive);
  }
  
  public void paint(Graphics g, Component c)
  {
    if (alive)
      ae.paint(g, c);
  }
}