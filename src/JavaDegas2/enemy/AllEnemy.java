package JavaDegas2.enemy;

import java.awt.*;

// class to handle all enemy
public class AllEnemy
{
  public Enemy1G1W1 e1g1w1;
  public Enemy1G1W2 e1g1w2;
  public Enemy2G1W1 e2g1w1;
  public Enemy2G1W2 e2g1w2;
  public Enemy2G1W3 e2g1w3;
  public Enemy2G2W1 e2g2w1;
  public Enemy3G1W1 e3g1w1;
  public Enemy4G1W1 e4g1w1;
  public Enemy5G1W1 e5g1w1;
  public Enemy6G1W1 e6g1w1;
  public Enemy7G1W1 e7g1w1;
  public Enemy7G1W2 e7g1w2;
  public Enemy7G1W3 e7g1w3;
  public Enemy7G2W1 e7g2w1;
  
  public Boss1W b1w;
  
  public AllEnemy()
  {
    e1g1w1 = new Enemy1G1W1();
    e1g1w2 = new Enemy1G1W2();
    e2g1w1 = new Enemy2G1W1();
    e2g1w2 = new Enemy2G1W2();
    e2g1w3 = new Enemy2G1W3();
    e2g2w1 = new Enemy2G2W1();
    e3g1w1 = new Enemy3G1W1();
    e4g1w1 = new Enemy4G1W1();
    e5g1w1 = new Enemy5G1W1();
    e6g1w1 = new Enemy6G1W1();
    e7g1w1 = new Enemy7G1W1();
    e7g1w2 = new Enemy7G1W2();
    e7g1w3 = new Enemy7G1W3();
    e7g2w1 = new Enemy7G2W1();
    
    b1w = new Boss1W();
  }

  public void killThem()
  {
    e1g1w1.killThem();
    e1g1w2.killThem();
    e2g1w1.killThem();
    e2g1w2.killThem();
    e2g1w3.killThem();
    e2g2w1.killThem();
    e3g1w1.killThem();
    e4g1w1.killThem();
    e5g1w1.killThem();
    e6g1w1.killThem();
    e7g1w1.killThem();
    e7g1w2.killThem();
    e7g1w3.killThem();
    e7g2w1.killThem();
    
    b1w.killThem();
  }
  
  public void paint(Graphics g, Component c)
  {
    e1g1w1.paint(g, c);
    e1g1w2.paint(g, c);
    e2g1w1.paint(g, c);
    e2g1w2.paint(g, c);
    e2g1w3.paint(g, c);
    e2g2w1.paint(g, c);
    e3g1w1.paint(g, c);
    e4g1w1.paint(g, c);
    e5g1w1.paint(g, c);
    e6g1w1.paint(g, c);
    e7g1w1.paint(g, c);
    e7g1w2.paint(g, c);
    e7g1w3.paint(g, c);
    e7g2w1.paint(g, c);
    
    b1w.paint(g, c);
  }
  
}
