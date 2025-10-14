/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_p3;

/**
 *
 * @author Lucía Zamudio
 */
import java.util.ArrayList;
import java.util.List;

public class PilaLenta implements IPila {

    private int cima = -1;
    private int capacidad;
    private int numelementos;
    private Object[] datos;
    private CanvasPila canvas;

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

    @Override
    public synchronized void Apila(Object elemento) throws Exception {
        if (this.numelementos == this.capacidad) {
            this.canvas.avisa("PILA LLENA");
            try {
                Thread.sleep(500); // 100ms es suficiente para que el repintado se inicie.
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            throw new Exception("La pila esta llena");
        } else {
            this.datos[++this.cima] = elemento;
            ++this.numelementos;
            this.canvas.avisa("");
            this.actualizarCanvas();
        }
    }

    @Override
    public synchronized Object Desapila() throws Exception {
        if (this.pilavacia()) {
            this.canvas.avisa("PILA VACIA");
            try {
                Thread.sleep(500); // Medio segundo para que sea visible
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            throw new Exception("La pila no contiene ningun elemento");
        } else {
            Object elemento = this.datos[this.cima];
            --this.cima;
            --this.numelementos;
            this.canvas.avisa("");
            this.actualizarCanvas();
            return elemento;
        }
    }

    @Override
    public Object Primero() throws Exception {
        if (this.pilavacia()) {
            throw new Exception("La pila no contiene ningun elemento");
        } else {
            return this.datos[this.cima];
        }
    }

    public boolean pilavacia() {
        return this.numelementos == 0;
    }

    public boolean pilallena() {
        return this.numelementos == this.capacidad;
    }

    private void actualizarCanvas() {
        if (this.canvas != null) {
            List<Object> lista = new ArrayList();

            for (int i = 0; i <= this.cima; ++i) {
                lista.add(this.datos[i]);
            }

            this.canvas.representa(lista);
        }

    }
}
