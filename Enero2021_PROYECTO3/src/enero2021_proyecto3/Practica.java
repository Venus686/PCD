/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enero2021_proyecto3;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author leonl
 */
public class Practica implements Callable<Long> {
    private int idP;
    private Revision re;
    Random rand;
    
    public int getIdP(){
        return idP;
    }
    public Practica(int id, Revision re){
        this.re=re;
        this.idP= id;
        rand = new Random(System.currentTimeMillis());
    }
    
    @Override
    public Long call() throws Exception{
        long t_inicio = System.currentTimeMillis();
        System.out.println("Soy el estudiante de Prácticas"+ Thread.currentThread().getName() + " con id: "+ idP);
        re.entraPracticas(this);
        Thread.sleep(rand.nextInt(3000)+2000);
        re.salePracticas(this);
        System.out.println("Fin del hilo: " + Thread.currentThread().getName() + "del estudiante con id: "+ idP);
        long t_final = System.currentTimeMillis();
        long tiempo = t_final-t_inicio;
        return tiempo;
    }
}
