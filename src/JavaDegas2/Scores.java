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
