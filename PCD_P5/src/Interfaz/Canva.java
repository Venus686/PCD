/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.ImageIcon;
import pcd_p5.Combustion;
import pcd_p5.Electrico;
import pcd_p5.Parking;

/**
 *
 * @author Lucía Zamudio
 */
public class Canva extends Canvas {
    
    private Parking parking;
    private Image cocheElectrico;
    private Image cocheCombustion;
    private Image supermercado;
    private Image offscreen;
    
    int ancho;
    int alto;
    
    private Queue<Electrico> colaElectrico = new LinkedList<>();
    private Queue<Combustion> colaCombustion = new LinkedList<>();
    private List<Object> parkingVisible = new ArrayList<>(); 
    
    public Canva(int ancho, int alto) {
        this.ancho=ancho;
        this.alto=alto;
        this.setSize(ancho,alto);
        this.setBackground(new Color(143, 180, 188));
        supermercado= new ImageIcon(getClass().getResource("/Interfaz/super.jpg")).getImage();
        cocheElectrico = new ImageIcon(getClass().getResource("Coche_electrico.png")).getImage();
        cocheCombustion = new ImageIcon(getClass().getResource("Coche_combustion.png")).getImage();
        MediaTracker dibu= new MediaTracker(this);
        dibu.addImage( cocheElectrico,0);
        dibu.addImage(cocheCombustion, 1);
        dibu.addImage(supermercado,2);
        try {
            dibu.waitForAll();
        } catch (InterruptedException ex) {
            System.getLogger(Canva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
     
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    @Override
    public void paint(Graphics g){
        if (parking == null) {
            return;
        }
        
        offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();
        
        og.drawImage(supermercado, 0 ,0,425,350,this);
        og.setColor(Color.BLACK);
        og.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        og.drawString("COLA DE COCHES ELECTRICOS", 480, 60);
        
   
        int ex = 480, ey = 80;
        for (Electrico e : colaElectrico) {
            og.drawImage(cocheElectrico, ex, ey, 70, 110, this);
            og.setColor(new Color(100, 19, 186));
            og.drawString("E" + e.getIde(), ex + 18, ey + 120);
            ex += 80;
        }
        
        og.setColor(Color.BLACK);
        og.drawString("COLA DE COCHES COMBUSTION", 480, 250);
        int cx = 480, cy = 270;
        for (Combustion c : colaCombustion) {
            og.drawImage(cocheCombustion, cx, cy, 70, 110, this);
            og.setColor(new Color(176, 39, 82));
            og.drawString("C" + c.getIdc(), cx + 18, cy + 120);
            cx += 80;
        }
        
    
    int plazaAncho = 70;
    int plazaAlto = 110;

    og.setColor(new Color(128, 0, 128, 180));
    for (int i = 0; i < 6; i++) {
        int px = 50 + i * (plazaAncho + 10);
        int py = 420;
        og.fillRect(px, py, plazaAncho, plazaAlto);
        og.setColor(Color.BLACK);
        og.drawRect(px, py, plazaAncho, plazaAlto);
        og.setColor(new Color(128, 0, 128, 180));
    }
    
    
        og.setColor(Color.BLACK);
        og.drawString("PARKING", 50, 400);
        int px = 50, py = 420;

        for (Object o : parking.getParking()) {
            if (o instanceof Electrico) {
                og.drawImage(cocheElectrico, px, py, 70, 110, this);
                og.setColor(new Color(100, 19, 186));
                og.drawString("E" + ((Electrico) o).getIde(), px + 20, py + 125);
            }
            else if (o instanceof Combustion) {
                og.drawImage(cocheCombustion, px, py, 70, 110, this);
                og.setColor(new Color(176, 39, 82));
                og.drawString("C" + ((Combustion) o).getIdc(), px + 20, py + 125);
            }
            px += 80;
        }     
        g.drawImage(offscreen, 0, 0, this);

    }
    
    
    public void agregaCocheElec(Electrico e) {
        colaElectrico.add(e);
        repaint();
    }

    public void eliminaCocheElec(Electrico e) {
        colaElectrico.remove(e);
        repaint();
    }
    
     public void agregaCocheCom(Combustion com) {
        colaCombustion.add(com);
        repaint();
    }

    public void eliminaCocheCom(Combustion c) {
        colaCombustion.remove(c);
        repaint();
    }

    public void agregaParking(Object coche) {
        parkingVisible.add(coche);
        repaint();
    }

    public void eliminaParking(Object coche) {
        parkingVisible.remove(coche);
        repaint();
    }
     
}
