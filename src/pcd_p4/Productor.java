/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_p4;

/**
 *
 * @author leonl
 */
import java.util.Random;

public class Productor extends Thread {
    private PilaLenta pila;

    public Productor(PilaLenta pila) {
        this.pila = pila;
    }

    @Override
    public void run() {
        Random rand = new Random();

        for(int i = 0; i < 10; ++i) {
            int num_alea = rand.nextInt(100);

            try {
                this.pila.Apila(num_alea);
                System.out.println("Se apila el: " + num_alea);
                Thread.sleep(1000L);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
}
