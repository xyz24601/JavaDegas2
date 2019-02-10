package JavaDegas2;

// super class to hold x & y coordinate in double
public class Coord
{
  private double x;
  private double y;

  public Coord()
  {
    x = 0.0;
    y = 0.0;
  }
  
  public Coord(double ix, double iy)
  {
    x = ix;
    y = iy;
  }
  
  public double getX() { return(x); }
  
  public double getY() { return(y); }
  
  public void setX(double ix)
  {
    x = ix;
  }
  
  public void setY(double iy)
  {
    y = iy;
  }
  
  public void setXY(double ix, double iy)
  {
    x = ix;
    y = iy;
  }
  
  public void setXY(int ix, int iy)
  {
    x = (double) ix;
    y = (double) iy;
  }
}



