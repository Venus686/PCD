/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4_pcd;

/**
 *
 * @author Lucía Zamudio
 */
import Interfaz.CanvasPila;
import java.util.Random;

public class Productor extends Thread {

    private PilaLenta pila;
    private CanvasPila canvas;

    public Productor(PilaLenta pila) {
        this.pila = pila;
    }

/**
 *Cada hilo intenta apilarse 15 veces dejando un tiempo aleatorio entre ellos de 1 a 3 segundos 
 */
    @Override
    public void run() {
        Random rand = new Random();

        for (int i = 0; i < 15; ++i) {
            int num_alea = rand.nextInt(100);
            try {
                this.pila.Apila(num_alea);
                System.out.println("Se apila el: " + num_alea);
                int tiempo = 1000 * (1 + rand.nextInt(3));
                Thread.sleep(tiempo);
            } catch (Exception e) {
                System.out.println("Productor con id: "+ this.getId() +" ha finalizado por:" + e.getMessage());
                return;
            }
        }
    }
}
