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
        if (StatVar.titleScreen)
        {  // start the game
          StatVar.titleScreen = false;
          StatVar.startGame = true;
        }
        StatVar.keySPACE = true;
        break;
      case KeyEvent.VK_M:
      case KeyEvent.VK_N:
        StatVar.powerUp = true;
        break;
        
      // these are for debugging
      case KeyEvent.VK_ADD:
        if (StatVar.level < MAX_LEVEL)
          StatVar.level++;
        break;
      case KeyEvent.VK_SUBTRACT:
        if (StatVar.level != 1)
          StatVar.level--;
        break;
        
      case KeyEvent.VK_CONTROL:
        StatVar.keyCTL = true;
        break;
        
      // these are secret keys
      case KeyEvent.VK_W:
//        if (StatVar.keyCTL)
          if (StatVar.wType == WT_LASER)
            StatVar.wType = WT_BEAM;
          else
            StatVar.wType++;
        break;
      case KeyEvent.VK_Q:
//        if (StatVar.keyCTL)
          StatVar.addOption = true;
        break;
      case KeyEvent.VK_S:
//        if (StatVar.keyCTL)
          StatVar.addShield = true;
        break;
      case KeyEvent.VK_A:
//        if (StatVar.keyCTL)
          StatVar.hasBomb = true;
        break;
      case KeyEvent.VK_Z:
//        if (StatVar.keyCTL)
          StatVar.speedUp = true;
        break;
      case KeyEvent.VK_X:
//        if (StatVar.keyCTL)
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
