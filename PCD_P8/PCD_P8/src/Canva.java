
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lucia Zamudio 
 */
public class Canva extends Canvas{

    Image offscreen, fondo, derecha, izquierda;
    int ancho, alto;
    Pasarela pasarela;

    void setPasarela(Pasarela pasarela) {
        this.pasarela = pasarela;
        pasarela.setCanvas(this);
    }
    
    public Canva(int ancho, int alto) {
        this.ancho=ancho;
        this.alto=alto;
        setSize(ancho,alto);
        fondo = new ImageIcon(getClass().getResource("/fondoP8.jpg")).getImage();
        izquierda= new ImageIcon(getClass().getResource("/chica.png")).getImage();
        derecha = new ImageIcon(getClass().getResource("/chico.png")).getImage();
        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(fondo, 0);
        dibu.addImage(derecha, 1);
        dibu.addImage(izquierda, 2);
        try {
            dibu.waitForAll();
        } catch (InterruptedException ex) {
            System.getLogger(Canva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    @Override
    public void paint(Graphics g){
        offscreen = createImage(getWidth(), getHeight());
        Graphics og= offscreen.getGraphics();
        og.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        
        og.setColor(Color.WHITE);
        og.fillRect(75, 580, 150, 30);
        og.fillRect(450, 580, 150, 30);
        og.setColor(Color.BLACK);
        og.setFont(new Font("Segoe UI", Font.BOLD, 20));
        og.drawString("Cola Derecha", 460 , 600);
        og.drawString("Cola Izquierda", 80 , 600);
        
        if(pasarela != null){
        pasarela.lock.lock();
        try{
            int alto_img=120;
            int ancho_img=90;
            int xIzq = 350;
            int xDer = 400;
            int altura_cola= 620;
            int separacion = 70;

            og.setFont(new Font("Segoe UI", Font.BOLD, 16));
                for (int i = 0; i < pasarela.colaIzq.size(); i++) {
                    og.drawImage(izquierda, xIzq - i* separacion, altura_cola, ancho_img, alto_img, this);
                    int id= pasarela.colaIzq.get(i);
                    og.drawString("I"+id, xIzq- i * separacion, 750);
                }

                for (int i = 0; i < pasarela.colaDer.size(); i++) {
                    og.drawImage(derecha, xDer + i * separacion,altura_cola, ancho_img, alto_img, this);
                    int id= pasarela.colaDer.get(i);
                    og.drawString("D"+id, xDer + i * separacion, 750);
                }

                og.setColor(Color.WHITE);
                for (int i = 0; i < pasarela.cruzandoIzq.size(); i++) {
                    og.drawImage(izquierda, 250 - i * separacion, 300, ancho_img, alto_img, this);
                    int id= pasarela.cruzandoIzq.get(i);
                    og.drawString("I"+id, 250- i * separacion, 440);
                }
                for (int i = 0; i < pasarela.cruzandoDer.size(); i++) {
                    og.drawImage(derecha, 340 + i * separacion, 300, ancho_img, alto_img, this);
                    int id= pasarela.cruzandoDer.get(i);
                    og.drawString("D"+id, 340 + i * separacion, 440);
                }
        } finally {
            pasarela.lock.unlock();
        }
    }
        g.drawImage(offscreen, 0, 0, this);
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }

}
