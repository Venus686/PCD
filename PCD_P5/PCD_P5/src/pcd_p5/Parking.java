/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_p5;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Lucía zamudio 
 */
public class Parking {
    int aparcados=0;
    int combustion_aparcados=0;
    int num_plazas=6;
    int electricos_esperando=0;
    Queue<Electrico> colaElectricos = new LinkedList<>();
    Queue<Combustion> colaCombustion = new LinkedList<>();
    List<Object> parking = new ArrayList<>();
    private Interfaz.Canva canvas;

    public int getAparcados() {
        return aparcados;
    }

    public int getCombustion_aparcados() {
        return combustion_aparcados;
    }

    public int getNum_plazas() {
        return num_plazas;
    }

    public int getElectricos_esperando() {
        return electricos_esperando;
    }
    public Queue<Electrico> getColaElectricos() {
        return new LinkedList<>(colaElectricos); 
    }

    public Queue<Combustion> getColaCombustion() {
        return colaCombustion;
    }

    public List<Object> getParking() {
        return parking;
    }
    
    public void setCanvas(Interfaz.Canva c) {
        this.canvas = c;
    }
    
    public synchronized void entraCocheElectrico(Electrico elec) throws InterruptedException {
        colaElectricos.add(elec);
        electricos_esperando++;
        canvas.agregaCocheElec(elec);
        while(aparcados==num_plazas || colaElectricos.peek() != elec){
            wait();
        }
        aparcados++;
        electricos_esperando--; 
        colaElectricos.remove(elec);
        canvas.eliminaCocheElec(elec);
        parking.add(elec);
        canvas.agregaParking(elec);
    }
    
    public synchronized void saleCocheElectrico(Electrico elec){
        if(aparcados>0) aparcados--;
        parking.remove(elec);
        canvas.eliminaParking(elec);
        notifyAll();
    }
    
    public synchronized void entraCocheCombustion(Combustion combus) throws InterruptedException{
       colaCombustion.add(combus);
       canvas.agregaCocheCom(combus);
       while(aparcados==num_plazas || (combustion_aparcados>=2 && electricos_esperando>=1)|| colaCombustion.peek() != combus){ 
           wait();
        }
        aparcados++;
        combustion_aparcados++;
        colaCombustion.remove(combus);
        canvas.eliminaCocheCom(combus);
        parking.add(combus);
        canvas.agregaParking(combus);
    }
    
     public synchronized void saleCocheCombustion(Combustion combus){
        if(aparcados>0){
            aparcados--;
            combustion_aparcados--;
        }
        parking.remove(combus);
        canvas.eliminaParking(combus);
        notifyAll();
        
    }
}
