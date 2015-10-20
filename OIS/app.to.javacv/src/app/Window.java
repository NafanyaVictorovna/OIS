/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.cpp.opencv_core;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import javax.swing.JFrame;

/**
 *
 * @author NafanyaVictorovna
 */
public class Window extends JFrame{
    
    public Window(){
        opencv_core.IplImage image = cvLoadImage("src/images/Dom.png");
        final CanvasFrame canvas = new CanvasFrame("Dom");
        canvas.showImage(image);
    }
}
