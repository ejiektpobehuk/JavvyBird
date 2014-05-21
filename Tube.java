package JavvyBird;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Класс пар труб(верхней и нижней)
 * @author Vlad
 */
public class Tube {
    int X = 200;
    int spacer_Y; //Расположение верхней границы пространства между трубами
    static int spacer_heigth = 50;//Высота этого пространства
    static int width = 40;
    static int tubesEndDeltaX = 5;//Отступ раширения трубы от самой трубы
    static int tubesEndY = 10;//Высота отступа
    static int space_between_tubes = 200;
    static Color colour = Color.darkGray;
    Random rand = new Random();
    int[] polX = new int[8];//Массив для отрисовки полигона по Х
    int[] polY = new int[8];//Массив для отрисовки полигона по Y
    
    /**
    * Задание случайного значения расположения верхней границы пространства между трубами
    */
    public void rand(){
        spacer_Y=rand.nextInt(spacer_heigth*3)+BirdsFrame.jPanel1.getHeight()/2-spacer_heigth;
        //spacer_Y=(int)(rand.nextInt((BirdsFrame.jPanel1.getHeight()-(spacer_heigth*3)))+spacer_heigth);
    }

    /**
    * Задание случайного значения расположения верхней границы пространства между трубами с учётом высоты предыдущей трубы
    */
    public void depencive_rand(int previous_Y){
        //spacer_Y=rand.nextInt(spacer_heigth*4)+previous_Y-spacer_heigth*2;
        spacer_Y=rand.nextInt(spacer_heigth*3)+previous_Y-spacer_heigth;
        if(spacer_Y<spacer_heigth){spacer_Y=spacer_heigth;}
        if((BirdsFrame.jPanel1.getHeight()-spacer_Y)<2*spacer_heigth){spacer_Y=BirdsFrame.jPanel1.getHeight()-spacer_heigth*2;}
    }
    
    /**
    * Расчёт движения труб
    */
    public void move(){
        X-=5;
    }
    
    /**
    * Отрисовка пары труб
    */
    void paint(){
        Graphics g = BirdsFrame.dp.getGraphics();
        BirdsFrame.gbim_dp.setColor(colour);
        //polX = new int[]{X+5, X+5, X, X, X+width, X+width, X+width-5, X+width-5};
        polX[0] = X; polX[1] = X; polX[2] = X-tubesEndDeltaX; polX[3] = X-tubesEndDeltaX;  polX[4] = X+width+tubesEndDeltaX; polX[5] = X+width+tubesEndDeltaX; polX[6] = X+width; polX[7] = X+width;
        //polY = new int[]{0, spacer_Y, spacer_Y, spacer_Y+5, spacer_Y+5, spacer_Y, spacer_Y, 0};
        polY[0] = 0; polY[1] = spacer_Y-tubesEndY; polY[2] = spacer_Y-tubesEndY; polY[3] = spacer_Y; polY[4] = spacer_Y; polY[5] = spacer_Y-tubesEndY; polY[6] = spacer_Y-tubesEndY; polY[7] = 0;
        BirdsFrame.gbim_dp.fillPolygon(polX, polY, 8);
        //polX = new int[]{X+5, X+5, X, X, X+width, X+width, X+width-5, X+width-5};
        polX[0] = X; polX[1] = X; polX[2] = X-tubesEndDeltaX; polX[3] = X-tubesEndDeltaX; polX[4] = X+width+tubesEndDeltaX; polX[5] = X+width+tubesEndDeltaX; polX[6] = X+width; polX[7] = X+width;
        //polY = new int[]{(int)BirdsFrame.dp.getBounds().getMaxY(), spacer_Y+spacer_heigth+5, spacer_Y+spacer_heigth+5, spacer_Y+spacer_heigth, spacer_Y+spacer_heigth, spacer_Y+spacer_heigth+5, spacer_Y+spacer_heigth+5, (int)BirdsFrame.dp.getBounds().getMaxY()};
        polY[0] = (int)BirdsFrame.jPanel1.getHeight(); polY[1] = spacer_Y+spacer_heigth+tubesEndY; polY[2] = spacer_Y+spacer_heigth+tubesEndY; polY[3] = spacer_Y+spacer_heigth; polY[4] = spacer_Y+spacer_heigth; polY[5] = spacer_Y+spacer_heigth+tubesEndY; polY[6] = spacer_Y+spacer_heigth+tubesEndY; polY[7] = (int)BirdsFrame.jPanel1.getHeight();
        BirdsFrame.gbim_dp.fillPolygon(polX, polY, 8);
        
        g.setColor(Tube.colour);
    }
    
    /**
    * Отрисовка границ пространства между трубами для отладки
    */
    void paint_hole_borders(){
        Graphics g = BirdsFrame.dp.getGraphics();
        BirdsFrame.gbim_dp.setColor(Color.DARK_GRAY);
        
        //BirdsFrame.gbim_dp.drawRect(X, spacer_Y, width+space_between_tubes, spacer_heigth);
        BirdsFrame.gbim_dp.drawLine(X-width-space_between_tubes, spacer_Y, X, spacer_Y);
        BirdsFrame.gbim_dp.drawLine(X-width-space_between_tubes, spacer_Y+spacer_heigth, X, spacer_Y+spacer_heigth);
    }
    
}

