package JavvyBird;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;

/**
 * Класс птички
 * @author Vlad
 */
public class Bird {
    static Color body_colour = Color.red;
    static int X = 45; //Во время игры не меняется
    static int Y = 100;
    static int size = 15;
    static int new_Y; //Компенсатор расчёта траетктории
    static int delta_Y = 0; //Хранит предел скорости при свободном падении 
    static int falling = 50;//Скорость таймера, ответственного за птичку
    static int jump_heigth = 5;
    
    
    /**
    * Расчёт траектории прыжка и падения
    */
    public int jumpNfall(){
        if(new_Y<12){Y=new_Y*new_Y+delta_Y-jump_heigth*jump_heigth; new_Y++;}
        else{Y+=(new_Y*new_Y+delta_Y-jump_heigth*jump_heigth)-((new_Y-1)*(new_Y-1)+delta_Y-jump_heigth*jump_heigth);}
        return Y;
    }
    
    /**
    * отрисовка птички
    */
    void paint(){
        Graphics g = BirdsFrame.dp.getGraphics();
        BirdsFrame.gbim_dp.setColor(body_colour);
        BirdsFrame.gbim_dp.fillOval(X, Y, size, size);
    }

}
