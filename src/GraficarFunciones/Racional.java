/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraficarFunciones;

/**
 *
 * @author OMCG1
 */
public class Racional {

    public static void Dibujar(Plano panela) {
        for (float x = -10; x < 10; x += 0.001) {
            float y = (float) 1/x;
            panela.DibujarPunto(x, y);

        }

    }

}
