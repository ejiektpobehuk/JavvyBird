/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JavvyBird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * @author Vlad
 */
public class BirdsFrame extends javax.swing.JFrame {
    String version = "0.8.781";
    String title = "Javvy Bird ver. "+version;
    static BufferedImage bim_dp; // Вирт. окно поля-панели
    static Graphics gbim_dp; // его графический контекст
    static DrawPanel dp; //Панель виртуального окна
    Timer birdsTime; 
    static public int hits_per_game = 0; 
    boolean is_paused = true;
    static public String central_text = ""; //Текст выводимый при крушение птички
    static public Bird bird;
    Vector<Tube> tubes = null; //Вектор труб, предназначенный для удобного циклирования
    static public int tubes_passed = 0;
    boolean debug_bird = false;
    boolean debug_window = false;
    boolean no_clip = false; // Прохождение сковзь стены
    int multiply_sbt = 1; //множитель растояния между трубами
    int previous_Y=0; // высота текущего Y для расчёта следующего Y
    /**
     * Creates new form BirdsFrame
     */
    public BirdsFrame() {
        this.tubes = new Vector<Tube>(); //Создание вектора труб
        try{//Установление интерфэйса системы
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
           }catch(Exception e){}
        
        initComponents();
        bird = new Bird();
        //Расчёт необходимого количества труб из размера окна
        for(int i = 0; i< (jPanel1.getWidth()/(Tube.width+Tube.space_between_tubes*multiply_sbt+Tube.tubesEndDeltaX)+1); i++){
            tubes.add(new Tube());
        }
        dp = (DrawPanel)jPanel1;
        bim_dp = new BufferedImage((int)dp.getBounds().getMaxX(),
                (int)dp.getBounds().getMaxY(), BufferedImage.BITMASK);
        gbim_dp = bim_dp.getGraphics();
        //Установление минимального размера окна на основе параметров игровых объектов
        this.setMinimumSize(new Dimension(Tube.space_between_tubes+Tube.width+Tube.tubesEndDeltaX+Tube.tubesEndDeltaX, Tube.spacer_heigth+Tube.spacer_heigth+Tube.spacer_heigth+Tube.spacer_heigth+Tube.spacer_heigth   ));
        //Центрирование окна
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(new Point( (screenSize.width-frameSize.width)/2,
           (screenSize.height-frameSize.height)/2 )
        );
        //Объявление таймера
        birdsTime = new Timer(bird.falling , new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(is_paused==false){
                    bird.jumpNfall();
                    for(Tube i: tubes){
                        i.move();
                        if(bird.X==i.X+i.width+i.tubesEndDeltaX){tubes_passed++;}
                        if(i.X==0-i.width-i.tubesEndDeltaX){i.X=i.space_between_tubes*multiply_sbt+(i.width+i.space_between_tubes*multiply_sbt+i.tubesEndDeltaX)*(jPanel1.getWidth()/(i.width+i.space_between_tubes*multiply_sbt+i.tubesEndDeltaX)); central_text="Tube has been teleported"; i.depencive_rand(previous_Y);}
                        previous_Y = i.spacer_Y;
                    }
                }
                
                dp.paint_foreground(); //Отрисовка фона
                bird.paint(); //Отрисовка птички
                if(debug_window==true)dp.paint_borders();//Отрисовка тестовых полей
                
                for(Tube i: tubes){
                        i.paint();//Отрисовка одной пары труб
                        
                        
                        if(TubeDebugButton.getState()==true){i.paint_hole_borders();}//Отрисовка границ одного проёма
                        if((bird.X+bird.size>i.X&&bird.X<(i.X+i.width))&&no_clip==false){
                            if(bird.Y<i.spacer_Y||bird.Y+bird.size>i.spacer_Y+i.spacer_heigth){
                                is_paused=true;//Проверка на попадание птички в трубу
                            }
                        }
                    }
                
                if(bird.Y>(jPanel1.getSize().height-bird.size)||bird.Y<0)
                {is_paused=true; }//проверка на выход птички за раницы экрана
                
                if(debug_bird==true)dp.paint_test_stats();//Отрисовка тестовых данных
                repaint();
                //Обработка проигрышной ситуации
                if(is_paused==true){central_text="A crash!"; dp.paint_loose_panel(); birdsTime.stop();JumpMenuButton.setEnabled(true);}
                //Возвращение способности прыгать при достижениее верха параболы
                if(bird.new_Y==0){JumpMenuButton.setEnabled(true);}
            }
           
        });
        
        dp.paint_hello();//Отрисовка приветствия
    }

 



    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g = jPanel1.getGraphics();
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new DrawPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        GameMenu = new javax.swing.JMenu();
        JumpMenuButton = new javax.swing.JMenuItem();
        SBTMButton = new javax.swing.JMenuItem();
        ExitButton = new javax.swing.JMenuItem();
        AboutMenu = new javax.swing.JMenu();
        AboutButton = new javax.swing.JMenuItem();
        DebugMenu = new javax.swing.JMenu();
        DebugBirdButton = new javax.swing.JMenuItem();
        DebugWindowButton = new javax.swing.JMenuItem();
        NoClipButton = new javax.swing.JMenuItem();
        TubeDebugButton = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(title);
        setBackground(new java.awt.Color(153, 255, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(800, 400));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        GameMenu.setText("Game");

        JumpMenuButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_SPACE, 0));
        JumpMenuButton.setText("Jump");
        JumpMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JumpMenuButtonActionPerformed(evt);
            }
        });
        GameMenu.add(JumpMenuButton);

        SBTMButton.setText("SBT Multiplier: 1");
        SBTMButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SBTMButtonActionPerformed(evt);
            }
        });
        GameMenu.add(SBTMButton);

        ExitButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        ExitButton.setText("Exit");
        GameMenu.add(ExitButton);

        jMenuBar1.add(GameMenu);

        AboutMenu.setText("About");

        AboutButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        AboutButton.setText("About");
        AboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutButtonActionPerformed(evt);
            }
        });
        AboutMenu.add(AboutButton);

        jMenuBar1.add(AboutMenu);

        DebugMenu.setText("Debug");

        DebugBirdButton.setText("Bird: off");
        DebugBirdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DebugBirdButtonActionPerformed(evt);
            }
        });
        DebugMenu.add(DebugBirdButton);

        DebugWindowButton.setText("Window: off");
        DebugWindowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DebugWindowButtonActionPerformed(evt);
            }
        });
        DebugMenu.add(DebugWindowButton);

        NoClipButton.setText("No_Clip: off");
        NoClipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoClipButtonActionPerformed(evt);
            }
        });
        DebugMenu.add(NoClipButton);

        TubeDebugButton.setText("Tube");
        DebugMenu.add(TubeDebugButton);

        jMenuBar1.add(DebugMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Нажатие на кнопку: Game->Jump
    private void JumpMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JumpMenuButtonActionPerformed
        if(is_paused==true){//установление всех параметров в исходное состояние для начала новой игры
            bird.Y=jPanel1.getHeight()/2;
            is_paused = false;
            hits_per_game = 1;
            birdsTime.start();
            int previous_Y = 0;
            int counter = 0;
              for(Tube i: tubes){
                        if(counter==0){i.X = i.space_between_tubes; i.rand();}
                        else{i.X=i.space_between_tubes+(i.width+i.space_between_tubes+i.tubesEndDeltaX)*counter; i.depencive_rand(previous_Y);}
                        counter++;
                        previous_Y=i.spacer_Y;
                        tubes_passed=0;
                    }
        }
        else{
            hits_per_game++;}
        bird.delta_Y=bird.Y;//поправка расчётов траектории
        bird.new_Y=-bird.jump_heigth;
        
        JumpMenuButton.setEnabled(false);//Блокировка кнопки для ограничения возможноти прыжка
    }//GEN-LAST:event_JumpMenuButtonActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        dp = (DrawPanel)jPanel1; //присвоение буфферному изображению параметров изменённого jPanel1
        bim_dp = new BufferedImage((int)dp.getBounds().getMaxX(),
                (int)dp.getBounds().getMaxY(), BufferedImage.BITMASK);
        gbim_dp = bim_dp.getGraphics();
        is_paused=true;
        tubes.clear();
        //Расчёт количества труб на изменённый экран
        for(int i = 0; i< (jPanel1.getWidth()/(Tube.width+Tube.space_between_tubes+Tube.tubesEndDeltaX)+1); i++){
            tubes.add(new Tube());
        }
        birdsTime.stop();
        dp.paint_hello();//Вывод инструкции
        if(debug_window==true)dp.paint_borders(); //Отрисовка границ для отладки
    }//GEN-LAST:event_formComponentResized

    //Нажатие на кнопку: Debug->Bird
    private void DebugBirdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DebugBirdButtonActionPerformed
        if(debug_bird==false){debug_bird=true; this.DebugBirdButton.setText("Bird: on");}
        else{debug_bird=false; this.DebugBirdButton.setText("Bird: off");}
    }//GEN-LAST:event_DebugBirdButtonActionPerformed

    //Нажатие на кнопку: Debug->Window
    private void DebugWindowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DebugWindowButtonActionPerformed
        if(debug_window==false){debug_window=true; this.DebugWindowButton.setText("Window: on");}
        else{debug_window=false; this.DebugWindowButton.setText("Window: off");}
    }//GEN-LAST:event_DebugWindowButtonActionPerformed
    
    //Нажатие на кнопку: Debug->No_Clip
    private void NoClipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoClipButtonActionPerformed
        if(no_clip==false){no_clip=true; this.NoClipButton.setText("No_Clip: on");}
        else{no_clip=false; this.NoClipButton.setText("No_Clip: off");}
    }//GEN-LAST:event_NoClipButtonActionPerformed
    
    //Нажатие на кнопку: Game->SBT_Multiplier
    private void SBTMButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SBTMButtonActionPerformed
        switch(multiply_sbt){
            case 1: multiply_sbt=2; this.SBTMButton.setText("SBT Multiplier: 2"); break;
            case 2: multiply_sbt=1; this.SBTMButton.setText("SBT Multiplier: 1"); break;
        }
    }//GEN-LAST:event_SBTMButtonActionPerformed

     //Нажатие на кнопку: About->About
    private void AboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutButtonActionPerformed
        JOptionPane.showMessageDialog(this,title+"\nPetrov Vlad 23501/1", "About", 3);
    }//GEN-LAST:event_AboutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BirdsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BirdsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BirdsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BirdsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BirdsFrame().setVisible(true);
            }
        });
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AboutButton;
    private javax.swing.JMenu AboutMenu;
    private javax.swing.JMenuItem DebugBirdButton;
    private javax.swing.JMenu DebugMenu;
    private javax.swing.JMenuItem DebugWindowButton;
    private javax.swing.JMenuItem ExitButton;
    private javax.swing.JMenu GameMenu;
    private javax.swing.JMenuItem JumpMenuButton;
    private javax.swing.JMenuItem NoClipButton;
    private javax.swing.JMenuItem SBTMButton;
    private javax.swing.JCheckBoxMenuItem TubeDebugButton;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
