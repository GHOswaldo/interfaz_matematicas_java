/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trigonometria;


/**
 *
 * @author OMCG1
 */
import Intefaz.MenuPrincipal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TrianguloInteractivoT extends JPanel {
    private int[] x = new int[3];
    private int[] y = new int[3];
    private double[] angulos = new double[3];
    private JLabel sumLabel;
    private int dragPoint = -1;

    public TrianguloInteractivoT() {
        setPreferredSize(new Dimension(500, 450));
        setLayout(new BorderLayout());
        

        JPanel drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setColor(Color.BLUE);
                g2d.drawLine(x[0], y[0], x[1], y[1]);
                g2d.drawLine(x[1], y[1], x[2], y[2]);
                g2d.drawLine(x[2], y[2], x[0], y[0]);

                for (int i = 0; i < 3; i++) {
                    g2d.setColor(Color.RED);
                    g2d.fillOval(x[i] - 3, y[i] - 3, 6, 6);
                    g2d.setColor(Color.BLACK);
                    g2d.drawString("Ángulo " + (i + 1) + ": " + String.format("%.2f", angulos[i]), x[i] + 10, y[i] - 10);
                }
            }
        };
        add(drawPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sumLabel = new JLabel("Suma de los ángulos internos: ");
        JTextField sumTextField = new JTextField(10);
        sumTextField.setEditable(false);
        JButton menuButton = new JButton("Volver al menú");
        controlPanel.add(sumLabel);
        controlPanel.add(sumTextField);
        controlPanel.add(menuButton);
        add(controlPanel, BorderLayout.NORTH);

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal obj = new MenuPrincipal();
                obj.setVisible(true);
                JFrame topLevelFrame = (JFrame) SwingUtilities.getWindowAncestor(TrianguloInteractivoT.this);
                topLevelFrame.dispose();
                topLevelFrame.setLocationRelativeTo(null);
            }
        });

        JLabel infoLabel = new JLabel("<html>En un triángulo, cada vez que agregas un ángulo, también aumentas el número total de grados en la figura. Como hay tres ángulos en un triángulo, cada uno contribuye a la suma total de grados. Dado que la suma total de los ángulos en una figura plana es siempre 180 grados, y un triángulo es una figura plana, la suma de sus ángulos internos también es 180 grados. Es una regla de correspondencia.</html>");
        add(infoLabel, BorderLayout.SOUTH);

        drawPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                for (int i = 0; i < 3; i++) {
                    if (Math.abs(mouseX - x[i]) <= 5 && Math.abs(mouseY - y[i]) <= 5) {
                        dragPoint = i;
                        break;
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                dragPoint = -1;
                updateAngles();
                updateSumTextField(sumTextField);
                drawPanel.repaint();
            }
        });

        drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                if (dragPoint != -1) {
                    x[dragPoint] = mouseX;
                    y[dragPoint] = mouseY;
                    updateAngles();
                    updateSumTextField(sumTextField);
                    drawPanel.repaint();
                }
            }
        });

        x[0] = 100;
        y[0] = 300;
        x[1] = 300;
        y[1] = 300;
        x[2] = 200;
        y[2] = 100;
        updateAngles();
        updateSumTextField(sumTextField);
    }

    private void updateAngles() {
        for (int i = 0; i < 3; i++) {
            int dx1 = x[(i + 1) % 3] - x[i];
            int dy1 = y[(i + 1) % 3] - y[i];
            int dx2 = x[(i + 2) % 3] - x[i];
            int dy2 = y[(i + 2) % 3] - y[i];
            double dotProduct = dx1 * dx2 + dy1 * dy2;
            double len1 = Math.sqrt(dx1 * dx1 + dy1 * dy1);
            double len2 = Math.sqrt(dx2 * dx2 + dy2 * dy2);
            angulos[i] = Math.acos(dotProduct / (len1 * len2)) * 180 / Math.PI;
        }
    }

    private void updateSumTextField(JTextField textField) {
        double sum = angulos[0] + angulos[1] + angulos[2];
        textField.setText(String.format("%.2f", sum));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setResizable(false);
            frame.setTitle("Triángulo interactivo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new TrianguloInteractivoT());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}