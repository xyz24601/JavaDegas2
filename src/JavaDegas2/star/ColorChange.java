package JavaDegas2.star;

import java.awt.*;
import JavaDegas2.*;

// class to handle changing colors for the star
public class ColorChange implements StatConst
{
  static public Color gColors[] = new Color[CNG_COLORS];
  
  static
  {
    double d = 0.0;
    for (int i = 0; i < CNG_COLORS; i++, d += (1.0 / CNG_COLORS))
      gColors[i] = Color.getHSBColor((float)d, 1.0F, 1.0F); 
  }

}
