/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enero2021_reentracklock;

import java.util.Random;

/**
 *
 * @author leonl
 */
public class Practicas implements Runnable{
    private int idP;
    Random rand;
    private Revision re;

    public int getIdP() {
        return idP;
    }

    public Practicas(int idP, Revision re) {
        this.idP = idP;
        this.re = re;
        rand = new Random(System.currentTimeMillis());
    }
    
    @Override
    public void run(){
        System.out.println("Soy el estudiante de Prácticas"+ Thread.currentThread().getName() + " con id: "+ idP);
        try {
            re.entraPracticas(this);
            Thread.sleep(rand.nextInt(3000)+2000);
            re.SalePracticas(this);
            System.out.println("Fin del hilo: " + Thread.currentThread().getName() + "del estudiante con id: "+ idP);
        } catch (InterruptedException ex) {
            System.getLogger(Practicas.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }   
        
    }
    
}
