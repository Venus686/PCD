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
public class Teoria extends Thread{
    private int idT;
    Random rand;
    private Revision revision;
    
    public Teoria(int id, Revision re){
        rand = new Random(System.currentTimeMillis());
        this.idT= idT;
        this.revision= re;
    }

    public int getIdT() {
        return idT;
    }
    
    @Override
    public void run(){
        System.out.println("Soy el estudiante "+ Thread.currentThread().getName()+ " con id: " + idT);
        try {
            revision.entraTeoria(this);
            Thread.sleep(rand.nextInt(3000)+2000);
            revision.SaleTeoria(this);
             System.out.println("Fin del hilo "+ Thread.currentThread().getName()+ " con id: "+ idT);
        } catch (InterruptedException ex) {
            System.getLogger(Teoria.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
}
