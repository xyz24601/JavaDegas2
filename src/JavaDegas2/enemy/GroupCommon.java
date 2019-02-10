package JavaDegas2.enemy;

import java.awt.Component;
import java.awt.Graphics;

import JavaDegas2.Coord;

public class GroupCommon
{
  protected boolean alive;
  protected Coord coord;  // return killed location
  protected EnemyCommon enemy[];

  public GroupCommon()
  {
    coord = new Coord();
  }
  
  public void killThem()
  {  alive = false; }
  
  public void reset()
  {  alive = true;  }

  public void paint(Graphics g, Component c)
  {
    if (alive)
    {
      for (int i = 0; i < enemy.length; i++)
        enemy[i].paint(g, c);
    }
  }

}
