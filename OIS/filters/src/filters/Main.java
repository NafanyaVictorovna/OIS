/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filters;

import java.io.IOException;
import javax.swing.JFrame;
/**
 *
 * @author NafanyaVictorovna
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Window window = new Window("фильтры");
        window.setSize(580, 570);
        window.setLocation(430, 180);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.show();
        window.repaint();
    }  
}