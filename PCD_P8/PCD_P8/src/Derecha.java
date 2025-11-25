
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *Clase que representa las personas que quieren cruzar la pasarela por la derecha
 * El tiempo que estarán en ella es entre 4 y 6 segundos
 * @author Lucía Zamudio
 */
public class Derecha extends Thread{
    Pasarela p;
    Random rand;
    int id;
    
    public Derecha(Pasarela p, int id) {
        this.p = p;
        rand= new Random(System.currentTimeMillis());
        this.id=id;
    }

    public int getIdD() {
        return id;
    }
  
    @Override
    public void run() {
        try{
            p.entraDerecha(id);
            try{
                Thread.sleep(rand.nextInt(2000)+4000);
            }catch(InterruptedException e){
                System.out.println("Error del sleep" + e.getMessage());
            }  
            p.saleDerecha();
        }catch(Exception e){
            System.out.println("Error"+ e.getMessage());
        }     
    }
}
