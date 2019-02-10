package JavaDegas2;

import java.awt.*;

import JavaDegas2.weapon.Weapons;

public class Ship extends IFO implements StatConst
{
  private double speed;
  private Weapons weapons;
  private Shield shield;
  private PwrCapG pCap;
  private PwrInd pInd;
  private Coord mvTracker[];  // track ship positions
  private int sIndex;  // ship's index for mvTracker
  private int oIndex[];  // option's indexes for mvTracker
  private int cOption; // current num of option
  private Option option[];
  private int sdDelay;  // current deal delay
  private int cShip;  // current ship
  private int imgC;
  private int moveDir;
  private boolean blinker;

  public Ship(PwrCapG ipCap)
  {
    super(S_WIDTH, S_HEIGHT);
    cShip = 1;
    
    weapons = new Weapons();
    shield = new Shield();
    pCap = ipCap;
    pInd = new PwrInd();
    
    mvTracker = new Coord[(MAX_OPTION + 1) * OPT_DISTANCE];
    for (int i = 0; i < mvTracker.length; i++)
      mvTracker[i] = new Coord();
    oIndex = new int[MAX_OPTION];
    option = new Option[MAX_OPTION];
    for (int i = 0; i < option.length; i++)
      option[i] = new Option();
    
    reset();
  }
  
  // restart to restart a game
  public void restart()
  {
    cShip = 1;
    reset();
    pInd.zero();  // zero out power indicator
  }
  
  // reset to start with new ship
  public void reset()
  {
    super.reset();
    sdDelay = 0;
    setXY(S_INITX, S_INITY);
    speed = S_ISPEED;
    
    weapons.reset();
    shield.reset();
    pInd.reset();
    imgC = moveDir = 0;
    
    // reset mvTracker and indexes
    for (int i = 0; i < mvTracker.length; i++)
      mvTracker[i].setXY(S_INITX, S_INITY);
    sIndex = 0;
    cOption = 0;

    for (int i = 0; i < oIndex.length; i++)
    {
      oIndex[i] = mvTracker.length - ((i + 1) * OPT_DISTANCE) - 1;
      option[i].reset();
    }
    StatVar.maxOption = false;
  }
  
  // return false when all ships are destroyed
  public boolean move()
  {
    if (alive)
    {
      if (!ready)
        readyDelay(S_READY);
      
      if (dying)
      {
        dyingDelay(S_DYING);
        shield.reset();
      }
      else
      {
        moveShip();
        powerUp();
        shield.move(this);
        
        incSpeed();
        addOption();
        addShield();
      }
    }
    else
    {
      if (sdDelay < StatConst.S_NEXT)
        sdDelay++;
      else if (cShip < StatConst.S_SPARE)
      {
        cShip++;
        reset();
      }
      else
        return(false);  // all ships are destroyed
    }
    
    if (imgC < (jdImages.shipSide.length * 2 - 1))
      imgC++;
    else
      imgC = 0;
    
    for (int i = 0; i < MAX_OPTION; i++)
      option[i].move(mvTracker[oIndex[i]].getX(), mvTracker[oIndex[i]].getY());
    weapons.move(this);
    return(true);
  }
  
  private void powerUp()
  {
    if (StatVar.powerUp)
    {
      switch (pInd.getCurrentLoc())
      {
        case PI_SPEED_UP:
          StatVar.speedUp = true;
          pInd.zero();
          break;
        case PI_BOMB:
          if (!StatVar.hasBomb) // no bomb yet
          {
            jdAudio.play(jdAudio.vBomb);
            StatVar.hasBomb = true;
            pInd.zero();
          }  // otherwise, do nothing
          break;
        case PI_DOUBLE:
          if (StatVar.wType != WT_DOUBLE)
          {
            jdAudio.play(jdAudio.vDouble);
            StatVar.wType = WT_DOUBLE;
            pInd.zero();
          }
          break;
        case PI_LASER:
          if (StatVar.wType != WT_LASER)
          {
            jdAudio.play(jdAudio.vLaser);
            StatVar.wType = WT_LASER;
            pInd.zero();
          }
          break;
        case PI_OPTION:
          if (cOption < MAX_OPTION)
          {
            StatVar.addOption = true;
            pInd.zero();
          }
          break;
        case PI_SHIELD:
          if (!shield.alive)
          {
            StatVar.addShield = true;
            pInd.zero();
          }
          break;
      }
      StatVar.powerUp = false;
    }
  }

  private void moveShip()
  {
    if (StatVar.keyDOWN || StatVar.keyUP ||
        StatVar.keyLEFT || StatVar.keyRIGHT)
    {
      double tx = getX();
      double ty = getY();
      
      if ((StatVar.keyDOWN) && (ty < G_HEIGHT - S_HEIGHT - 10))
      {
        ty += speed;
        if (0 < moveDir)
          moveDir = 0;
        else if (-15 < moveDir)
          moveDir--;
      }
      if ((StatVar.keyUP) && (ty > G_TOP_MERGIN))
      {
        ty -= speed;
        if (0 > moveDir)
          moveDir = 0;
        else if (15 > moveDir)
          moveDir++;
      }
      if ((StatVar.keyLEFT) && (tx > G_LEFT_MERGIN))
      {
        tx -= speed;
      }
      if ((StatVar.keyRIGHT) && (tx < F_WIDTH * 3 / 4))
      {
        tx += speed;
      }

      setXY(tx, ty);
    
      // update mvTracker and indexes
      if (sIndex < (mvTracker.length - 1))
        sIndex++;
      else
        sIndex = 0;
      mvTracker[sIndex].setXY(tx, ty);
      for (int i = 0; i < MAX_OPTION; i++)
      {
        if (oIndex[i] < (mvTracker.length - 1))
          oIndex[i]++;
        else
          oIndex[i] = 0;
      }
    }

    // no up or down key pressed
    // slowly level the ship
    if ((!StatVar.keyUP) && (!StatVar.keyDOWN))
    {
      if (0 != moveDir)
      {
        if (0 < moveDir)
          moveDir--;
        else
          moveDir++;
      }
    }

  }
  
  // speed up
  public void incSpeed()
  {
    if (alive && StatVar.speedUp)
    {
      jdAudio.play(jdAudio.vSpeedUp);
      speed += S_SPEEDUP;  
    }
    StatVar.speedUp = false;
  }
  
  // option
  public void addOption()
  {
    if (alive && StatVar.addOption)
    {
      if (cOption < MAX_OPTION)
      {
        jdAudio.play(jdAudio.vOption);
        option[cOption].add(mvTracker[cOption].getX(), mvTracker[cOption].getY());
        cOption++;
        
        if (MAX_OPTION == cOption)
          StatVar.maxOption = true;
      }
    }
    StatVar.addOption = false;
  }
  
  // shield
  public void addShield()
  {
    if (alive && StatVar.addShield)
    {
      jdAudio.play(jdAudio.vShield);
      shield.add(this);
    }
    StatVar.addShield = false;
  }
  
  // ifo - reference to enemy
  // return true if ifo is destroyed
  public boolean checkStatus(IFO ifo)
  {
    return(checkWeapon(ifo) || checkCrash(ifo) || checkShield(ifo));
  }

  // return true if weapon hit ifo
  public boolean checkWeapon(IFO ifo)
  {
    if (weapons.checkHit(ifo))
      return(true);
    
    for (int i = 0; i < cOption; i++)
    {
      if (option[i].getWeapons().checkHit(ifo))
        return(true);
    }
    
    return false;
  }
  
  // return true if ifo crashed into ship
  public boolean checkCrash(IFO ifo)
  {
    if (alive && ready && !dying &&
        area.intersects(ifo.area))
    {
      if (!StatVar.godMode)
      {
        dying = true;
        jdAudio.play(jdAudio.sShipHit);
        for (int i = 0; i < cOption; i++)
        {
          pCap.place(option[i].getX(), option[i].getY(), true);
          option[i].delete();
        }
      }
      return(true);
    }
    return(false);
  }
  
  // return true if ifo hits shield
  public boolean checkShield(IFO ifo)
  {
    if (shield.alive && shield.area.intersects(ifo.area))
    {
      shield.hit();
      jdAudio.play(jdAudio.sShieldHit);
      return(true);
    }
    return(false);
  }
  
  // return true if pc is picked up
  public boolean checkPower(PwrCap pc)
  {
    if (alive && !dying && area.intersects(pc.area))
    {
      if (pc.isOption())
        StatVar.addOption = true;
//        addOption();
      else
      {
        jdAudio.play(jdAudio.sGotPower);
        pInd.pickedPower();
      }
      return(true);
    }
    return(false);
  }
  
  public void paint(Graphics g, Component c)
  {
    for (int i = 0; i < option.length; i++)
      option[i].paint(g, c);

    if (alive)
    {
      if (!ready || StatVar.godMode)
        blinker = true;
      else
        blinker = false;
     
      if (dying)
        g.drawImage(jdImages.shipBlow[imgC / 2], (int) getX(), (int) getY(), c);
      else if (blinker && (imgC % 2) == 0)
      {}
      else
      {
        if (10 < moveDir)
          g.drawImage(jdImages.shipBot[imgC / 2], (int) getX(), (int) getY(), c);
        else if (5 < moveDir)
          g.drawImage(jdImages.shipABot[imgC / 2], (int) getX(), (int) getY(), c);
        else if (-10 > moveDir)
          g.drawImage(jdImages.shipTop[imgC / 2], (int) getX(), (int) getY(), c);
        else if (-5 > moveDir)
          g.drawImage(jdImages.shipATop[imgC / 2], (int) getX(), (int) getY(), c);
        else
          g.drawImage(jdImages.shipSide[imgC / 2], (int) getX(), (int) getY(), c);
      }
      
      shield.paint(g, c);
    }
    weapons.paint(g, c);

    pInd.paint(g, c);
  }

}
