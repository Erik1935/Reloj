/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Thread.sleep;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author erikj
 */
public class Main extends JPanel implements Runnable{
    private int x = 0;
    private int y = 0;
      // Graphics g;
    public Main(){
        setFocusable(true);
          Thread t = new Thread(this);
        t.start();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    @Override
    public void paintComponent(Graphics g){
        BufferedImage imagen = cargarImg("manecilla.png");
        AffineTransform at = AffineTransform.getTranslateInstance(100, 100);
        at.rotate(Math.toRadians(getX()), imagen.getWidth() / 2, imagen.getHeight() / 2);
        Graphics2D d2 = (Graphics2D) g;
        d2.drawImage(imagen, at, null);
        repaint();
        //d2.dispose();
    }
     BufferedImage cargarImg(String name) {
        try {
//            System.out.println("entroi");
            return ImageIO.read(Principal.class.getResource(name));
        } catch (IOException ex) {
            //Logger.getLogger(Carga.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return null;
    }
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.add(new Main());
        f.setVisible(true);
        f.setSize(400,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    @Override
    public void run() {
        int i = 0;
        while(true){
            
            try {
                //repaint();
                i=i+10;
                if(i==360){
                    i=0;
                }
                setX(i);
                //repaint();
                //g.dispose();
                sleep(1000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
