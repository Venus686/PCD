/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * La clase posee métodos del estado en el que se encuentra la pasarela
 * @author Lucía Zamudio
 */
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Pasarela {
    Lock lock;
    Condition entraIzq, entraDer;
    int cruzaIzq, cruzaDer;
    
    LinkedList<Integer> colaIzq = new LinkedList<>();
    LinkedList<Integer> colaDer = new LinkedList<>();
    
    LinkedList<Integer> cruzandoIzq = new LinkedList<>();
    LinkedList<Integer> cruzandoDer = new LinkedList<>();
    
    Canva canvas;
    public Pasarela(){
       lock= new ReentrantLock();
       entraIzq= lock.newCondition();
       entraDer = lock.newCondition();
       cruzaDer=0;
       cruzaIzq=0;
    }

    public void setCanvas(Canva canvas) {
        this.canvas = canvas;
    }
    
    
    public void entraIzquierda(int idI) throws InterruptedException{
        lock.lock();
        try{    
            colaIzq.add(idI);
            canvas.repaint();
            while(cruzaIzq>=3 || cruzaDer==0 && cruzaIzq>=2){
                entraIzq.await();
            }
            colaIzq.remove((Integer)idI);
            cruzandoIzq.add(idI);
            cruzaIzq++;
            canvas.repaint();
        }finally{
            lock.unlock();
        }   
    }
    
    public void entraDerecha(int idD) throws InterruptedException{
        lock.lock();
        try{
            colaDer.add(idD);
            canvas.repaint();
            while(cruzaDer>=3 || (cruzaDer>=2 && cruzaIzq==0)){
                entraDer.await();
            }
            colaDer.remove((Integer)idD);
            cruzandoDer.add(idD);
            cruzaDer++;
            canvas.repaint();
        }finally{
            lock.unlock();
        }
    }
    
    public void saleIzquierda(){
        lock.lock();
        try{
            if (!cruzandoIzq.isEmpty()) {
                cruzandoIzq.removeFirst();
            }
            cruzaIzq--;
            entraIzq.signal();
            entraDer.signal();
            
            canvas.repaint();
        }finally{
            lock.unlock();
        }
    }
    
    public void saleDerecha(){
        lock.lock();
        try{
             if (!cruzandoDer.isEmpty()) {
                cruzandoDer.removeFirst();
            }
            cruzaDer--;
            entraDer.signal();
            entraIzq.signal();
            canvas.repaint();
        }finally{
            lock.unlock();
        }
    }
}
