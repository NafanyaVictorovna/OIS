package app;

import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.FrameRecorder;
import detection.Image;
/**
 *
 * @author NafanyaVictorovna
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FrameGrabber.Exception, FrameRecorder.Exception {    
        Image img = new Image("src/images/Dom.png");
        img.recognition();
    }    
}
/*
int main(int argc, char** argv)  
{  
  
 CvMat* trainData = cvCreateMat(classes * train_samples,ImageSize, CV_32FC1);  
 CvMat* trainClasses = cvCreateMat(classes * train_samples, 1, CV_32FC1);  
  
 namedWindow("single", CV_WINDOW_AUTOSIZE);  
 namedWindow("all",CV_WINDOW_AUTOSIZE);  
  
 LearnFromImages(trainData, trainClasses);  
  
 KNearest knearest(trainData, trainClasses);  
  
 RunSelfTest(knearest);  
  
 cout << "losgehts\n";  
  
 AnalyseImage(knearest);  
  
 return 0;  
  
}
*/