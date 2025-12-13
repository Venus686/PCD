/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_enero2021;

import java.util.Random;
import javax.sound.midi.SysexMessage;

/**
 * Representará a cada uno de los estudiantes que revisarán la
 teoría mediante un hilo. El hilo se creará heredando de la clase Thread. El
 estudiante pondrá un mensaje de inicio, revisará un tiempo aleatorio entre
 2 y 5 segundos y pondrá un mensaje de finalización al acabar
 * @author Lucia
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
