package JavaDegas2;

// main class
public class JavaDegas2 implements StatConst
{
  private static jdFrame jdf;
  
  public static void main(String[] args)
  {
    jdf = new jdFrame();

//    jdf.reset();
//    jdf.paint();
    
    while (true)
    {
      try { Thread.sleep(M_DELAY); }
      catch (InterruptedException ie) { }

      if (!StatVar.paused)
        jdf.update();
      
      jdf.paint();
    }
  }
  
}
