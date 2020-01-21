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

import java.awt.event.*;

// class to handle keyboard input
public class ListenerKey extends KeyAdapter implements StatConst
{
  public void keyPressed(KeyEvent e)
  {
    int kc = e.getKeyCode();
    e.consume();
    
    switch (kc)
    {
      case KeyEvent.VK_UP:
      case KeyEvent.VK_K:
        StatVar.keyUP = true;
        break;
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_J:
        StatVar.keyDOWN = true;
        break;
      case KeyEvent.VK_LEFT:
      case KeyEvent.VK_H:
        StatVar.keyLEFT = true;
        break;
      case KeyEvent.VK_RIGHT:
      case KeyEvent.VK_L:
        StatVar.keyRIGHT = true;
        break;
      case KeyEvent.VK_SPACE:
        if (StatVar.titleScreen ||
            (StatVar.gameOver && StatVar.goDelay > StatConst.GO_DELAY))
        {  // start the game
          StatVar.titleScreen = false;
          StatVar.gameOver = false;
          StatVar.startGame = true;
        }
        else
        {
          StatVar.keySPACE = true;
        }
        break;
      case KeyEvent.VK_M:
      case KeyEvent.VK_N:
        StatVar.powerUp = true;
        break;
        
      // these are for debugging
      case KeyEvent.VK_ADD:
        if (StatVar.keyCTL)
        {
          if (StatVar.level < MAX_LEVEL)
            StatVar.level++;
        }
        break;
      case KeyEvent.VK_SUBTRACT:
        if (StatVar.keyCTL)
        {
          if (StatVar.level != 1)
            StatVar.level--;
        }
        break;
        
      case KeyEvent.VK_CONTROL:
        StatVar.keyCTL = true;
        break;
        
      // these are secret keys
      case KeyEvent.VK_W:
        if (StatVar.keyCTL)
        {
          if (StatVar.wType == WT_LASER)
            StatVar.wType = WT_BEAM;
          else
            StatVar.wType++;
        }
        break;
      case KeyEvent.VK_Q:
        if (StatVar.keyCTL)
          StatVar.addOption = true;
        break;
      case KeyEvent.VK_S:
        if (StatVar.keyCTL)
          StatVar.addShield = true;
        break;
      case KeyEvent.VK_A:
        if (StatVar.keyCTL)
          StatVar.hasBomb = true;
        break;
      case KeyEvent.VK_Z:
        if (StatVar.keyCTL)
          StatVar.speedUp = true;
        break;
      case KeyEvent.VK_X:
        if (StatVar.keyCTL)
          StatVar.godMode = !StatVar.godMode;
        break;
    }
  }
  
  public void keyReleased(KeyEvent e)
  {
    int kc = e.getKeyCode();
    e.consume();
    
    switch (kc)
    {
      case KeyEvent.VK_UP:
      case KeyEvent.VK_K:
        StatVar.keyUP = false;
        break;
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_J:
        StatVar.keyDOWN = false;
        break;
      case KeyEvent.VK_LEFT:
      case KeyEvent.VK_H:
        StatVar.keyLEFT = false;
        break;
      case KeyEvent.VK_RIGHT:
      case KeyEvent.VK_L:
        StatVar.keyRIGHT = false;
        break;
      case KeyEvent.VK_SPACE:
        StatVar.keySPACE = false;
        break;
      case KeyEvent.VK_CONTROL:
        StatVar.keyCTL = false;
        break;
    }
    
  }
  
}
