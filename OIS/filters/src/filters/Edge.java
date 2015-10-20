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
public class Edge {
    
    private BufferedImage source;
    private BufferedImage result;
    private int width;
    private int height;
    private int [][] firstF;
    private int [][] secondF;
    
    public Edge(int w, int h){
      if(w<0) throw new IllegalArgumentException("width must be nonnegative");
      if(h<0) throw new IllegalArgumentException("height must be nonnegative");
      width = w;
      height = h;
      result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      int[][] first = {{-1,0,1},{-1,0,1},{-1,0,1}};
      int[][] second = {{1,0,1},{0,1,0},{-1,-2,-1}};
      setFilters(first, second);
    }
    
    public void setSource(BufferedImage source) {
        this.source = source;
    }
    
    public BufferedImage getResult() {
        return result;
    }
    
    private void setFilters(int[][] f, int[][] s){
        firstF = f;
        secondF = s;
    }
    
    private double luminance(Color color){
      int r = color.getRed();
      int g = color.getGreen();
      int b = color.getBlue();
      return .299f*r + .587f*g + .114f*b;
    }
    
    private Color gray(Color color){
        int y = (int)(Math.round(luminance(color)));
        Color gray = new Color(y,y,y);
        return gray;
    }
    
    private int truncate(int a){
        if(a<0)return 0;
        else if(a>255)return 255;
        else return a;
    }
     public void border(){
         for(int y = 1; y<height-1; y++){
             for(int x = 1; x<width-1; x++){
                 int[][] gray = new int[3][3];
                 for(int i=0; i<3; i++){
                     for(int j=0; j<3; j++){
                         gray[i][j] = (int) luminance(this.get(x-1+i, y-1+j));
                     }
                 }         
                 int g1 = 0;
                 int g2 = 0;
                 for (int i = 0; i < 3; i++) {
                     for (int j = 0; j < 3; j++) {
                         g1 += gray[i][j] * firstF[i][j];
                         g2 += gray[i][j] * secondF[i][j];
                    }
                }
                 int magnit = 255 - truncate((int) Math.sqrt(g1*g1 + g2*g2));
                 Color grscale = new Color (magnit,magnit,magnit);
                 set(x, y, grscale);
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