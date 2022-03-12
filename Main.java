/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author erikj
 */
public class Main extends JPanel implements Runnable,KeyListener {
    private Principal p;
    private int x = 0;
    private int y = 0;
      // Graphics g;
    public Main(){
        setFocusable(true);
        p = new Principal();
          addKeyListener((KeyListener) this);
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
    public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
                    if(KeyEvent.getKeyText(e.getKeyCode()).equals("Izquierda")){
                        x = p.getX();
                       // System.out.println("X:Clic "+x);
                        x = x+1;
                        p.setX(x);
                    }else if(KeyEvent.getKeyText(e.getKeyCode()).equals("Derecha")){
                         y = p.getY();
                        // System.out.println("y:Clic "+y);
                        y = y+1;
                        p.setY(y);
                        
                    }
                        //System.out.println("entro");
//			System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
		}

		@Override
		public void keyReleased(KeyEvent e) {
			//System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
		}
}
