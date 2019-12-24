package JavaDegas2;

import java.awt.*;

import JavaDegas2.enemy.*;
import JavaDegas2.star.*;

// game panel class
public class jdFrame implements StatConst
{
  private Frame f;
  private Graphics g;
  private Image osI;  // off screen Image
  private Graphics osG;  // off screen graphics
  
  private StarG starg;  // stars
  private EFireG efg;  // enemy fires
  private AllEnemy ae;  // all enemy
  
  private Ship ship;
  private PwrCapG pCap;  // power capsules
//  private Scores scores;
  
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
//    StatVar.efg = new EFireG();
    ae = new AllEnemy();
    
    jdImages.init(f);
    jdAudio.init();
    
    pCap = new PwrCapG();
    ship = new Ship(pCap);
//    scores = new Scores();
    StatVar.scores = new Scores();
    
    starg.goSouth();

//    jdAudio.mBGM00.loop(Clip.LOOP_CONTINUOUSLY);
    
  }
  
  public void reset()
  {
//    starg.goSouth();
    efg.reset();
    
    ae.killThem();
    pCap.deleteAll();
      
    ae.e1g1w1.reset();
    ae.e1g1w2.reset();
    ae.e2g1w1.reset();
    ae.e2g1w2.reset();
    ae.e2g1w3.reset();
    ae.e2g2w1.reset();
    ae.e3g1w1.reset();
    ae.e4g1w1.reset();
    ae.e5g1w1.reset();
    ae.e6g1w1.reset();
    ae.e7g1w1.reset();
    ae.e7g1w2.reset();
    ae.e7g1w3.reset();
    ae.e7g2w1.reset();
    ae.b1w.reset();
    
    ship.restart();
//    scores.reset();
    StatVar.scores.reset();

  }
  
  public void update()
  {
    starg.move();

    if (StatVar.titleScreen)
    {
      
    }
    else if (StatVar.startGame)
    {
      reset();
      StatVar.startGame = false;
    }
    else
    {
      starg.goWest();
    
//      if (!ae.e1g1w1.move(ship, efg, pCap))
//        ae.e1g1w1.reset();

//      if (!ae.e1g1w2.move(ship, efg, pCap))
//        ae.e1g1w2.reset();

//      if (!ae.e2g1w1.move(ship, efg, pCap))
//        ae.e2g1w1.reset();

//      if (!ae.e2g1w2.move(ship, efg, pCap))
//        ae.e2g1w2.reset();

//      if (!ae.e2g1w3.move(ship, efg, pCap))
//        ae.e2g1w3.reset();

//    if (!ae.e2g2w1.move(ship, efg, pCap))
//      ae.e2g2w1.reset();

//    if (!ae.e3g1w1.move(ship, efg, pCap))
//      ae.e3g1w1.reset();

//      if (!ae.e4g1w1.move(ship, efg, pCap))
//        ae.e4g1w1.reset();

//    if (!ae.e5g1w1.move(ship, efg, pCap))
//      ae.e5g1w1.reset();

//    if (!ae.e6g1w1.move(ship, efg, pCap))
//      ae.e6g1w1.reset();

//    if (!ae.e7g1w1.move(ship, efg, pCap))
//      ae.e7g1w1.reset();

//    if (!ae.e7g1w2.move(ship, efg, pCap))
//      ae.e7g1w2.reset();

//    if (!ae.e7g1w3.move(ship, efg, pCap))
//      ae.e7g1w3.reset();

//    if (!ae.e7g2w1.move(ship, efg, pCap))
//      ae.e7g2w1.reset();

      if (!ae.b1w.move(ship, efg))
        ae.b1w.reset();
    
      if (!ship.move())
      {
        starg.goSouth();
        StatVar.titleScreen = true;
      }
    
//      pCap.move(ship, scores);
      pCap.move(ship, StatVar.scores);
      efg.move(ship);
    
//      scores.setHigh();  // update high score
      StatVar.scores.setHigh();
    }
  }
  
  public void paint()
  {
    osG.setColor(Color.black);
    osG.fillRect(0,  0,  F_WIDTH,  F_HEIGHT);

    // this may need to be removed
//    osG.setColor(Color.white);
//    osG.drawRect(0, 0, F_WIDTH, G_HEIGHT);
    
    // this may need to be removed
//    osG.setColor(Color.gray);
//    osG.drawRect(0, PIP_TOP, F_WIDTH, PIP_HEIGHT);
    
    starg.paint(osG);  // stars are always background
    
    if (!StatVar.titleScreen)
    {
      efg.paint(osG);
    
      ae.paint(osG, f);
      
      pCap.paint(osG, f);
      ship.paint(osG, f);
    }
    
//    scores.paint(osG);
    StatVar.scores.paint(osG);
    
    g.drawImage(osI, 0, 0, f);
  }

}
