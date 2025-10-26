/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4_pcd;

import Interfaz.CanvasPila;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucía Zamudio
 */
public class PilaLenta implements IPila {

    private int cima = -1;
    private int capacidad;
    private int numelementos;
    private Object[] datos;
    private CanvasPila canvas;
    private boolean detenido = false;//variable para saber si el consumidor ha detenido la ejecución

    public PilaLenta(int capacidad) {
        this.capacidad = capacidad;
        this.numelementos = 0;
        this.datos = new Object[capacidad];
    }

    public void setCanvas(CanvasPila canvas) {
        this.canvas = canvas;
    }

    @Override
    public int GetNum() {
        return this.numelementos;
    }

    /**
     * El método apila un elemento, si después de 3 intentos sigue llena, lanza una excepción
     * @param elemento
     * @throws Exception 
     */
    @Override
    public synchronized void Apila(Object elemento) throws Exception {
        int intentos = 0;
        
        while (pilallena() && !detenido) {
            if (intentos >= 3) {
                throw new Exception("Pila llena tras 3 intentos");
            }
            this.canvas.avisa("PILA LLENA");
            intentos++;
            wait();
        }

        if (detenido) {
            throw new Exception("Ejecución detenida");
        }
        cima++;
        datos[cima] = elemento;
        numelementos++;
        this.canvas.avisa("");
        this.actualizarCanvas();
        notifyAll();
    }

    /**
     * El metodo desapila un elemento, si lleva 3 intentoss detiene la ejecución del programa
     * @return
     * @throws Exception 
     */
    @Override
    public synchronized Object Desapila() throws Exception {
        int intentos = 0;

        while (pilavacia() && !detenido) {
            if (intentos >= 3) {
                detenido = true;  
                notifyAll();  
                throw new Exception("Pila vacia despues de 3 intentos");
            }
            this.canvas.avisa("PILA VACIA");
            intentos++;
            wait();  
        }
        if (detenido) {
            throw new Exception("Ejecucion detenida");
        }

        Object elemento = datos[cima];
        cima--;
        numelementos--;
        this.canvas.avisa("");
        this.actualizarCanvas();
        notifyAll();
        return elemento;
    }

    @Override
    public Object Primero() throws Exception {
        if (this.pilavacia()) {
            throw new Exception("La pila no contiene ningún elemento");
        } else {
            return datos[cima];
        }
    }

    public boolean pilavacia() {
        return this.numelementos == 0;
    }

    public boolean pilallena() {
        return numelementos == capacidad;
    }

    public synchronized void terminar() {
        detenido = true;
        notifyAll(); 
    }

    private void actualizarCanvas() {
        if (canvas != null) {
            List<Object> lista = new ArrayList<>();
            for (int i = 0; i <= cima; i++) {
                lista.add(datos[i]);
            }
            canvas.representa(lista);
        }
    }
}
