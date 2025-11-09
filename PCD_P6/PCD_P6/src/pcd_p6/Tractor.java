/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_p6;

import java.util.Random;

/**
 *
 * @author Lucia Zamudio
 */
public class Tractor implements Runnable {

    int id;
    Generador gen;
    Random rand;
    private Interfaz.Canva canvas;

    public Tractor(int id, Generador gen) {
        this.id = id;
        this.gen = gen;
        this.rand = new Random(System.nanoTime());
    }

    public Tractor(int id, Generador gen, Interfaz.Canva canvas) {
        this.id = id;
        this.gen = gen;
        this.canvas = canvas;
        this.rand = new Random(System.nanoTime());
    }

    public int getIdT() {
        return id;
    }

    @Override
    public void run(){
        try{
            gen.preadmision.acquire();
            gen.setVehiculoEnPuesto(0, this);
            gen.removeVehiculoEspera(this); 
            Thread.sleep(rand.nextInt(1000)+2000);
            
            gen.descarga.acquire();
            gen.setVehiculoEnPuesto(0, null);
            gen.setVehiculoEnPuesto(1, this);
            Thread.sleep(rand.nextInt(1000)+2000);
            gen.preadmision.release();
            
            gen.documentacion.acquire();
            gen.setVehiculoEnPuesto(1, null);
            gen.setVehiculoEnPuesto(2, this);
            Thread.sleep(rand.nextInt(1000)+2000);
            gen.descarga.release();
            gen.documentacion.release();
            gen.setVehiculoEnPuesto(2, null);
            
        }catch(InterruptedException e){
            System.out.println("Error"+e.getMessage());
        }
    }
}
