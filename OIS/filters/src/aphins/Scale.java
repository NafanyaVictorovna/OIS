package aphins;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * @author NafanyaVictorovna
 */
public class Scale {
    
    private BufferedImage source;
    private BufferedImage result;
    private int width;
    private int height;
    private int newWidth;
    private int newHeight;
    
    public Scale(int w, int h){
        newWidth = w;
        newHeight = h;
        result = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
    }
    
    public void setSource(BufferedImage image){
        source = image;
        width = source.getWidth();
        height = source.getHeight();
    }
    
    public BufferedImage getResult(){
        return result;
    }
    
    public void scaleImage(){
        for(int x=0; x<newWidth; x++){
            for(int y=0; y<newHeight; y++){
                int newX = x*width/newWidth;
                int newY = y*height/newHeight;
                Color color = get(newX, newY);
                set(x,y, color);
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