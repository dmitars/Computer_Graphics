package sample;

import com.company.DrawPanel;
import com.company.calculators.CalculatorFactory;
import com.company.calculators.CalculatorType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private static final int WIDTH = 560;
    private static final int HEIGHT = 800;

    private static final int INPUT_HEIGHT = 30;
    private static final int INPUT_LABEL_WIDTH = 60;
    private static final int INPUT_TEXTFIELD_WIDTH = 60;


    private static final double PANEL_SCALE_W = 1.0;
    private static final double PANEL_SCALE_H = 0.7;

    private static final Font dataFont = new Font("Serif", Font.BOLD, 18);
    private static final Font errorFont = new Font("Serif", Font.ITALIC, 12);

    private final DrawPanel drawingPanel = new DrawPanel();

    private final JComboBox<CalculatorType>calculatorTypeJComboBox = new JComboBox<>();

    private final JTextField x1Input = new JTextField(21);
    private final JTextField y1Input = new JTextField(21);
    private final JTextField x2Input = new JTextField(21);
    private final JTextField y2Input = new JTextField(21);
    private final JTextField radiusInput = new JTextField(21);;

    private final JLabel timeLabel;
    private final JLabel errorLabel;


    private void doAction() {
        int x1, y1, x2, y2;
        try {
            errorLabel.setText("");
            x1 = Integer.parseInt(x1Input.getText());
            y1 = Integer.parseInt(y1Input.getText());
            x2 = Integer.parseInt(x2Input.getText());
            y2 = Integer.parseInt(y2Input.getText());

            var calculatorType = (CalculatorType)calculatorTypeJComboBox.getSelectedItem();

            var calculator = CalculatorFactory.getCalculator(calculatorType);
            if(CalculatorType.BREZENHEM_CIRCLE.equals(calculatorType)) {
                int r = Integer.parseInt(radiusInput.getText());
                calculator.setAdditionalParameter(r);
            }

            drawingPanel.drawPoints(calculator.calculateWithTimeCounting(x1, y1, x2, y2));
            timeLabel.setText("Time: " + calculator.getCountedTime() / 1000 + " ms");
        } catch (Exception ex) {
            errorLabel.setText("one of inputs is not int; please, repeat");
        }
    }

    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLayout(null);

        drawingPanel.setBounds(0, (int) ((1-PANEL_SCALE_H)*HEIGHT), (int) (PANEL_SCALE_W * WIDTH), (int) (PANEL_SCALE_H * HEIGHT));
        add(drawingPanel);

        timeLabel = new JLabel("Time:");
        timeLabel.setFont(errorFont);
        timeLabel.setBounds(20, 160, 100, 30);
        add(timeLabel);


        errorLabel = new JLabel("");
        errorLabel.setFont(errorFont);
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(20, 200, 900, 40);
        add(errorLabel);

        configureInputsOn(10,10);

        for(var item: CalculatorType.values())
            calculatorTypeJComboBox.addItem(item);

        calculatorTypeJComboBox.setBounds(240,10,140,40);
        add(calculatorTypeJComboBox);

        var startButton = new JButton("Calculate");
        startButton.setBounds(430, 10, 100, 40);
        startButton.addActionListener(e -> doAction());
        add(startButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(430, 90, 100, 30);
        clearButton.addActionListener(e -> drawingPanel.drawPoints(new ArrayList<>()));

        add(clearButton);

        setResizable(false);
    }

    private void configureInputsOn(int x, int y){
        configureInputCoordinate(x1Input, "X1: ", x, y);
        configureInputCoordinate(y1Input, "Y1: ", x, y+INPUT_HEIGHT+10);
        configureInputCoordinate(x2Input, "X2: ", x+INPUT_TEXTFIELD_WIDTH+10, y);
        configureInputCoordinate(y2Input, "Y2: ", x+INPUT_TEXTFIELD_WIDTH+10, y+INPUT_HEIGHT+10);
        configureInputCoordinate(radiusInput, "Rad.: ", x, y+(INPUT_HEIGHT+10)*2);
    }

    private void configureInputCoordinate(JTextField jTextField, String name, int x, int y) {
        JLabel jLabel = new JLabel(name);
        jLabel.setFont(dataFont);
        jLabel.setBounds(x, y, INPUT_LABEL_WIDTH, INPUT_HEIGHT);
        add(jLabel);

        jTextField.setBounds(x + INPUT_LABEL_WIDTH + 10, y, INPUT_TEXTFIELD_WIDTH, INPUT_HEIGHT);
        jTextField.setFont(dataFont);
        add(jTextField);
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.repaint();
    }
}
