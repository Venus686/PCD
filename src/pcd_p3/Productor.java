/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_p3;

/**
 *
 * @author Lucía Zamudio
 */
import java.awt.Color;
import java.util.Random;

public class Productor extends Thread {
    private PilaLenta pila;
    private CanvasPila canvas;

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
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
}
