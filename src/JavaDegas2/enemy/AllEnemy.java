/*
    JavaDegas2 v1.0 --- Space Shooting Game Classic
    Copyright (C) 2020  Shinji Umeki (shinji@umeki.org)
    
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
    
    See the file, COPYING, for more details.
*/

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
  
  public void reset()
  {
    e1g1w1.reset();
    e1g1w2.reset();
    e2g1w1.reset();
    e2g1w2.reset();
    e2g1w3.reset();
    e2g2w1.reset();
    e3g1w1.reset();
    e4g1w1.reset();
    e5g1w1.reset();
    e6g1w1.reset();
    e7g1w1.reset();
    e7g1w2.reset();
    e7g1w3.reset();
    e7g2w1.reset();
    b1w.reset();
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
