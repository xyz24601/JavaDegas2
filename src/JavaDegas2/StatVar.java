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
}
