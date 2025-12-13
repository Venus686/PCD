/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enero2021_proyecto3;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author leonl
 */
public class Revision {
    int estudiantes_teoria;
    int estudiantes_practica;
    int profesores_ocupados;
    int practica_esperando;
    Queue<Integer> idsTeoria;
    Queue<Integer> idsPractica;
       
    public Revision(){
        estudiantes_teoria=0;
        estudiantes_practica=0;
        profesores_ocupados=0;
        practica_esperando=0;
        idsTeoria = new LinkedList<>();
        idsPractica = new LinkedList<>();
    }
    
    public synchronized void entraTeoria(Teoria t) throws InterruptedException{
        while(profesores_ocupados>=3 || practica_esperando>0){
            System.out.println("El estudiante con id: "+ t.getIdT() + " está esperando en la cola de Revisión Teoría");
            wait();    
        }
        estudiantes_teoria++;
        profesores_ocupados++;
        idsTeoria.add(t.getIdT());
        System.out.println("El estudiante con id: " + t.getIdT() + " entra en la revisión de Teoria");
    }
    
    public synchronized void saleTeoria(Teoria t){
        estudiantes_teoria--;
        profesores_ocupados--;
        idsTeoria.remove(t.getIdT());
        System.out.println("El estudiante con id: " + t.getIdT() + " sale de la revisión de Teoria");
        notifyAll();
    }
    
    public synchronized void entraPracticas(Practica p) throws InterruptedException{
        boolean esperando = false;
        while(estudiantes_practica>=2 || profesores_ocupados>= 3){
            if (!esperando) {
                practica_esperando++; 
                esperando = true;
            }
            System.out.println("El estudiante con id: "+ p.getIdP() + " está esperando en la cola de revisión de Prácticas");
            wait();
        }
        estudiantes_practica++;
        
        if (esperando) {
             practica_esperando--; 
        }
        profesores_ocupados++;
        idsPractica.add(p.getIdP());
        System.out.println("El estudiante con id: "+p.getIdP()+ " ha entrado en la revisión de Prácticas");
    }
    
    public synchronized void salePracticas(Practica p){
        estudiantes_practica--;
        profesores_ocupados--;
        idsPractica.remove(p.getIdP());
        System.out.println("El estudiante con id: "+ p.getIdP()+ " sale de la revisión de Prácticas");
        notifyAll();
    }
}
