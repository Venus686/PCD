/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_p3;

import java.awt.Color;

/**
 *
 * @author Lucía Zamudio
 */
public class Consumidor implements Runnable {
    private PilaLenta pila;
   // private CanvasPila canvas;

    public Consumidor(PilaLenta pila) {
        this.pila = pila;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; ++i) {
            try {
                Object num = this.pila.Desapila();
                System.out.println("Desapila: " + String.valueOf(num));
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
}
