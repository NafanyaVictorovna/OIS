/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filters;

import aphins.FlipX;
import aphins.FlipY;
import aphins.Rotation;
import aphins.Scale;
import clustering.K_means;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
/**
 *
 * @author NafanyaVictorovna
 */
public class Window extends JFrame{
    
    private BufferedImage image;
    private BufferedImage result;
    private JButton start;
    private JButton cluster;
    private JButton conversion;
    private JLabel label;
    private JLabel imgIcon;
    private JRadioButton radio1;
    private JRadioButton radio2;
    private JRadioButton radio3;
    private JRadioButton radio4;
    private JRadioButton scale;
    private JRadioButton flipX;
    private JRadioButton flipY;
    private JRadioButton rotate;
    private ButtonGroup bg;
    private ButtonGroup conv;
    private JPanel buttonPanel;
    private JPanel filters;
    private JPanel converse;
    private JPanel imagePanel;
    private JPanel sourceImagePanel;
    private JPanel pane;
    private GridLayout layout;
    private JScrollPane scroller;
    
    private int[] histogram;
    

    public Window(String name){ 
        super(name);
        init();
        listeners();
    }
 
   private void openImage(String name){
        try {
            image = ImageIO.read(new File(name));
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   private void init(){
          
        buttonPanel = new JPanel();
        imagePanel = new JPanel();
        sourceImagePanel = new JPanel();
        filters = new JPanel();
        converse = new JPanel();
        pane = new JPanel();
        
        layout = new GridLayout(0,3);
        pane.setLayout(layout);
        filters.setLayout(new GridLayout(0,2));
        converse.setLayout(new GridLayout(0,2));
        buttonPanel.setLayout(new GridLayout(0,1));
        imagePanel.setLayout(new GridLayout(0,1));
        sourceImagePanel.setLayout(new GridLayout(0,1));
        
        layout.addLayoutComponent("0", sourceImagePanel);
        layout.addLayoutComponent("1", imagePanel);
        layout.addLayoutComponent("2", buttonPanel);
        //применить
        start = new JButton("start");
        start.setSize(20,5);
        cluster = new JButton("clustering");
        cluster.setSize(20,5);
        conversion = new JButton("converse");
        conversion.setSize(20,5);
        //фильтры:
        TitledBorder border = BorderFactory.createTitledBorder("filters:");
        TitledBorder con = BorderFactory.createTitledBorder("converse:");
        filters.setBorder(border);
        converse.setBorder(con);
        bg = new ButtonGroup();
        conv = new ButtonGroup();
        //выделения границ
        radio1 = new JRadioButton("Canny edge detector");
        //яркость
        radio2 = new JRadioButton("brightness");
        //рассеяние
        radio3 = new JRadioButton("blur");
        //градации серого
        radio4 = new JRadioButton("greyscale");
        //
        scale = new JRadioButton("scale");
        flipX = new JRadioButton("reflection of X");
        flipY = new JRadioButton("reflection of Y");
        rotate = new JRadioButton("rotation");
        
        bg.add(radio1);
        bg.add(radio2);
        bg.add(radio3);
        bg.add(radio4);
        //
        conv.add(scale);
        conv.add(flipX);
        conv.add(flipY);
        conv.add(rotate);
        //
        openImage("src/images/minion.png");
        //openImage("../src/images/flowers.png");
        label = new JLabel(new ImageIcon(image));
        imgIcon = new JLabel();
        
        filters.add(radio1);
        filters.add(radio2);
        filters.add(radio3);
        filters.add(radio4);
        //
        converse.add(scale);
        converse.add(flipX);
        converse.add(flipY);
        converse.add(rotate);
        
        filters.setSize(new Dimension(90, 90));
        converse.setSize(new Dimension(90, 90));
        buttonPanel.add(start);
        sourceImagePanel.add(label);
        imgIcon = new JLabel();
        
        imagePanel.add(imgIcon);
        buttonPanel.add(filters);
        
        buttonPanel.add(cluster);
        
        buttonPanel.add(converse);
        buttonPanel.add(conversion);
        
        scroller = new JScrollPane(imagePanel);
        scroller.setPreferredSize(new Dimension(150,150));
        JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 30,20,0,300);
        JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 30,40,0,300);
        
        imagePanel.add(vbar, BorderLayout.EAST);
        imagePanel.add(hbar, BorderLayout.SOUTH);
        
        pane.add(sourceImagePanel);
        pane.add(buttonPanel);
        setContentPane(pane);  
        
    }
 
private void listeners(){
           start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
              if(radio1.isSelected()){
//strange
                Border detector = new Border();
                detector.setSourceImage(image);
                detector.setLowThershold(0.5f);
                detector.setHighThershold(1f);
                detector.setGaussianKernelRadius(1.75f);
                detector.setGaussianKernelWidth(32);
                detector.setSourceImage(image);
                detector.process();
                result = detector.getEdgesImage();   
                 } 
                    else if(radio2.isSelected()){
                  //double effect!
                        Brightness contrast = new Brightness(image.getWidth(),image.getHeight());
                        contrast.setSource(image);
                        contrast.bright();
                        result = contrast.getResult();
                        contrast.setSource(result);
                        contrast.bright();
                        result = contrast.getResult();
                     }
              else if(radio3.isSelected()){
                  Blure glass = new Blure(image.getWidth(),image.getHeight());
                  glass.setSource(image);
                  glass.glass();
                  result = glass.getResult();
              }
              else if(radio4.isSelected())
              {
                Edge edge = new Edge(image.getWidth(),image.getHeight());
                edge.setSource(image);
                edge.border();
                result = edge.getResult(); 
              } else {
                  JOptionPane.showMessageDialog(pane, "You must choose one of filters");
              }
              imgIcon.setIcon(new ImageIcon(result));
//
              pane.add(imagePanel.add(imgIcon));
              pane.updateUI();
         }
        }); 
         
        cluster.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            String str = JOptionPane.showInputDialog(null, "input numbers of clusters", JOptionPane.QUESTION_MESSAGE);
            int k = Integer.parseInt(str);
            histogram = new int[256];
            makeHistogram();
            K_means cluster = new K_means(image.getWidth(), image.getHeight());
            cluster.setImage(image);
            cluster.KMeans(k,histogram,3);
            result = cluster.getResultImage();
            imgIcon.setIcon(new ImageIcon(result));
//
            pane.add(imagePanel.add(imgIcon));
            pane.updateUI(); 
            }
            });
        
        conversion.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(scale.isSelected()){
                    String w = JOptionPane.showInputDialog(null, "input new width", JOptionPane.QUESTION_MESSAGE);
                    String h = JOptionPane.showInputDialog(null, "input new height", JOptionPane.QUESTION_MESSAGE);
                    int wid = Integer.parseInt(w);
                    int heig = Integer.parseInt(h);
                    if(wid>7979 || heig>7979){
                        JOptionPane.showMessageDialog(pane, "You must input size less than 7980");
                        result = image;
                    } else{
//                        if(wid>image.getWidth()){
//                            
//                        }
                        Scale scale = new Scale(wid, heig);
                        scale.setSource(image);
                        scale.scaleImage();
                        result = scale.getResult();
                        imgIcon.setAutoscrolls(true);
                        Rectangle rect = new Rectangle(result.getWidth(), result.getHeight(), 1, 1);
                        imgIcon.scrollRectToVisible(rect);
                    }
                 } 
                    else if(flipX.isSelected()){
                        FlipX x = new FlipX(image.getWidth(), image.getHeight());
                        x.setSource(image);
                        x.flip();
                        result = x.getResult();
                     }
              else if(flipY.isSelected()){
                        FlipY y = new FlipY(image.getWidth(), image.getHeight());
                        y.setSource(image);
                        y.flip();
                        result = y.getResult();
              }
              else if(rotate.isSelected()){
                  boolean bool = false;
                      Rotation rotation = new Rotation(image.getWidth(), image.getHeight());
                      String w = JOptionPane.showInputDialog(null, "input angle of rotation (lower 360)", 
                              JOptionPane.QUESTION_MESSAGE);
                      for(char c: w.toCharArray()){
                          if(Character.isDigit(c)) bool = true;
                          else  bool = false;
                      }
                      if(!bool){
                            JOptionPane.showMessageDialog(pane, "You must input a number");
                            bool = true;
                      } else { bool = false;}                     
                        double angle = Double.parseDouble(w);
                        rotation.setSource(image);
                        rotation.rotate(angle);
                        result = rotation.getResult();
                          
                      
              } else {
                  JOptionPane.showMessageDialog(pane, "You must choose one of convertion");
              }
              imgIcon.setIcon(new ImageIcon(result));
//
              pane.add(imagePanel.add(imgIcon));
              pane.updateUI(); 
            }
        }); 
    } 
    
    private void makeHistogram() {
        BufferedImage img = image;
        for (int i = 0; i < 256; i++){
             histogram[i] = 0;
        }
             for (int h=0; h<img.getHeight(); h++) {
		for (int w=0; w<img.getWidth(); w++) {
                    Color color = new Color(img.getRGB(w, h));
                    int value = color.getBlue();
                    histogram[value] += 1;
		}
            }
	}
}       