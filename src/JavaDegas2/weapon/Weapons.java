package JavaDegas2.weapon;

import java.awt.*;
import JavaDegas2.*;

// class to handle all weapons for ship and option
public class Weapons implements StatConst
{
  private BeamG beamg;
  private DiagBeamG dbeamg;
  private LaserG laserg;
  private BombG bombg;
  
  public Weapons()
  {  // create all weapons
    beamg = new BeamG();
    dbeamg = new DiagBeamG();
    laserg = new LaserG();
    bombg = new BombG();
  }
  
  public void reset()
  {
    StatVar.wType = WT_BEAM;  // reset to beam
    StatVar.hasBomb = false;
    
    beamg.reset();
    dbeamg.reset();
    laserg.reset();
    bombg.reset();
  }
  
  public void move(IFO ifo)
  {
    beamg.move(ifo);
    dbeamg.move(ifo);
    laserg.move(ifo);
    bombg.move(ifo);
  }
  
  public boolean checkHit(IFO ifo)
  {
    if (beamg.checkHit(ifo) || dbeamg.checkHit(ifo) ||
        laserg.checkHit(ifo) || bombg.checkHit(ifo))
      return(true);
    else
      return(false);
  }

  public void paint(Graphics g, Component c)
  {
    beamg.paint(g);
    dbeamg.paint(g);
    laserg.paint(g);
    bombg.paint(g, c);
  }
}
