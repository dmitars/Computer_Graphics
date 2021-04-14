package sample;

import javax.swing.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 670;

    private static final int PANEL_HEIGHT = 560;

    private final DrawPanel drawingPanel = new DrawPanel();

    public MainWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);

        drawingPanel.setBounds(0, 100, WIDTH, PANEL_HEIGHT);
        add(drawingPanel);

        JButton stepByStepButton = new JButton("Draw lines");
        stepByStepButton.setBounds(10, 10, 200, 40);
        stepByStepButton.addActionListener(e -> drawingPanel.updateLines(Main.lines));
        add(stepByStepButton);

        JButton drawPolygon = new JButton("Draw polygon");
        drawPolygon.setBounds(240, 10, 200, 40);
        drawPolygon.addActionListener(e -> drawingPanel.updatePolygon(Main.polygon));
        add(drawPolygon);

        JButton CDAButton = new JButton("Draw rectangle");
        CDAButton.setBounds(460, 10, 200, 40);
        CDAButton.addActionListener(e -> drawingPanel.drawRectangle(Main.rectangle));
        add(CDAButton);


        JButton clearButton = new JButton("Clean");
        clearButton.setBounds(700, 10, 200, 40);
        clearButton.addActionListener(e -> {
            drawingPanel.updateLines(new ArrayList<>());
                    drawingPanel.drawRectangle(new ArrayList<>());
                            drawingPanel.updatePolygon(new ArrayList<>());
        });
        add(clearButton);

        setResizable(false);
    }
}
