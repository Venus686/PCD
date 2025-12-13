/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enero2021_proyecto3;

import java.util.Random;

/**
 *
 * @author leonl
 */
public class Teoria extends Thread{
    private int idT;
    Revision re;
    Random rand;

    public int getIdT() {
        return idT;
    }
    
    public Teoria(int id, Revision re){
        this.re=re;
        this.idT=id;
        rand= new Random(System.currentTimeMillis());
    }
    
    @Override
    public void run(){
        System.out.println("Soy el estudiante "+ Thread.currentThread().getName()+ " con id: " + idT);
        try {
            re.entraTeoria(this);
            Thread.sleep(rand.nextInt(3000)+2000);
            re.saleTeoria(this);
            System.out.println("Fin del hilo "+ Thread.currentThread().getName()+ " con id: "+ idT);
        } catch (InterruptedException ex) {
            System.getLogger(Teoria.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
