/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aphins;

import java.awt.Color;
import java.awt.image.BufferedImage;
/**
 *
 * @author NafanyaVictorovna
 */
public class FlipY {
    private BufferedImage source;
    private BufferedImage result;
    private int width;
    private int height;
    
    public FlipY(int w,int h){
        width = w;
        height = h;
        result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);        
    }
    
    public void setSource(BufferedImage source){
        this.source = source;
    }
    
    public BufferedImage getResult(){
        return result;
    }
    
    public void flip(){
        for(int x=0; x<width; x++){
            for(int y=0; y<=height/2; y++){
                Color color = get(x,y);
                Color out = get(x,height-y-1);
                set(x,y,out);
                set(x,height-y-1,color);
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

