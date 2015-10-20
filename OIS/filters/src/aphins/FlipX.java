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
public class FlipX {
    private BufferedImage source;
    private BufferedImage result;
    private int width;
    private int height;
    
    public FlipX(int w,int h){
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
        for(int y=0; y<height; y++){
            for(int x=0; x<=width/2; x++){
                Color color = get(x,y);
                Color out = get(width-x-1,y);
                set(x,y,out);
                set(width-x-1,y,color);
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
