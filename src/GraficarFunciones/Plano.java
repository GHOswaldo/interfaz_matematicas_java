/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraficarFunciones;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author OMCG1
 */
public class Plano {

    private JPanel panela;
    private int ancho;
    private int alto;
    
    private int escala = 10;

    public Plano(JPanel panela, int escala) {
        this.escala = escala;
        this.panela = panela;
        ancho = panela.getWidth();
        alto = panela.getHeight();

    }

    public void Dibujar() {
       
        Graphics2D graficando = (Graphics2D) panela.getGraphics();
        graficando.clearRect(0, 0, ancho, alto);
        
        graficando.drawLine(ancho / 2, 0, ancho / 2, alto);
        graficando.drawLine(0, alto / 2, ancho, alto / 2);

        int i2 = ancho / 2;
        for (int i = ancho / 2; i < ancho; i += escala) {
            graficando.setColor(Color.gray);
            graficando.drawLine(i, 0, i, alto);
            graficando.drawLine(i2, 0, i2, alto);
            i2 = i2 - escala;
        }

        int j2 = alto / 2;
        for (int j = alto / 2; j < alto; j += escala) {
            graficando.setColor(Color.gray);
            graficando.drawLine(0, j, ancho, j);
            graficando.drawLine(0, j2, ancho, j2);
            j2 = j2 - escala;
        }
        graficando.setColor(Color.red);
        graficando.drawLine(ancho / 2, 0, ancho / 2, alto);
        graficando.drawLine(0, alto / 2, ancho, alto / 2);
    }

    public void DibujarPunto(float x, float y) {
        Graphics2D graficando = (Graphics2D) panela.getGraphics();
        int xDigital = (int) (ancho / 2 + x * escala);
        int yDigital = (int) (alto / 2 - y * escala);
        graficando.fillOval(xDigital - 3, yDigital - 3, 6, 6);
    }

}
