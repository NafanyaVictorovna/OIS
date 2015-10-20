/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author NafanyaVictorovna
 */
public class Brightness {
   private BufferedImage source; 
   private BufferedImage result;
   private int width;
   private int height;

   public Brightness(int w, int h){ 
      if(w<0) throw new IllegalArgumentException("width must be nonnegative");
      if(h<0) throw new IllegalArgumentException("height must be nonnegative");
      width = w;
      height = h;
      result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
   }

   public BufferedImage getResult(){
       return result;
   }
      
   public void setSource(BufferedImage image){
       source = image;
   }
      
   public void bright(){
       for(int x=0; x<width; x++){
           for(int y=0; y<height; y++){
               Color scolor = get(x,y);
               set(x,y,scolor.brighter());
           }
       }   
   }   

    private Color get(int c, int r) { 
        return new Color(source.getRGB(c,r));  
    }

    private void set(int c, int r, Color color) {
        result.setRGB(c,r,color.getRGB()); 
    }
}
