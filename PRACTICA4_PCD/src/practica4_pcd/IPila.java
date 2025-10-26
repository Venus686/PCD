/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package practica4_pcd;

/**
 *
 * @author Lucía Zamudio
 */
public interface IPila {
    int GetNum();
    void Apila(Object elemento) throws Exception;
    Object Desapila() throws Exception;
    Object Primero() throws Exception;
}
