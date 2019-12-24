package JavaDegas2;

import java.awt.*;

// class to keep track of scores
public class Scores implements StatConst
{
  private int highScore;
  private int score;
  private Font f;
  
  public Scores()
  {
    score = highScore = 0;
    f = new Font("Monospaced", Font.BOLD | Font.ITALIC, 16);
  }
  
  public void reset()
  {
    score = 0;
  }
  
  public void setHigh()
  {
    if (score > highScore)
      highScore = score;
  }
  
  public void add(int s)
  {
    score += s;
  }
  
  public void paint(Graphics g)
  {
    g.setFont(f);
    g.setColor(Color.white);
    g.drawString("Score: " + score, SCORE_LEFT, SCORE_BOTTOM);
    g.drawString("High Score: " + highScore, F_WIDTH - 250, SCORE_BOTTOM);
    g.drawString("Level: " + StatVar.level, (F_WIDTH / 2) - 100, SCORE_BOTTOM);
  }
}
