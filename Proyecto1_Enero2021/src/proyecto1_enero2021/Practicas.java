/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_enero2021;

import java.util.Random;

/**
 *
 * @author leonl
 */
public class Practicas implements Runnable {
    private int idP;
    Revision re;
    Random rand;
    
    public int getIdP(){
        return idP;
    }
    public Practicas(int id, Revision re){
        this.re=re;
        this.idP= id;
        rand = new Random(System.currentTimeMillis());
    }
    
    @Override
    public void run(){
        System.out.println("Soy el estudiante de Prácticas"+ Thread.currentThread().getName() + " con id: "+ idP);
        try {
            re.entraPracticas(this);
            Thread.sleep(rand.nextInt(3000)+2000);
            re.salePracticas(this);
            System.out.println("Fin del hilo: " + Thread.currentThread().getName() + "del estudiante con id: "+ idP);
        } catch (InterruptedException ex) {
            System.getLogger(Practicas.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }   
    }
}
