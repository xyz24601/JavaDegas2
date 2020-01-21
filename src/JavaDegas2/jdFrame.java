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

package JavaDegas2;

import java.awt.*;

import JavaDegas2.enemy.*;
import JavaDegas2.star.*;

// game panel class
// this class does most of the heavy lifting
public class jdFrame implements StatConst
{
  private Frame f;
  private Graphics g;
  private Image osI;  // off screen Image
  private Graphics osG;  // off screen graphics
  
  private StarG starg;  // stars
  private EFireG efg;  // enemy fires
  private AllEnemy ae;  // all enemy
  private Title ttl;  // title screen
  private GameOver ovr;  // game over screen
  private Stage01 stg01;  // stage 01
  
  private Ship ship;
  private PwrCapG pCap;  // power capsules
  
  public jdFrame()
  {
    f = new Frame("JavaDegas 2.0");
    f.setSize(F_WIDTH, F_HEIGHT);
    f.addWindowListener(new ListenerWinExit());
    f.addKeyListener(new ListenerKey());
    f.addMouseListener(new ListenerMouse());
    f.setVisible(true);
    
    g = f.getGraphics();
    
    // set up for double buffer
    osI = f.createImage(F_WIDTH, F_HEIGHT);
    osG = osI.getGraphics();
    
    starg = new StarG();
    
    efg = new EFireG();
    ae = new AllEnemy();
    stg01 = new Stage01(ae);
    
    jdImages.init(f);
    jdAudio.init();
    
    pCap = new PwrCapG();
    ship = new Ship(pCap);
    StatVar.scores = new Scores();
    ttl = new Title();
    ovr = new GameOver();
    
    starg.goSouth();
  }
  
  public void reset()
  {
    efg.reset();
    
    ae.killThem();
    pCap.deleteAll();

    stg01.reset();
//    ae.reset();
    
    ship.restart();

    StatVar.scores.reset();
    StatVar.level = 1;
    jdAudio.normalBGM();
    jdAudio.playBGM();  
  }
  
  public void update()
  {
    starg.move();

    if (StatVar.titleScreen)
    {
       // add some kind of demo 
    }
    else if (StatVar.gameOver)
    {
      ovr.move();
    }
    else if (StatVar.startGame)
    {
      reset();
      StatVar.startGame = false;
    }
    else
    {
      starg.goWest();
    
      if (!stg01.move(ship, efg, pCap))
      {
        StatVar.level++;
        stg01.reset();
      }
      
// line      
//      if (!ae.e1g1w1.move(ship, efg, pCap)) ae.e1g1w1.reset();
// huge line
//      if (!ae.e1g1w2.move(ship, efg, pCap)) ae.e1g1w2.reset();

// sine wave full screen
//      if (!ae.e2g1w1.move(ship, efg, pCap))
//        ae.e2g1w1.reset();  

// crowd sine wave
//      if (!ae.e2g1w2.move(ship, efg, pCap)) ae.e2g1w2.reset();

// sine wave back-and-forth
//      if (!ae.e2g1w3.move(ship, efg, pCap)) ae.e2g1w3.reset();

// simple sine wave
//    if (!ae.e2g2w1.move(ship, efg, pCap)) ae.e2g2w1.reset();

// bouncer
//    if (!ae.e3g1w1.move(ship, efg, pCap)) ae.e3g1w1.reset();

// crash
//      if (!ae.e4g1w1.move(ship, efg, pCap)) ae.e4g1w1.reset();

// straight march
//    if (!ae.e5g1w1.move(ship, efg, pCap)) ae.e5g1w1.reset();

// walker
//    if (!ae.e6g1w1.move(ship, efg, pCap)) ae.e6g1w1.reset();

// zabu front
//    if (!ae.e7g1w1.move(ship, efg, pCap))
//      ae.e7g1w1.reset();

// zabu back
//    if (!ae.e7g1w2.move(ship, efg, pCap))
//      ae.e7g1w2.reset();

// zabu front and back
//    if (!ae.e7g1w3.move(ship, efg, pCap)) ae.e7g1w3.reset();

// zabu all over
//    if (!ae.e7g2w1.move(ship, efg, pCap)) ae.e7g2w1.reset();

// boss
//      if (!ae.b1w.move(ship, efg))
//        ae.b1w.reset();
    
      if (!ship.move())
      {
        starg.goSouth();
        StatVar.gameOver = true;
        StatVar.goDelay = 0;
        
        // game over - stop the music
        jdAudio.stopBGM();        
      }
    
      pCap.move(ship, StatVar.scores);
      efg.move(ship);
    
      StatVar.scores.setHigh();
    }
  }
  
  public void paint()
  {
    osG.setColor(Color.black);
    osG.fillRect(0,  0,  F_WIDTH,  F_HEIGHT);
  
    if (StatVar.titleScreen)
    {
      ttl.paint(osG, f);
    }
    else if (StatVar.gameOver)
    {
      ovr.paint(osG, f);
    }
    
    // draw stars on top of title, but behind characters
    starg.paint(osG);

    if (!StatVar.titleScreen && !StatVar.gameOver)
    {  // game in progress
      efg.paint(osG);
    
      stg01.paint(osG, f);
//      ae.paint(osG, f);
      
      pCap.paint(osG, f);
      ship.paint(osG, f);
    }
    
    StatVar.scores.paint(osG);
    
    g.drawImage(osI, 0, 0, f);
  }

}
