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

import JavaDegas2.enemy.EnemyConst;
import JavaDegas2.star.StarConst;
import JavaDegas2.weapon.WeapConst;

// interface with all constants
public interface StatConst extends EnemyConst, StarConst, WeapConst
{
  // frame dimension
  public static final int F_WIDTH = 800;
  public static final int F_HEIGHT = 600;
  
  public static final int MAX_LEVEL = 7;
  
  public static final int GO_DELAY = 50;
  
  // game area
  public static final int G_HEIGHT = F_HEIGHT - 40;
  public static final int G_TOP_MERGIN = 60;
  public static final int G_LEFT_MERGIN = 10;
  public static final int SCORE_LEFT = 50;
  public static final int SCORE_BOTTOM = 50;

  // animation delay
  public static final int M_DELAY = 20;
  public static final double SCROLL_SPEED = 1.0;
  
  // constant for player's ship
  public static final int S_WIDTH = 50;
  public static final int S_HEIGHT = 25;
  public static final int S_INITX = 100;
  public static final int S_INITY = 350;
  public static final double S_ISPEED = 4.0;
  public static final double S_SPEEDUP = 1.0;
  public static final int S_DYING = 20;
  public static final int S_READY = 70;
  public static final int S_NEXT = 30;
  public static final int S_SPARE = 3;  // number of ships
  
  // constant for player's shield
  public static final int SH_WIDTH = 25;
  public static final int SH_HEIGHT = 35;
  public static final int SH_MAX_HIT = 5;
  
  // constant for player's power indicator
  public static final int PI_MAX = 6;
  public static final int PI_SPEED_UP = 1;
  public static final int PI_BOMB = 2;
  public static final int PI_DOUBLE = 3;
  public static final int PI_LASER = 4;
  public static final int PI_OPTION = 5;
  public static final int PI_SHIELD = 6;
  public static final int PIP_HEIGHT = 40;
//  public static final int PIP_WIDTH = (F_WIDTH / PI_MAX);  // 135
  public static final int PIP_WIDTH = 125;
  public static final int PIP_TOP = F_HEIGHT - PIP_HEIGHT;

  
  // constant for options
  public static final int MAX_OPTION = 4;
  public static final int OPT_DISTANCE = 10;
  public static final int O_WIDTH = 30;
  public static final int O_HEIGHT = 25;
  
  // constant for power capsules
  public static final int P_WIDTH = 30;
  public static final int P_HEIGHT = 25;
  public static int P_MAX_CAP = 15;
  public static int P_SCORE = 5;
}
