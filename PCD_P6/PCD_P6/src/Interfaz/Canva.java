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
import java.util.List;
import javax.swing.ImageIcon;
import pcd_p6.Camion;
import pcd_p6.Generador;
import pcd_p6.Tractor;

/**
 *
 * @author Lucia Zamudio
 */
public class Canva extends Canvas {

    int ancho = 0;
    int alto = 0;
    Image viñedo, camion, camion1, camionArriba, camion1Arriba, tractor, tractorArriba,tractor1, tractor1Arriba;
    Image offscreen;
    public Generador gen;

    public Canva(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.setSize(ancho, alto);
        this.setBackground(Color.GREEN);
        viñedo = new ImageIcon(getClass().getResource("/Interfaz/Imagenes/viñedo.png")).getImage();
        camion = new ImageIcon(getClass().getResource("/Interfaz/Imagenes/Camion1.png")).getImage();
        camion1 = new ImageIcon(getClass().getResource("/Interfaz/Imagenes/Camion2.png")).getImage();
        camionArriba = new ImageIcon(getClass().getResource("/Interfaz/Imagenes/Camion1DesdeArriba.png")).getImage();
        camion1Arriba = new ImageIcon(getClass().getResource("/Interfaz/Imagenes/Camion2DesdeArriba.png")).getImage();
        tractor = new ImageIcon(getClass().getResource("/Interfaz/Imagenes/Tractor1.png")).getImage();
        tractorArriba = new ImageIcon(getClass().getResource("/Interfaz/Imagenes/Tractor1DesdeArriba.png")).getImage();
        tractor1= new ImageIcon(getClass().getResource("/Interfaz/Imagenes/Tractor2.png")).getImage();
        tractor1Arriba = new ImageIcon(getClass().getResource("/Interfaz/Imagenes/Tractor2DesdeArriba.png")).getImage();
        MediaTracker dibu = new MediaTracker(this);
        dibu.addImage(viñedo, 0);
        dibu.addImage(camion, 1);
        dibu.addImage(camion1, 2);
        dibu.addImage(camionArriba, 3);
        dibu.addImage(camion1Arriba, 4);
        try {
            dibu.waitForAll();
        } catch (InterruptedException ex) {
            System.getLogger(Canva.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();
        og.drawImage(viñedo, 0, 0, getWidth(), getHeight(), this);

        og.setColor(new Color(255, 255, 200));
        og.fillRect(240, 655, 185, 35);
        og.fillRect(440, 655, 125, 35);
        og.fillRect(580, 655, 160, 35);

        og.setFont(new Font("Segoe UI", Font.BOLD, 20));
        og.setColor(Color.BLACK);
        og.drawString("PREADMISION", 590, 680);
        og.drawString("DESCARGA", 450, 680);
        og.drawString("DOCUMENTACIÓN", 245, 680);

       

        og.setFont(new Font("Segoe UI", Font.BOLD, 16));
        Object pread = gen.getVehiculoEnPuesto(0);  
        Object desc = gen.getVehiculoEnPuesto(1);   
        Object doc = gen.getVehiculoEnPuesto(2);
        
        int tamXCamion=150;
        int tamYCamion=100;
        int tamXTractor=350;
        int tamYTractor=150;
        og.setColor(Color.WHITE);

        if (pread instanceof Camion) {
            int id=((Camion) pread).getIdC();
            if(id%2==0){
                og.drawImage(camion, 580, 550, tamXCamion, tamYCamion, this);
            }else{
                og.drawImage(camion1, 580, 550, tamXCamion, tamYCamion, this);
            }
            og.drawString("C" + id, 625, 650);

        } else if (pread instanceof Tractor) {
            int id=((Tractor) pread).getIdT();
            if (id%2==0){
                og.drawImage(tractor, 580, 520, tamXTractor, tamYTractor, this);
            }else{
                og.drawImage(tractor1, 580, 500, 320, tamYTractor, this);
            }
            og.drawString("T" + id, 625, 650);
        }
        
        if (desc instanceof Camion) {
            int id=((Camion) desc).getIdC();
            if(id%2==0){
                og.drawImage(camion, 420, 550, tamXCamion, tamYCamion, this);
            }else{
                og.drawImage(camion1, 420, 550, tamXCamion, tamYCamion, this);
            }
            og.drawString("C" + id, 475, 650);
        } else if (desc instanceof Tractor) {
            int id= ((Tractor) desc).getIdT();
            if(id%2==0){
                og.drawImage(tractor, 420, 520, tamXTractor, tamYTractor, this);
            }else{
                og.drawImage(tractor1, 420, 500, 320, tamYTractor, this);
            }
            og.drawString("T" + id, 475, 650);
        }
        
        if (doc instanceof Camion) {
            int id=((Camion) doc).getIdC();
            if(id%2==0){
                og.drawImage(camion, 250, 550, tamXCamion, tamYCamion, this);
             }else{
                og.drawImage(camion1, 250, 550, tamXCamion, tamYCamion, this);
            }
            og.drawString("C" + id, 295, 650);
        } else if (doc instanceof Tractor) {
            int id= ((Tractor) doc).getIdT();
            if(id%2==0){
                og.drawImage(tractor, 250, 520, tamXTractor, tamYTractor, this);
            }else{
                og.drawImage(tractor1, 250, 500, 320, tamYTractor, this);
            }
            og.drawString("T" + id, 295, 650);
        }
        int yCamionEspera = 460;
        int yTractorEspera = 420;
        og.setColor(Color.BLACK);
        
        for (Object vehiculo : gen.getVehiculosEspera()) {
            if (vehiculo instanceof Camion) {
                int id= ((Camion) vehiculo).getIdC();
                if(id%2==0){
                   og.drawImage(camionArriba, 430, yCamionEspera, 80, 80, this);
                }else{
                    og.drawImage(camion1Arriba, 440, yCamionEspera, 60, 60, this);
                }              
                og.drawString("C" + id , 455, yCamionEspera +35);
                yCamionEspera -= 70;
            } else if (vehiculo instanceof Tractor) {
                int id=((Tractor)vehiculo).getIdT();
                if(id%2==0){
                    og.drawImage(tractorArriba, 340, yTractorEspera, 120, 120, this);
                }else{
                    og.drawImage(tractor1Arriba, 340, yTractorEspera, 120, 120, this);
                }
                og.drawString("T" + id, 390, yTractorEspera + 50);
                yTractorEspera -= 100;
            }
        }
        
        g.drawImage(offscreen, 0, 0, this);
    }

    void setGenerador(Generador gen) {
        this.gen = gen;
    }
}
