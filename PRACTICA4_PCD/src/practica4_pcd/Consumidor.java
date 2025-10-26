/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4_pcd;

import java.util.Random;

/**
 *
 * @author Lucía Zamudio
 */
public class Consumidor implements Runnable {

    private PilaLenta pila;

    public Consumidor(PilaLenta pila) {
        this.pila = pila;
    }

    @Override
    public void run() {
        Random rand = new Random();
        for (int i = 0; i < 15; ++i) {
            try {
                Object num = this.pila.Desapila();
                System.out.println("Desapila: " + String.valueOf(num));
                int tiempo = 1000 * (1 + rand.nextInt(3));
                Thread.sleep(tiempo);
            } catch (Exception e) {
                System.out.println("Consumidor detenido: " + e.getMessage());
                return;
            }
        }
        System.out.println("Consumidor ha terminado - Se detienen los productores...");
        pila.terminar();  
    }
}
