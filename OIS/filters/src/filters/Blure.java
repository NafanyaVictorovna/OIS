/*
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
public class Blure {
   
    private BufferedImage source;
    private BufferedImage result;
    private int width;
    private int height;

    public Blure(int w, int h){
        if(w<0) throw new IllegalArgumentException("width must be nonnegative");
        if(h<0) throw new IllegalArgumentException("height must be nonnegative");
        width = w;
        height =h;
        result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }
    
    public void setSource(BufferedImage source) {
        this.source = source;
    }

    public BufferedImage getResult() {
        return result;
    }
  
    public void glass(){
       for(int x=0; x<width; x++){
           for(int y=0; y<height; y++){
               int newX = (width + x + random(-7, 7)) % width;
               int newY = (height + y + random(-7, 7)) % height;
               Color scolor = get(newX,newY);
               set(x,y,scolor);
            }
        }   
    }   

    private Color get(int c, int r) { 
        return new Color(source.getRGB(c,r));  
    }

    private void set(int c, int r, Color color) {
        result.setRGB(c,r,color.getRGB()); 
    }
    
    private int random(int a, int b){
        return a+(int)(Math.random()*(b-a+2));
    }  
}

