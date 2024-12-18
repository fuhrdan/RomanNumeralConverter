import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RomanNumeralConverter {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Number to Roman Numeral Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create components
        JLabel inputLabel = new JLabel("Enter a number:");
        JTextField inputField = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        JLabel resultLabel = new JLabel("Roman Numeral: ");

        // Set up layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.add(inputLabel);
        panel.add(inputField);
        panel.add(convertButton);
        panel.add(resultLabel);

        // Add functionality to the button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int number = Integer.parseInt(inputField.getText());
                    if (number <= 0) {
                        resultLabel.setText("Roman Numeral: Invalid Input (must be positive)");
                    } else {
                        String romanNumeral = toRoman(number);
                        resultLabel.setText("Roman Numeral: " + romanNumeral);
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Roman Numeral: Invalid Input");
                }
            }
        });

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to convert number to Roman numeral
    private static String toRoman(int number) {
        String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder roman = new StringBuilder();
        while (number > 3999) {
            roman.append("(").append(toRoman(number / 1000)).append(")");
            number %= 1000;
        }
        for (int i = 0; i < values.length; i++) {
            while (number >= values[i]) {
                roman.append(romanNumerals[i]);
                number -= values[i];
            }
        }
        return roman.toString();
    }
}
