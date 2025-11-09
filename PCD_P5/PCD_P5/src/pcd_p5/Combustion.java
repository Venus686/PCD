/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_p5;

import java.util.Random;

/**
 *
 * @author Lucía Zamudio
 */
public class Combustion implements Runnable {
    private int id;
    private Parking parking;
    Random rand=new Random(System.nanoTime());

    public Combustion(int id, Parking parking) {
        this.id = id;
        this.parking = parking;
    }

    public int getIdc() {
        return id;
    }    
    
    @Override
    public void run() {
        try{
            parking.entraCocheCombustion(this);
            Thread.sleep(rand.nextInt(40000)+2000);
            parking.saleCocheCombustion(this);
            
        }catch(InterruptedException e){
            System.out.println("Error en el wait");
        }
    }
    
}
