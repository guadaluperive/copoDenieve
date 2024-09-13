package copodenieve;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

/**
 * Copo de Nieve de Koch - Adaptación como aplicación de escritorio con Swing.
 * Dibuja los tres lados de un triángulo equilátero con curvas de Koch.
 * 
 * @author José Juan Aliaga
 */
public class CopoDenieve extends JPanel {

    // Coordenadas iniciales del triángulo equilátero
    double xp1 = 200, yp1 = 50; // Primer vértice
    double xp2 = 50, yp2 = 300; // Segundo vértice
    double xp3 = 350, yp3 = 300; // Tercer vértice

    // Cálculo preciso de sin(60°)
    double sin60 = Math.sin(Math.PI / 3);

    // Nivel de recursividad
    int nivel_de_recursividad = 5;

    // Método paint para dibujar
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar cada lado del triángulo equilátero con la curva de Koch
        paintRecursivo(g, nivel_de_recursividad, xp1, yp1, xp2, yp2); // Lado 1
        paintRecursivo(g, nivel_de_recursividad, xp2, yp2, xp3, yp3); // Lado 2
        paintRecursivo(g, nivel_de_recursividad, xp3, yp3, xp1, yp1); // Lado 3
    }

    // Método recursivo para dibujar la curva de Koch
    private void paintRecursivo(Graphics g, int i, double xp12, double yp12, double xp22, double yp22) {
        // Cálculo de desplazamientos en x e y
        double dx = (xp22 - xp12) / 3.0;
        double dy = (yp22 - yp12) / 3.0;

        // Cálculo del nuevo punto intermedio para formar el triángulo equilátero
        double xx = xp12 + 1.5 * dx - dy * sin60;
        double yy = yp12 + 1.5 * dy + dx * sin60;

        if (i <= 0) {
            // Dibujar una línea si el nivel de recursividad es 0
            g.drawLine((int) xp12, (int) yp12, (int) xp22, (int) yp22);
        } else {
            // Llamadas recursivas para cada uno de los nuevos segmentos
            paintRecursivo(g, i - 1, xp12, yp12, xp12 + dx, yp12 + dy); // Primer segmento
            paintRecursivo(g, i - 1, xp12 + dx, yp12 + dy, xx, yy); // Segmento con la "punta"
            paintRecursivo(g, i - 1, xx, yy, xp22 - dx, yp22 - dy); // Segundo segmento
            paintRecursivo(g, i - 1, xp22 - dx, yp22 - dy, xp22, yp22); // Tercer segmento
        }
    }

    // Método principal para ejecutar la aplicación como JFrame
    public static void main(String[] args) {
        // Crear un nuevo frame
        JFrame frame = new JFrame("Copo de Nieve de Koch");
        CopoDenieve panel = new CopoDenieve();

        // Configurar el frame
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel); // Añadir el panel de dibujo al frame
        frame.setVisible(true); // Mostrar el frame
    }
}