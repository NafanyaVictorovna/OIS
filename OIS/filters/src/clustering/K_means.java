/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clustering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * @author NafanyaVictorovna
 */
public class K_means {
    
    private BufferedImage image;
    private BufferedImage result;
    private boolean not_term;
    private int loops;
    private int changedPix;
    private int[] histogram;
    private ArrayList<Cluster> clas;
    private int[] lowbound;
    public final static int MEAN_BY_MOD = 1;
    public final static int MEAN_BY_SPACE = 2;
    public final static int MEAN_BY_RANDOM = 3;
    
    public K_means(int width, int height){
        result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }
    // bins - clusters(k)
    public void KMeans(int bins, int[] hist, int initway){
        histogram = hist;
        lowbound = new int[bins];
        initialize(image,bins,initway);
        calculateBounds();
        while(not_term){
            recalculateMeans();
            loops++;
            checkTermination();
        }
        processImage(image,bins);
    }

    private void processImage(BufferedImage image, int bins) {
      int delta = 255/(bins-1);
      for(int h=0; h<image.getHeight(); h++){
        for(int w =0; w<image.getWidth(); w++){
            Color rgb = new Color(image.getRGB(w, h));
            int red = rgb.getRed();
            int green = rgb.getGreen();
            int blue = rgb.getBlue();
            for(int i=0; i<clas.size(); i++){
                if(red>clas.get(i).lowbound && red<clas.get(i).upbound){
                    int g = i*delta;
                    result.setRGB(w, h, (new Color(g,green,blue)).getRGB());
                   // result.setRGB(w, h, (new Color(red,green,g)).getRGB());
                }
            }
        }
      }
    }
    
    public BufferedImage getResultImage(){
        return result;
    }
    
    public void setImage(BufferedImage image){
        this.image = image;
    }

    private void initialize(BufferedImage image, int bins, int initway){
        loops = 0;
        changedPix = 0;
        clas = new ArrayList<Cluster>();
        for(int i=0; i<bins; i++){
            Cluster cls = new Cluster(createMean(initway, bins, i, image));
            clas.add(cls);
        }
    }
    
    private void calculateBounds(){
        for(int i =0; i<clas.size(); i++){
            int lb = calculateLowerBound(clas.get(i));
            lowbound[i] = lb;
            clas.get(i).setBounds(lb, calculateUpperBound(clas.get(i)));
        }
    }
    
    private int calculateLowerBound(Cluster cls){
        int cMean = cls.getMean();
        int current = 0;
        for(int i=0; i<clas.size(); i++){
            if(cMean > clas.get(i).getMean()){
                current = Math.max((cMean + clas.get(i).getMean())/2, current);
            } else {}
        }
        return current;
    }
    
    private int calculateUpperBound(Cluster cls){
        int cMean = cls.getMean();
        int current = 255;
        for(int i=0; i<clas.size(); i++){
            if(cMean < clas.get(i).getMean()){
                current = Math.min((cMean + clas.get(i).getMean())/2, current);
            } else {}
        }
        return current;
    }
        
    private void recalculateMeans(){
        for(int i =0; i<clas.size(); i++){
            clas.get(i).calculateMean(histogram);
        }
        calculateChangedPix();
    } 
    
    private void checkTermination(){
        if(loops >= 50){
            not_term = false;
        }
        if(changedPix <= 300){
            not_term = false;
        }
    }
    
    private void calculateChangedPix(){
        int ch = 0;
        for(int i =0; i<clas.size(); i++){
            int c = calculateLowerBound(clas.get(i));
            if(c<lowbound[i]){
                for(int j =c; j<lowbound[i]; j++)
                    ch += histogram[j];
            }
            if(c>lowbound[i]){
                for(int j =lowbound[i]; j<c; j++)
                    ch += histogram[j];
            }
        }
        changedPix = ch;
        calculateBounds();
    }
    
    private int createMean(int initway, int bins, int ind, BufferedImage image){
        switch(initway){
            case MEAN_BY_MOD:
                int pixel = 0;
                int sum = 0;
                int value = 0;
                for(int h =0; h<image.getHeight(); h++){
                    for(int w =0; w<image.getWidth(); w++){
                        pixel +=1;
                        if(pixel%bins == ind){
                            Color rgb = new Color(image.getRGB(w, h));
                            sum += rgb.getBlue();
                            value++;
                        }
                    }
                } 
            return sum/value;
            case MEAN_BY_SPACE:
                return (int)(255/(bins-1)*ind);
            case MEAN_BY_RANDOM:
                Double mean = Math.random()*255;
                return (int) Math.floor(mean);
            default: 
                return 0;
        } 
    }
}