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

package JavaDegas2.enemy;

public interface EnemyConst
{
  // constant for enemy fire
  public static final int EF_WIDTH = 5;
  public static final int EF_HEIGHT = 5;
  public static final int EF_MAX_NUM = 150;
  public static final double EF_SPEED = 2.5;
  
//- - - - - - - - - - - - - - - - - - - - - - -  

  // constant for enemy 1
  public static final int E1_WIDTH = 40;
  public static final int E1_HEIGHT = 40;
  public static final int E1_DYING = 5;
  public static final int E1_SCORE = 10;
  public static final int E1G1_MAXNUM = 20;
  
  // constant for enemy 1 G1 wave 1
  public static final int E1G1W1_NUM_GROUP = 3;
  public static final int E1G1W1_NUM_ENEMY = 5;
  public static final int E1G1W1_XCOUNT = 40;
  public static final int E1G1W1_YCOUNT = 10;
  public static final double E1G1W1_SPEED = 4.0;
  public static final int E1G1W1_EDELAY = 12;
  public static final int E1G1W1_GDELAY = 120;
  
  // constant for enemy 1 G1 wave 2
  public static final int E1G1W2_NUM_GROUP = 2;
  public static final int E1G1W2_NUM_ENEMY = 15;
  public static final int E1G1W2_XCOUNT = 80;
  public static final int E1G1W2_YCOUNT = 20;
  public static final double E1G1W2_SPEED = 4.0;
  public static final int E1G1W2_EDELAY = 12;
  public static final int E1G1W2_GDELAY = 250;

//- - - - - - - - - - - - - - - - - - - - - - -  
  
  // constant for enemy 2
  public static final int E2_WIDTH = 40;
  public static final int E2_HEIGHT = 40;
  public static final int E2_DYING = 5;
  public static final int E2_SCORE = 10;
  public static final int E2_FIRE_TOP = 1;
  public static final int E2_FIRE_BOTTOM = 2;
  public static final int E2_FIRE_BOTH = 3;
  public static final int E2G1_MAXNUM = 15;
  public static final int E2G2_MAXNUM = 5;
  
  // constant for enemy 2 G1 wave 1
  public static final double E2G1W1_XSPEED = 3.0;
  public static final double E2G1W1_RSPEED = 0.03;
  public static final int E2G1W1_EDELAY = 8;
  public static final int E2G1W1_GDELAY = 250;
  public static final int E2G1W1_NUM_GROUP = 2;
  public static final int E2G1W1_NUM_ENEMY = 10;
  
  // constant for enemy 2 G1 wave 2
  public static final double E2G1W2_XSPEED = 2.0;
  public static final double E2G1W2_RSPEED = 0.12;
  public static final int E2G1W2_EDELAY = 8;
  public static final int E2G1W2_GDELAY = 250;
  public static final int E2G1W2_NUM_GROUP = 2;
  public static final int E2G1W2_NUM_ENEMY = 10;

  // constant for enemy 2 G1 wave 3
  public static final double E2G1W3_XSPEED = 8.0;
  public static final double E2G1W3_RSPEED = 0.06;
  public static final int E2G1W3_EDELAY = 100;
  public static final int E2G1W3_NUM_ENEMY = 10;

  // constant for enemy 2 G2 wave 1
  public static final double E2G2W1_XSPEED = 4.0;
  public static final double E2G2W1_RSPEED = 0.12;
  public static final int E2G2W1_GDELAY = 70;
  public static final int E2G2W1_NUM_GROUP = 4;
  public static final int E2G2W1_NUM_ENEMY = 1;

//- - - - - - - - - - - - - - - - - - - - - - -  
  
  // constant for enemy 3
  public static final int E3_WIDTH = 40;
  public static final int E3_HEIGHT = 40;
  public static final int E3_DYING = 5;
  public static final int E3_SCORE = 25;
  public static final int E3G1_MAXNUM = 10;

  // constant for enemy3 G1 wave 1
  public static final double E3G1W1_XSPEED = 5.0;
  public static final double E3G1W1_RSPEED = 0.06;
  public static final int E3G1W1_EDELAY = 100;
  public static final int E3G1W1_NUM_ENEMY = 5;
  public static final int E3G1W1_MAX_BOUNCE = 7;

//- - - - - - - - - - - - - - - - - - - - - - -  

  // constant for enemy 4
  public static final int E4_WIDTH = 50;
  public static final int E4_HEIGHT = 30;
  public static final int E4_DYING = 5;
  public static final int E4_SCORE = 10;
  public static final int E4G1_MAXNUM = 7;
  
  // constant for enemy 4 G1 wave 1
  public static final double E4G1W1_SPEED = 4.0;
  public static final int E4G1W1_GDELAY = 70;
  public static final int E4G1W1_NUM_GROUP = 4;
  public static final int E4G1W1_NUM_ENEMY = 1;

//- - - - - - - - - - - - - - - - - - - - - - -  

  // constant for enemy 5
  public static final int E5_WIDTH = 40;
  public static final int E5_HEIGHT = 40;
  public static final int E5_DYING = 5;
  public static final int E5_SCORE = 10;
  public static final int E5G1_MAXNUM = 20;

  // constant for enemy 5 G1 wave 1
  public static final double E5G1W1_SPEED = 8.0;
  public static final int E5G1W1_EDELAY = 5;
  public static final int E5G1W1_GDELAY = 75;

//- - - - - - - - - - - - - - - - - - - - - - -  

  // constant for enemy 6
  public static final int E6_WIDTH = 40;
  public static final int E6_HEIGHT = 50;
  public static final int E6_DYING = 5;
  public static final int E6_SCORE = 10;
  public static final int E6_STOP = 7;
  public static final int E6G1_MAXNUM = 10;

  // constant for enemy 5 G1 wave 1
  public static final double E6G1W1_WSPEED = 2.0;
  public static final int E6G1W1_EDELAY = 150;
  public static final int E6G1W1_NUM_ENEMY = 3;
  public static final int E6G1W1_NUM_STOP = 10;
  public static final int E6G1W1_WALK = 50;

//- - - - - - - - - - - - - - - - - - - - - - -  

  // constant for enemy 7
  public static final int E7_WIDTH = 40;
  public static final int E7_HEIGHT = 40;
  public static final int E7_DYING = 5;
  public static final int E7_SCORE = 10;
  // delay before becoming ready
  public static final int E7_RDELAY = 50;
  public static final int E7G1_MAXNUM = 5;
  public static final int E7G1_FDELAY = 200;
  
  public static final double E7G1W1_SPEED = 3.0;
  public static final int E7G1W1_GDELAY = 70;
  public static final int E7G1W1_NUM_GROUP = 4;
  public static final int E7G1W1_NUM_ENEMY = 2;
  
  public static final int E7G2_MAXNUM = 10;
  public static final int E7G2_FDELAY = 250;
  public static final int E7G2W1_GDELAY = 70;
  public static final int E7G2W1_NUM_GROUP = 5;
  public static final int E7G2W1_NUM_ENEMY = 2;

//- - - - - - - - - - - - - - - - - - - - - - -  

  // constant for boss 1 core
  public static final int B1C_WIDTH = 40;
  public static final int B1C_HEIGHT = 40;
  public static final int B1C_SCORE = 500;
  public static final double B1C_MHIT = 15.0;
  public static final int B1C_MHDELAY = 10;
  public static final int B1_HMOVE = 20; 
  public static final int B1_DYING = 50;
  
  // constant for boss 1 gate
  public static final int B1T_NUM = 7;
  public static final int B1T_WIDTH = 10;
  public static final int B1T_HEIGHT = 40;
  public static final int B1T_SCORE = 100;
  public static final int B1T_SPACE = 15;
  public static final double B1T_MHIT = 10.0;
  public static final int B1T_MHDELAY = 10;
  public static final int B1T_BNUM = 30;
  
  // constant for boss 1 side 1
  public static final int B1S1_WIDTH = 175;
  public static final int B1S1_HEIGHT = 30;
  
  // constant for boss 1 side 2
  public static final int B1S2_WIDTH = 275;
  public static final int B1S2_HEIGHT = 20;
  
  // constant for boss 1 back
  public static final int B1B_WIDTH = 25;
  public static final int B1B_HEIGHT = 260;
  
  // constant for boss 1 turret
  public static final int B1R1_WIDTH = 5;
  public static final int B1R1_HEIGHT = 5;
  public static final int B1R1_FDELAY = 40;
  public static final int B1R1_BNUM = 25;
  
  public static final double B1W_SPEED = 2.0;

}