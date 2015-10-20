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
public class Rotation {
    private BufferedImage source;
    private BufferedImage result;
    private int width;
    private int height;

    public Rotation(int w, int h){
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
    
    private Color get(int c, int r) {
        return new Color(source.getRGB(c,r));
    }
    
    private void set(int c, int r, Color color) {
        result.setRGB(c,r,color.getRGB()); 
    }
    
    public void rotate(double angle){
        double newAngle = Math.toRadians(angle);
        if(newAngle>360){
            newAngle = newAngle%360;
        }
        double sin = Math.sin(newAngle);
        double cos = Math.cos(newAngle);
        double x = 0.5*(width-1);  //point to rotate
        double y = 0.5*(height-1); //image center
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                double a = i-x;
                double b = j-y;
                int newX = (int)(+a*cos-b*sin+x);
                int newY = (int)(+a*sin+b*cos+y);
                if(newX>=0 && newX<width && newY>=0 && newY<height) 
                    set(i,j, get(newX, newY));
            }
        }
    }
}
