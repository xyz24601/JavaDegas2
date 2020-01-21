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

// class to hold static variables
public class StatVar implements StatConst
{
  public static boolean keyUP = false;
  public static boolean keyDOWN = false;
  public static boolean keyLEFT = false;
  public static boolean keyRIGHT = false;
  public static boolean keySPACE = false;
  public static boolean keyCTL = false;
  
  public static boolean paused = false;
  
  public static boolean powerUp = false;
  public static boolean addOption = false;
  public static boolean addShield = false;
  public static boolean hasBomb = false;
  public static boolean speedUp = false;
  public static boolean hasShield = false;  
  public static boolean maxOption = false;
  
  public static boolean godMode = false;
  
  public static boolean titleScreen = true;
  public static boolean startGame = false;
  public static boolean gameOver = false;
  
  public static int wType = WT_BEAM;
  public static int level = 1;
  
  // length to display Game Over
  public static int goDelay = 0;
  
  // this needs to be static to keep the high score
  public static Scores scores;
  
  public static int curBGM = 1;
}
