package JavvyBird;

import static JavvyBird.BirdsFrame.jPanel1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Класс буферного изображения и методов отрисовки различных объектов
 * @author Vlad
 */
public class DrawPanel extends JPanel{
     @Override
     
    /**
    * Отрисовка буфферного изображения
    */
    public void paint(Graphics g) {
        super.paint(g); 
        g.drawImage(BirdsFrame.bim_dp, 0, 0, this);
    }
    
    /**
    * Отрисовка фона для наличия его обновления
    */
    void paint_foreground(){
        Graphics g = BirdsFrame.dp.getGraphics();
        BirdsFrame.gbim_dp.setColor(BirdsFrame.jPanel1.getBackground());
        BirdsFrame.gbim_dp.fillRect(0, 0, BirdsFrame.jPanel1.getWidth(), BirdsFrame.jPanel1.getHeight());
    }
    
    /**
    * Отрисовка параметро для отладки
    */
    void paint_test_stats(){
        Graphics g = BirdsFrame.dp.getGraphics();
        BirdsFrame.gbim_dp.setColor(Color.black);
        BirdsFrame.gbim_dp.drawString("Y = "+BirdsFrame.bird.Y, 50, 10);
        BirdsFrame.gbim_dp.drawString("new_Y = "+BirdsFrame.bird.new_Y, 50, 20);
        BirdsFrame.gbim_dp.drawString("delta_Y = "+BirdsFrame.bird.delta_Y, 50, 30);
        BirdsFrame.gbim_dp.drawString("hits per game = "+BirdsFrame.hits_per_game, 50, 40);
        BirdsFrame.gbim_dp.drawString("tubes_passed = "+BirdsFrame.tubes_passed, 50, 50);
    }
    
    /**
    * Отрисовка окна поражения
    */
    void paint_loose_panel(){
        int text_length = 0;
        for(char i: BirdsFrame.central_text.toCharArray()){
            text_length+=this.getFontMetrics(getFont()).charWidth(i);
            text_length++;
        }
        Graphics g = BirdsFrame.dp.getGraphics();
        BirdsFrame.gbim_dp.setColor(Color.ORANGE);
        BirdsFrame.gbim_dp.fillRect(BirdsFrame.jPanel1.getWidth()/2-200, BirdsFrame.jPanel1.getHeight()/2-50, 400, 100);
        BirdsFrame.gbim_dp.setColor(Color.red);
        BirdsFrame.gbim_dp.fillRect(BirdsFrame.jPanel1.getWidth()/2-190, BirdsFrame.jPanel1.getHeight()/2-40, 380, 80);
        BirdsFrame.gbim_dp.setColor(Color.black);
        BirdsFrame.gbim_dp.drawString("Score: "+BirdsFrame.tubes_passed, (BirdsFrame.jPanel1.getWidth()/2-text_length/2),
           (BirdsFrame.jPanel1.getHeight()/2 ));
        
        BirdsFrame.gbim_dp.drawString("Jumps: "+BirdsFrame.hits_per_game, (BirdsFrame.jPanel1.getWidth()/2-text_length/2),
           (BirdsFrame.jPanel1.getHeight()/2+10 ));        
        
        BirdsFrame.gbim_dp.drawString(BirdsFrame.central_text, (BirdsFrame.jPanel1.getWidth()/2-text_length/2),
           (BirdsFrame.jPanel1.getHeight()/2-10 ));
    }
    
    /**
    * Отрисовки границ для отладки
    */
    void paint_borders(){
        Graphics g = BirdsFrame.dp.getGraphics();
        BirdsFrame.gbim_dp.setColor(Color.black);
        BirdsFrame.gbim_dp.drawRect(10, 10, BirdsFrame.jPanel1.getWidth()-20, BirdsFrame.jPanel1.getHeight()-20);
        BirdsFrame.gbim_dp.drawRect(0, Tube.spacer_heigth, BirdsFrame.jPanel1.getWidth(), BirdsFrame.jPanel1.getHeight()-(Tube.spacer_heigth*2));
        BirdsFrame.gbim_dp.setColor(Color.black);
        BirdsFrame.gbim_dp.drawString(" "+(BirdsFrame.jPanel1.getWidth()/(Tube.width+Tube.space_between_tubes)), 200,
           50);
    }
    
    /**
    * Отрисовка окон приветствия и изменения экрана
    */
    void paint_hello(){
        String text = "Welcome to Javvy Bird";
        int text_length = 0;
        for(char i: text.toCharArray()){
            text_length+=this.getFontMetrics(getFont()).charWidth(i);
            text_length++;
        }
        
        Graphics g = BirdsFrame.dp.getGraphics();
        BirdsFrame.gbim_dp.setColor(Color.black);
        BirdsFrame.gbim_dp.fillRect(BirdsFrame.jPanel1.getWidth()/2-200, BirdsFrame.jPanel1.getHeight()/2-50, 400, 100);
        BirdsFrame.gbim_dp.setColor(Color.red);
        BirdsFrame.gbim_dp.fillRect(BirdsFrame.jPanel1.getWidth()/2-190, BirdsFrame.jPanel1.getHeight()/2-40, 380, 80);
        
        BirdsFrame.gbim_dp.setColor(Color.black);
        BirdsFrame.gbim_dp.drawString(text, (BirdsFrame.jPanel1.getWidth()/2-text_length/2),
           (BirdsFrame.jPanel1.getHeight()/2-5 ));
        text="Press \"Space\" to start!";
        text_length = 0;
        for(char i: text.toCharArray()){
            text_length+=this.getFontMetrics(getFont()).charWidth(i);
            text_length++;
        }
        BirdsFrame.gbim_dp.drawString(text, (BirdsFrame.jPanel1.getWidth()/2-text_length/2),
           (BirdsFrame.jPanel1.getHeight()/2+10 ));
        
    }
}