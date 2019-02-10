package JavaDegas2;

import javax.sound.sampled.*;

public class jdAudio implements jdAudioConst
{
  public static Clip vSpeedUp;
  public static Clip vBomb;
  public static Clip vDouble;
  public static Clip vLaser;
  public static Clip vOption;
  public static Clip vShield;
  
  public static Clip sShipHit;
  public static Clip sBeamFire;
  public static Clip sLaserFire;
  public static Clip sGotPower;
  public static Clip sShieldHit;
  
  public static Clip sEnemyHit;
  public static Clip sBossExp;
  public static Clip sMissFire;
  public static Clip sBossHit;
  
  public static Clip mBGM00;
  public static Clip mBGM01;

  public static void init()
  {
    String aDir = System.getProperty("user.dir") + "\\audio\\";

    vSpeedUp = audioTools.getAudio(aSpeedUp, aDir);
    vBomb = audioTools.getAudio(aBomb, aDir);
    vDouble = audioTools.getAudio(aDouble, aDir);
    vLaser = audioTools.getAudio(aLaser, aDir);
    vOption = audioTools.getAudio(aOption, aDir);
    vShield = audioTools.getAudio(aShield, aDir);

    sShipHit = audioTools.getAudio(aShipHit, aDir);
    sBeamFire = audioTools.getAudio(aBeamFire, aDir);
    sLaserFire = audioTools.getAudio(aLaserFire, aDir);
    sGotPower = audioTools.getAudio(aGotPower, aDir);
    sShieldHit = audioTools.getAudio(aShieldHit, aDir);

    sEnemyHit = audioTools.getAudio(aEnemyHit, aDir);
    sBossExp = audioTools.getAudio(aBossExp, aDir);
    sMissFire = audioTools.getAudio(aMissFire, aDir);
    sBossHit = audioTools.getAudio(aBossHit, aDir);
    
    mBGM00 = audioTools.getAudio(bgm00, aDir);
    mBGM01 = audioTools.getAudio(bgm01, aDir);
  }
  
  public static void play(Clip c)
  {
    c.stop();
    c.setFramePosition(0);
    c.start();
  }
}
