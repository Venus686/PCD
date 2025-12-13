/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enero2021_reentracklock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author leonl
 */
public class Revision {
    ReentrantLock lock;
    int max_profesores;
    int num_practicas;
    int espera_practicas;
    Condition entra_teoria;
    Condition entra_practicas;
    
    Queue<Teoria> teoria = new LinkedList(); 
    Queue<Practicas> practica = new LinkedList();
    Queue<Teoria> espera_teoria = new LinkedList();
    Queue<Practicas> espera_practica = new LinkedList();
    
    public Revision(){
        lock = new ReentrantLock();
        espera_practicas= 0;
        entra_teoria = lock.newCondition();
        entra_practicas = lock.newCondition();
        max_profesores=0;
        num_practicas=0;
    }
    public void entraTeoria(Teoria t) throws InterruptedException{
        lock.lock();
        try{
            while (max_profesores>= 3 || espera_practicas >0){
                System.out.println("El hilo: "+ Thread.currentThread().getName()+ "Estudiante con id: "+ t.getIdT() + " entra a la espera de Teoria");
                espera_teoria.add(t);
                entra_teoria.await();
            }
            espera_teoria.remove(t);
            System.out.println("El hilo: "+ Thread.currentThread().getName()+ "Estudiante con id: "+ t.getIdT() + " entra a la revisión de Teoria");
            max_profesores++;
            teoria.add(t);
            
        }finally{
            lock.unlock();
        }
    }
    
    public void SaleTeoria(Teoria t){
        lock.lock();
        try{
            teoria.remove(t);
            max_profesores--;
            System.out.println("El hilo: "+ Thread.currentThread().getName()+ "Estudiante con id: "+ t.getIdT() + " sale de la revisión de Teoria");
            
            if(espera_practicas>0 && num_practicas<2){
                entra_practicas.signal();
            }else{
                entra_teoria.signal();
            }
            
        }finally{
            lock.unlock();
        }
    }
    
    public void entraPracticas(Practicas p) throws InterruptedException{
        boolean esperando= false;
        lock.lock();
        try{
            while (max_profesores>= 3 || num_practicas>=2 ){
                if(!esperando){
                    espera_practicas++;
                    espera_practica.add(p);
                    esperando=true;
                }
                System.out.println("El hilo: "+ Thread.currentThread().getName()+ "Estudiante con id: "+ p.getIdP()+ " entra a la espera de Practicas");
                entra_practicas.await();
            }
            if(esperando){
                espera_practicas--;
                espera_practica.remove(p);
            }
            num_practicas++; 
            System.out.println("El hilo: "+ Thread.currentThread().getName()+ "Estudiante con id: "+ p.getIdP()+ " entra a la revisión de Practicas");
            max_profesores++;
            practica.add(p);
            
        }finally{
            lock.unlock();
        }
    }
    
    public void SalePracticas(Practicas p){
        lock.lock();
        try{
            if (num_practicas>0){
                num_practicas--;
            }
            practica.remove(p);
            max_profesores--;
            System.out.println("El hilo: "+ Thread.currentThread().getName()+ "Estudiante con id: "+ p.getIdP() + " sale de la revisión de Prácticas");
            if(espera_practicas>0 && num_practicas<2){
                entra_practicas.signal();
            }else{
                entra_teoria.signal();
            }
        }finally{
            lock.unlock();
        }
    }
}
