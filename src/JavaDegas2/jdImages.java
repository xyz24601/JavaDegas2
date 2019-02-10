package JavaDegas2;

import java.awt.*;
import java.awt.image.*;

public class jdImages implements jdImgConst
{
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
  
  public static void init(Component c)
  {
    String imgDir = System.getProperty("user.dir") + "\\images\\";

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
    
    kab00 = ImgTools.loadBufImage(imgDir + jdImgConst.kab00, c);
    kab01 = ImgTools.loadBufImage(imgDir + jdImgConst.kab01, c);

  }
}
