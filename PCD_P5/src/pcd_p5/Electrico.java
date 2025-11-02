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
public class Electrico extends Thread{
    int id;
    Parking parking;
    Random rand= new Random(System.nanoTime());

    public Electrico(int id, Parking parking) {
        this.id = id;
        this.parking = parking;
    }

    public int getIde() {
        return id;
    }
    
    @Override
    public void run(){
        try{
            parking.entraCocheElectrico(this);
            Thread.sleep(rand.nextInt(40000)+2000);
            parking.saleCocheElectrico(this);            
        }catch(InterruptedException e){
            System.out.println("Error del wait");
        }
    }
    
}
