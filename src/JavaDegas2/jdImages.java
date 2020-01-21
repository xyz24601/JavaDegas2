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
import java.awt.image.*;

public class jdImages implements jdImgConst
{
  public static BufferedImage ttl, ttlUp, ttlDown, ttlLeft, ttlRight,
                              ttlFire, ttlPower;
  public static BufferedImage gOver;
  
  public static BufferedImage shipSide[];
  public static BufferedImage shipTop[];
  public static BufferedImage shipATop[];
  public static BufferedImage shipBot[];
  public static BufferedImage shipABot[];
  public static BufferedImage shipBlow[];
  
  public static BufferedImage pInd[];
  public static BufferedImage pIndLit[];
  public static BufferedImage pIndBlank;
  public static BufferedImage pIndBlankLit;
  public static BufferedImage bomb;
  public static BufferedImage shield[];
  public static BufferedImage shieldX[];
  public static BufferedImage option[];
  public static BufferedImage power[];
  public static BufferedImage powerO[];
  
  public static BufferedImage e1[];
  public static BufferedImage e2[];
  public static BufferedImage e2p[];
  public static BufferedImage e3[];
  public static BufferedImage e3p[];
  public static BufferedImage e4[];
  public static BufferedImage e4p[];
  public static BufferedImage e5[];
  public static BufferedImage e6top[];
  public static BufferedImage e6bot[];
  public static BufferedImage e7[];
  public static BufferedImage e7reg;
  public static BufferedImage e7pow;
  
  public static BufferedImage kab00;
  public static BufferedImage kab01;
  
  // boss images
  public static BufferedImage bcBlue, bcRed, bcGrey;
  public static BufferedImage bBarBlue, bBarRed, bBarGrey;
  public static BufferedImage bS1top, bS1topG;
  public static BufferedImage bS1bot, bS1botG;
  public static BufferedImage bS2top, bS2topG;
  public static BufferedImage bS2bot, bS2botG;
  public static BufferedImage b1back, b1backG;
  public static BufferedImage b1tur, b1turG;
  
  public static void init(Component c)
  {
    String imgDir = System.getProperty("user.dir") + "\\images\\";

    ttl = ImgTools.loadBufImage(imgDir + jdImgConst.title, c);
    ttlUp = ImgTools.loadBufImage(imgDir + jdImgConst.titleUp, c);
    ttlDown = ImgTools.loadBufImage(imgDir + jdImgConst.titleDown, c);
    ttlLeft = ImgTools.loadBufImage(imgDir + jdImgConst.titleLeft, c);
    ttlRight = ImgTools.loadBufImage(imgDir + jdImgConst.titleRight, c);
    ttlFire = ImgTools.loadBufImage(imgDir + jdImgConst.titleFire, c);
    ttlPower = ImgTools.loadBufImage(imgDir + jdImgConst.titlePower, c);
    gOver = ImgTools.loadBufImage(imgDir + jdImgConst.gameOver, c);
    
    shipSide = ImgTools.loadMultiImg(sSideImgs, imgDir, c);
    shipTop = ImgTools.loadMultiImg(sTopImgs, imgDir, c);
    shipATop = ImgTools.loadMultiImg(sATopImgs, imgDir, c);
    shipBot = ImgTools.loadMultiImg(sBotImgs, imgDir, c);
    shipABot = ImgTools.loadMultiImg(sABotImgs, imgDir, c);
    shipBlow = ImgTools.loadMultiImg(sBlowImgs, imgDir, c);

    pInd = ImgTools.loadMultiImg(pIndImgs, imgDir, c);
    pIndLit = ImgTools.loadMultiImg(pIndLitImgs, imgDir, c);
    pIndBlank = ImgTools.loadBufImage(imgDir + jdImgConst.pIndBlank, c);
    pIndBlankLit = ImgTools.loadBufImage(imgDir + jdImgConst.pIndBlankLit, c);
    
    bomb = ImgTools.loadBufImage(imgDir + jdImgConst.bomb, c);

    shield = ImgTools.loadMultiImg(shieldImgs, imgDir, c);
    shieldX = ImgTools.loadMultiImg(shieldXImgs, imgDir, c);
    
    option = ImgTools.loadMultiImg(optImgs, imgDir, c);
    power = ImgTools.loadMultiImg(powerImgs, imgDir, c);
    powerO = ImgTools.loadMultiImg(powerOImgs, imgDir, c);
    
    e1 = ImgTools.loadMultiImg(e1images, imgDir, c);
    
    e2 = ImgTools.loadMultiImg(e2images, imgDir, c);
    e2p = ImgTools.loadMultiImg(e2Pimages, imgDir, c);
    
    e3 = ImgTools.loadMultiImg(e3images, imgDir, c);
    e3p = ImgTools.loadMultiImg(e3Pimages, imgDir, c);
        
    e4 = ImgTools.loadMultiImg(e4images, imgDir, c);
    e4p = ImgTools.loadMultiImg(e4Pimages, imgDir, c);

    e5 = ImgTools.loadMultiImg(e5images, imgDir, c);

    e6top = ImgTools.loadMultiImg(e6imgTop, imgDir, c);
    e6bot = ImgTools.loadMultiImg(e6imgBot, imgDir, c);

    e7 = ImgTools.loadMultiImg(e7img, imgDir, c);
    e7reg = ImgTools.loadBufImage(imgDir + e7imgReg, c);
    e7pow = ImgTools.loadBufImage(imgDir + e7imgPow, c);
    
    kab00 = ImgTools.loadBufImage(imgDir + kabImg00, c);
    kab01 = ImgTools.loadBufImage(imgDir + kabImg01, c);

    // load boss images
    bcBlue = ImgTools.loadBufImage(imgDir + bcBlueImg, c);
    bcRed = ImgTools.loadBufImage(imgDir + bcRedImg, c);
    bcGrey = ImgTools.loadBufImage(imgDir + bcGreyImg, c);

    bBarBlue = ImgTools.loadBufImage(imgDir + bBarBlueImg, c);
    bBarRed = ImgTools.loadBufImage(imgDir + bBarRedImg, c);
    bBarGrey = ImgTools.loadBufImage(imgDir + bBarGreyImg, c);

    bS1top = ImgTools.loadBufImage(imgDir + bS1topImg, c);
    bS1bot = ImgTools.loadBufImage(imgDir + bS1botImg, c);
    bS1topG = ImgTools.loadBufImage(imgDir + bS1topGImg, c);
    bS1botG = ImgTools.loadBufImage(imgDir + bS1botGImg, c);

    bS2top = ImgTools.loadBufImage(imgDir + bS2topImg, c);
    bS2bot = ImgTools.loadBufImage(imgDir + bS2botImg, c);
    bS2topG = ImgTools.loadBufImage(imgDir + bS2topGImg, c);
    bS2botG = ImgTools.loadBufImage(imgDir + bS2botGImg, c);

    b1back = ImgTools.loadBufImage(imgDir + b1backImg, c);
    b1backG = ImgTools.loadBufImage(imgDir + b1backGImg, c);
    b1tur = ImgTools.loadBufImage(imgDir + b1turImg, c);
    b1turG = ImgTools.loadBufImage(imgDir + b1turGImg, c);

  }
}
