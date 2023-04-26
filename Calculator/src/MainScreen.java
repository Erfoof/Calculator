import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainScreen implements  ActionListener {

    ImageIcon logo = new ImageIcon("C:\\Users\\User\\IdeaProjects\\Calculator\\src\\pixelLogoResized.png");
    String num1 = null;
    String num2 = null;
    double result;

    String operator;

    Font numberFont = new Font("Bahnshrift", Font.PLAIN, 30);
    Font functionFont = new Font("Bahnshrift", Font.PLAIN, 20);

    Font resultFont = new Font("Bahnshrift", Font.PLAIN, 35);

    Font offbutton = new Font("Bahnshrift", Font.BOLD, 16);
    JFrame calculator;

    RoundedTextField numberField;

    JPanel panel;

    JButton[] numberButton = new JButton[10]; //10 numbers

    JButton[] functionButton = new JButton[9]; //8 functions

    RoundedButton addButton, subButton, mulButton, decButton, divButton, equButton, clrButton, delButton, negButton, offButton;

    public static double round(double number, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return Math.round(number * factor) / factor;
    }

    MainScreen() {
        calculator = new JFrame();
        calculator.setSize(400, 550);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setLocationRelativeTo(null);
        calculator.setResizable(false);
        calculator.setLayout(null);
        calculator.getContentPane().setBackground(new Color(235, 215, 255, 255));
        calculator.setTitle("Erfan's Calculator");
        calculator.setIconImage(logo.getImage());


        //Number field
        numberField = new RoundedTextField(10);
        numberField.setBackground(new Color(224, 207, 255, 255));
        numberField.setBounds(45, 35, 301, 80);
        numberField.setEditable(false);
        numberField.setFont(resultFont);
        numberField.setToolTipText("nyomnyomnyom");
        calculator.add(numberField);

        //panel for numbers
        panel = new JPanel();
        panel.setBounds(45, 130, 300, 350);
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setOpaque(false);
        calculator.add(panel);


        //Adding the function buttons to an array and also instantiating them.
        functionButton[0] = addButton = new RoundedButton("+");
        functionButton[1] = subButton = new RoundedButton("-");
        functionButton[2] = divButton = new RoundedButton("÷");
        functionButton[3] = mulButton = new RoundedButton("*");
        functionButton[4] = equButton = new RoundedButton("=");
        functionButton[5] = delButton = new RoundedButton("DEL");
        functionButton[6] = clrButton = new RoundedButton("CLR");
        functionButton[7] = decButton = new RoundedButton(".");
        functionButton[8] = negButton = new RoundedButton("{-}");

        offButton = new RoundedButton("ON");
        offButton.addActionListener(this);

        //instantiating the numbers
        for (int i = 0; i < 10; i++) {
            numberButton[i] = new RoundedButton(String.valueOf(i)); //now we have all the numbers 0-9
        }
        ;

        panel.add(numberButton[1]);
        panel.add(numberButton[2]);
        panel.add(numberButton[3]);

        panel.add(addButton);

        panel.add(numberButton[4]);
        panel.add(numberButton[5]);
        panel.add(numberButton[6]);

        panel.add(subButton);

        panel.add(numberButton[7]);
        panel.add(numberButton[8]);
        panel.add(numberButton[9]);

        panel.add(mulButton);

        panel.add(decButton);

        panel.add(numberButton[0]);
        panel.add(equButton);
        panel.add(divButton);

        panel.add(delButton);
        panel.add(clrButton);
        panel.add(negButton);
        panel.add(offButton);



        //formatting the number buttons
        for (JButton jButton : numberButton) {
            jButton.addActionListener(this); //adding action listener to all numbers
            jButton.setFont(numberFont);
            jButton.setFocusable(false);
            jButton.setBackground(new Color(207, 181, 241));
        }
        ;
        //formatting the function buttons
        for (JButton jButton : functionButton) {
            jButton.addActionListener(this); //adding action listener to all numbers
            jButton.setFont(functionFont);
            jButton.setFocusable(false);
            jButton.setBackground(numberField.getBackground());
        }
        ;

        // changing formatting for delete and clear buttons
        delButton.setFont(offbutton);
        clrButton.setFont(offbutton);

        // off & on button formatting
        offButton.setFont(offbutton);
        offButton.setEnabled(true);
        offButton.setBackground(new Color(132, 255, 143));

        calculator.setVisible(true);
    }

    ;

    public static void main(String[] args) {
        MainScreen calc = new MainScreen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButton[i]) { // if input is any number in array
                numberField.setText(numberField.getText() + Integer.parseInt(numberButton[i].getText()));
            }
        }

        //ON & OFF BUTTOn
        if(e.getSource() == offButton){
            if(offButton.getText() == "OFF") {
                offButton.setText("ON");
                for(int i = 0; i < functionButton.length; i++){
                    functionButton[i].setEnabled(true);
                };
                for(int i = 0; i < numberButton.length; i++){
                    numberButton[i].setEnabled(true);
                };
                offButton.setBackground(new Color(132, 255, 143));

            }

            else if (offButton.getText() == "ON"){
                offButton.setText("OFF");
                for(int i = 0; i < functionButton.length; i++){
                    functionButton[i].setEnabled(false);
                };
                for(int i = 0; i < numberButton.length; i++){
                    numberButton[i].setEnabled(false);
                };
                offButton.setBackground(new Color(255, 46, 46, 165));
            };
        };

        if(e.getSource() == negButton){
            numberField.setText("-");
        };

        if(e.getSource() == clrButton){
            numberField.setText(null);
        };

        if(e.getSource() == delButton){
            numberField.setText(numberField.getText().substring(0, numberField.getText().length()-1));
        };

        if(e.getSource() == addButton){

            num1 = numberField.getText();
            operator = "+";
            numberField.setText(null);
        };

        if(e.getSource() == mulButton){
            num1 = numberField.getText();
            operator = "*";
            numberField.setText(null);
        };

        if(e.getSource() == divButton){
            num1 = numberField.getText();
            operator = "/";
            numberField.setText(null);
        };

        if(e.getSource() == subButton){
            num1 = numberField.getText();
            operator = "-";
            numberField.setText(null);
        };

        if(e.getSource() == decButton){
            numberField.setText(numberField.getText() + ".");
        };


        if(e.getSource() == equButton){
            num2 = numberField.getText();
            if(operator == "+"){
                result = Double.parseDouble(num1) + Double.parseDouble(num2);

                //rounding before printing
                result = round(result, 5);
                numberField.setText(String.valueOf(result));
            }

            else if(operator == "*"){
                result = Double.parseDouble(num1) * Double.parseDouble(num2);

                result = round(result, 5);
                numberField.setText(String.valueOf(result));
            }

            else if(operator == "/"){
                result = Double.parseDouble(num1) / Double.parseDouble(num2);

                result = round(result, 5);
                numberField.setText(String.valueOf(result));
            }

            else if(operator == "-"){
                result = Double.parseDouble(num1) - Double.parseDouble(num2);

                result = round(result, 5);
                numberField.setText(String.valueOf(result));
            }

        };


    }

}