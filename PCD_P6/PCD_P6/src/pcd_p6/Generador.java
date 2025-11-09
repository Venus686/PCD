/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_p6;

import Interfaz.Canva;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Lucía Zamudio
 */
public class Generador {

    Semaphore preadmision;
    Semaphore descarga;
    Semaphore documentacion;
    public static int[] puestos;
    private Object[] vehiculosEnPuestos = new Object[3];
    private List<Object> vehiculosEspera = new ArrayList();

    private Canva canvas;

    public Generador(Canva canvas) {
        this.canvas = canvas;
        puestos = new int[3];
        preadmision = new Semaphore(1);
        descarga = new Semaphore(1);
        documentacion = new Semaphore(1);
    }

    public synchronized void setVehiculoEnPuesto(int puesto, Object vehiculo) {
        if (vehiculo != null) {
            vehiculosEspera.remove(vehiculo);
        }
        vehiculosEnPuestos[puesto] = vehiculo;
        canvas.repaint();
    }

    public synchronized Object getVehiculoEnPuesto(int puesto) {
        return vehiculosEnPuestos[puesto];
    }

    public synchronized void addVehiculoEspera(Object vehiculo) {
        boolean enPuesto = false;
        for (int i = 0; i < 3; i++) {
            if (vehiculosEnPuestos[i] == vehiculo) {
                enPuesto = true;
                break;
            }
        }
        if (!enPuesto && !vehiculosEspera.contains(vehiculo)) {
            vehiculosEspera.add(vehiculo);
            if (canvas != null) {
                canvas.repaint();
            }
        }
    }

    public synchronized void removeVehiculoEspera(Object vehiculo) {
        vehiculosEspera.remove(vehiculo);
        canvas.repaint();
    }

    public synchronized List<Object> getVehiculosEspera() {
        return new ArrayList<>(vehiculosEspera);
    }

}
