/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_p6;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Lucía Zamudio
 */
public class Camion extends Thread {

    int id;
    Generador gen;
    Random rand;
    private Interfaz.Canva canvas;

    public Camion(int id, Generador gen) {
        this.id = id;
        this.gen = gen;
        rand = new Random(System.nanoTime());
    }

    public Camion(int id, Generador gen, Interfaz.Canva canvas) {
        this.id = id;
        this.gen = gen;
        this.canvas = canvas;
        this.rand = new Random(System.nanoTime());
    }

    public int getIdC() {
        return id;
    }


    @Override
    public void run(){
        System.out.println("ID: C"+ id);
        try{          
            gen.preadmision.acquire();
            gen.removeVehiculoEspera(this);
            gen.setVehiculoEnPuesto(0, this);
            Thread.sleep(rand.nextInt(1000)+2000);
            
            
            gen.descarga.acquire();
            gen.preadmision.release();
            gen.setVehiculoEnPuesto(0, null);
            gen.setVehiculoEnPuesto(1, this);
            Thread.sleep(rand.nextInt(1000)+2000);
           
            
            gen.documentacion.acquire();
            gen.descarga.release();
            gen.setVehiculoEnPuesto(1, null);
            gen.setVehiculoEnPuesto(2, this);
            Thread.sleep(rand.nextInt(1000)+2000);
            gen.documentacion.release();
            gen.setVehiculoEnPuesto(2, null);
            
        }catch(InterruptedException e){
            System.out.println("Error" + e.getMessage());
        }       
    } 
}
