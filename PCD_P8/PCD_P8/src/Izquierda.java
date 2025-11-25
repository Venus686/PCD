
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 **Clase que representa las personas que quieren cruzar la pasarela por la izquierda
 * El tiempo que estarán en ella es entre 4 y 6 segundos
 * @author Lucía Zamudio
 */
public class Izquierda implements Runnable {

    Pasarela p;
    Random rand;
    int id;
    
    public Izquierda(Pasarela pas, int id) {
        p= pas;
        rand= new Random(System.currentTimeMillis());
        this.id=id;
    }

    public int getIdI() {
        return id;
    }

    @Override
    public void run() {
        try{
            p.entraIzquierda(id);
            try{
                Thread.sleep(rand.nextInt(2000)+4000);
            }catch(InterruptedException e){
                System.out.println("Error"+ e.getMessage());
            }
            p.saleIzquierda();
        }catch(Exception e){
            System.out.println("Error"+ e.getMessage());
        }
    }   
}
