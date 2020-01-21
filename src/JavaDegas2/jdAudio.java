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
  
  public static void playBGM()
  {
    if (1 == StatVar.curBGM)
    {
      mBGM00.loop(Clip.LOOP_CONTINUOUSLY);
    }
    else
    {
      mBGM01.loop(Clip.LOOP_CONTINUOUSLY);
    }
  }
  
  public static void stopBGM()
  {
    if (1 == StatVar.curBGM)
    {
      mBGM00.stop();
      mBGM00.setFramePosition(0);
    }
    else
    {
      mBGM01.stop();
      mBGM01.setFramePosition(0);
    }
  }
  
  public static void pauseBGM()
  {
    if (1 == StatVar.curBGM)
      mBGM00.stop();
    else
      mBGM01.stop();
  }
  
  public static void bossBGM()
  {
    StatVar.curBGM = 2;
  }
  
  public static void normalBGM()
  {
    StatVar.curBGM = 1;
  }

}
