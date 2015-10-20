/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clustering;

/**
 *
 * @author NafanyaVictorovna
 */
public class Cluster {
    private int mean;
    public int upbound;
    public int lowbound;
    
    public Cluster(int mean){
        this.mean = mean;
    }
    
    public void setBounds(int lb, int ub){
        lowbound = lb;
        upbound = ub;
    }
    
    public void setMean(int m){
        this.mean = m;
    }
    
    public int getMean(){
        return mean;
    }
    
    public int getLower(){
        return lowbound;
    }
    
    public int getUpper(){
        return upbound;
    }
    
    public void calculateMean(int [] hist){
        int temp = 0;
        int counter = 0;
        for(int i = lowbound; i<=upbound; i++){
            counter += hist[i];
            temp += hist[i] *i;
        }
        mean = temp/counter;
    }
}