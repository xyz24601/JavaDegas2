package JavaDegas2.enemy;

public abstract class BossCommon extends EnemyCommon
{
  protected double hCount[];  // hit count
  protected int hDelay[];  // add delay between hits
  
  public BossCommon(int w, int h)
  {
    super(w, h);
    hCount = new double[1];
    hDelay = new int[1];
  }
  
  public void reset(double xl, double yl)
  {
    super.reset();
    setXY(xl, yl);
    hCount[0] = 0.0;
    hDelay[0] = 100;
  }
  
  public void setReady()
  { ready = true; }
  
}
