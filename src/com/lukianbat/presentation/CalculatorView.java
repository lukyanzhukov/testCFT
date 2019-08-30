package com.lukianbat.presentation;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CalculatorView extends JFrame {

    private boolean equalFlag = false;
    private JTextField calculatorField;
    private CalculatorPresenter presenter;

    public CalculatorView(CalculatorPresenter presenter) {
        this.presenter = presenter;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("TestCFT");
        setSize(400, 250);
        setLocationRelativeTo(null);
        initView();
    }

    private void initView() {
        calculatorField = new JTextField();
        calculatorField.setHorizontalAlignment(JTextField.RIGHT);
        calculatorField.setEditable(true);
        calculatorField.setFont(new Font("Serif", Font.BOLD, 23));
        add(calculatorField, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));
        add(buttonPanel, BorderLayout.SOUTH);
        for (int i = 0; i < 17; i++) {
            addButton(buttonPanel, i);
        }

        JButton clearButton = new JButton();
        clearButton.setText("C");
        clearButton.addActionListener(actionEvent -> clearAction());
        buttonPanel.add(clearButton);

        JButton equalsButton = new JButton();
        equalsButton.setText("=");
        equalsButton.addActionListener(actionEvent -> equalsAction());
        buttonPanel.add(equalsButton);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_ENTER: {
                        equalsAction();
                    }
                    case KeyEvent.VK_DELETE: {
                        clearAction();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    private void addButton(Container parent, int name) {
        JButton button = new JButton();
        String symbol;
        switch (name) {
            case 10: {
                symbol = "*";
                break;
            }
            case 11: {
                symbol = "+";
                break;
            }
            case 12: {
                symbol = "-";
                break;
            }
            case 13: {
                symbol = "/";
                break;
            }
            case 14: {
                symbol = "(";
                break;
            }
            case 15: {
                symbol = ")";
                break;
            }
            case 16: {
                symbol = ".";
                break;
            }
            default: {
                symbol = String.valueOf(name);
            }
        }
        button.setText(symbol);
        button.addActionListener(actionEvent -> {
            if (equalFlag) {
                clearAction();
                equalFlag = false;
            }
            calculatorField.setText(calculatorField.getText() + symbol);
        });
        parent.add(button);
    }

    private void equalsAction() {
        equalFlag = true;
        presenter.getCurrent(calculatorField.getText(), message -> calculatorField.setText(message));
    }

    private void clearAction() {
        calculatorField.setText("");
    }
}