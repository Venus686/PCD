/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd_p4;

/**
 *
 * @author Lucía Zamudio Cecilia
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class CanvasPila extends Canvas {

    private List<Object> elementos;
    private String mensaje = "";
    // private Image buffer;
    private Image fondo;
    private Color colorPilaLlena = Color.BLACK;
    private Color colorPilaVacia = Color.BLACK;

    public CanvasPila(int ancho, int alto) {
        this.setSize(ancho, alto);
        fondo = new ImageIcon(getClass().getResource("/pcd_p4/fondo.jpg")).getImage();
        this.elementos = new ArrayList<>();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Image offscreen = createImage(getWidth(), getHeight());
        Graphics og = offscreen.getGraphics();

        og.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);

        og.setColor(Color.black);
        og.setFont(new Font("Courier New", Font.BOLD, 36));
        og.drawString("PilaLenta", 200, 100);

        og.setFont(new Font("Arial", Font.BOLD, 15));
        og.setColor(this.colorPilaVacia);
        og.drawString("PILA VACÍA", 60, 500);
        og.setColor(this.colorPilaLlena);
        og.drawString("PILA LLENA", 450, 500);

        og.setColor(new Color(251, 111, 146, 180));
        int anchoRect = 200;
        int altoRect = 40;
        int pixelX = 200;
        int pixelY = getHeight() - 50;
        for (int i = 0; i < 8; i++) {
            int y = pixelY - i * (altoRect + 10);
            og.fillRect(pixelX, y - altoRect, anchoRect, altoRect);
            og.setColor(Color.BLACK);
            og.drawRect(pixelX, y - altoRect, anchoRect, altoRect); // borde negro
            og.setColor(new Color(251, 111, 146, 180));
        }

        // Dibujar elementos de la pila
        og.setFont(new Font("Arial", Font.BOLD, 16));
        og.setColor(Color.black);

        for (int i = 0; i < elementos.size(); i++) {
            int posY = pixelY - i * (altoRect + 10);
            String valor = elementos.get(i).toString();
            int textoAncho = og.getFontMetrics().stringWidth(valor);
            int textoX = pixelX + (anchoRect - textoAncho) / 2;
            int textoY = posY - (altoRect / 2) + 5;
            og.drawString(valor, textoX, textoY);
        }

        if (!mensaje.isEmpty()) {
            if (mensaje.contains("LLENA") || mensaje.contains("VACIA")) {
                og.setColor(Color.PINK);
            } else {
                og.setColor(Color.BLACK);
            }
            og.setFont(new Font("Arial", Font.BOLD, 16));
            og.drawString(mensaje, 100, 30);
        }

        g.drawImage(offscreen, 0, 0, this);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public void avisa(String mensaje) {
        this.mensaje = mensaje;
        if (mensaje.contains("LLENA")) {
            this.colorPilaLlena = Color.PINK;
            this.colorPilaVacia = Color.BLACK;
        } else if (mensaje.contains("VACIA")) {
            this.colorPilaVacia = Color.PINK;
            this.colorPilaLlena = Color.BLACK;
        } else {
            this.colorPilaLlena = Color.BLACK;
            this.colorPilaVacia = Color.BLACK;
        }
        repaint();
    }

    public void representa(List<Object> nuevaPila) {
        this.elementos = new ArrayList<>(nuevaPila);
        repaint();
    }

}
