package JavaDegas2;

import java.io.*;
import javax.sound.sampled.*;

public class audioTools
{

  public static Clip getAudio(String s, String d)
  {
    AudioInputStream ais;
    Clip c = null;
    try
    {
      ais = AudioSystem.getAudioInputStream(new File(d + s).getAbsoluteFile());
      c = AudioSystem.getClip();
      c.open(ais);
    }
    catch (Exception e)
    {
      System.err.println(e.getMessage());
    }
    return(c);
  }
}
