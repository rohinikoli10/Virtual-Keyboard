package myapp.swing.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VirtualKeyboardWithEmojis implements ActionListener {

    private JFrame frame;
    private JTextArea textArea;
    private JButton[] charButtons;
    private JButton emojiButton;
    private JButton tabButton;
    private JButton spaceButton;
    private JButton backspaceButton;
    private JButton capsLockButton;
    private JButton shiftButton;
    private JButton enterButton;
    private JButton symbolButton;
    private JButton numberButton;


    private boolean capsLockEnabled = false;
    private boolean shiftEnabled = false;

    private static final String[] keys = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L", ";",
            ",", "Z", "X", "C", "V", "B", "N", "M", ".", "?"
    };

    private static final String[] EMOJIS = {
            "😃", "😊", "🙂", "😍", "🤩", "😎", "😂", "😉", "😛", "🥰",
            "🥳", "😘", "😁", "🤔", "😆", "😏", "🙃", "😄", "😜", "🤗"
    };

    private static final String[] SYMBOLS = {
            "!", "@", "#", "$", "%", "^", "&", "*", "(", ")",
            "-", "_", "+", "=", "~", "<", ">", ",", ".", "?",
            "/", "{", "}", "[", "]", "|"
    };

    private static final String[] NUMBERS = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
    };

    public VirtualKeyboardWithEmojis() {
        frame = new JFrame("Virtual Keyboard");
        textArea = new JTextArea();
        charButtons = new JButton[keys.length];
        emojiButton = new JButton("Emojis");
        tabButton = new JButton("Tab");
        spaceButton = new JButton("Space");
        backspaceButton = new JButton("Backspace");
        capsLockButton = new JButton("CapsLk");
        shiftButton = new JButton("Shift");
        enterButton = new JButton("Enter");
        symbolButton = new JButton("Symbols");
        numberButton = new JButton("Numbers");

        final JDialog emojiDialog = new JDialog(frame, "Emoji Selector",  true);
        emojiDialog.setLayout(new GridLayout(4, 5));

        for (String emoji : EMOJIS) {
            JButton emojiBtn = new JButton(emoji);
            emojiBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textArea.append(emoji);
                    emojiDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    emojiDialog.setVisible(true);

                }
            });
            emojiDialog.add(emojiBtn);
        }

        emojiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emojiDialog.setLocation(emojiButton.getLocationOnScreen().x, emojiButton.getLocationOnScreen().y + emojiButton.getHeight());
                emojiDialog.pack();
                emojiDialog.setVisible(true);
                emojiDialog.dispose();
            }
        });

        // Create panel for emoji button
        JPanel emojiPanel = new JPanel();
        emojiPanel.add(emojiButton);


        final JDialog symbolDialog = new JDialog(frame, "Symbol Selector", true);
        symbolDialog.setLayout(new GridLayout(4,5));

        for (String symbol : SYMBOLS) {
            JButton symbolBtn = new JButton(symbol);
            symbolBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textArea.append(symbol);
                    symbolDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    symbolDialog.setVisible(true);
                }
            });
            symbolDialog.add(symbolBtn);
        }

        symbolButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                symbolDialog.setLocation(symbolButton.getLocationOnScreen().x, symbolButton.getLocationOnScreen().y + symbolButton.getHeight());
                symbolDialog.pack();
                symbolDialog.setVisible(true);
                symbolDialog.dispose();
            }
        });

        //create panel for symbol button
        JPanel symbolPanel = new JPanel();
        symbolPanel.add(symbolButton);

        final JDialog numberDialog = new JDialog(frame, "NumPad", true);
        numberDialog.setLayout(new GridLayout(3,3));

        for (String number : NUMBERS) {
            JButton numberBtn = new JButton(number);
            numberBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textArea.append(number);
                    numberDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    numberDialog.setVisible(true);
                }
            });
            numberDialog.add(numberBtn);
        }

        numberButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                numberDialog.setLocation(numberButton.getLocationOnScreen().x, numberButton.getLocationOnScreen().y + numberButton.getHeight());
                numberDialog.pack();
                numberDialog.setVisible(true);
                numberDialog.dispose();
            }
        });

        //create panel for number button
        JPanel numberPanel = new JPanel(new GridLayout(3,3,5,5));
        numberPanel.add(numberButton);

        JPanel spacePanel = new JPanel(new GridLayout(1,1));
        spacePanel.add(spaceButton);

        JPanel charPanel = new JPanel(new GridLayout(4, 10, 5, 5));
        for (int i = 0; i < keys.length; i++) {
            charButtons[i] = new JButton(keys[i]);
            charButtons[i].addActionListener(this);
            charPanel.setSize(new Dimension(10, 20));
            charPanel.add(charButtons[i]);
        }

        JPanel controlPanel = new JPanel(new GridLayout(2, 5, 5, 5));

        controlPanel.add(tabButton);
        controlPanel.add(emojiButton);
        controlPanel.add(symbolButton);
        controlPanel.add(backspaceButton);
        controlPanel.add(capsLockButton);
        controlPanel.add(shiftButton);
        controlPanel.add(numberButton);
        controlPanel.add(enterButton);

        JPanel keyboardPanel = new JPanel(new BorderLayout());
        keyboardPanel.add(emojiPanel, BorderLayout.SOUTH);
        keyboardPanel.add(charPanel, BorderLayout.CENTER);
        keyboardPanel.add(controlPanel, BorderLayout.NORTH);
        keyboardPanel.add(numberPanel, BorderLayout.SOUTH);
        keyboardPanel.add(spacePanel, BorderLayout.SOUTH);

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(keyboardPanel, BorderLayout.SOUTH);

        textArea.setFont(new Font("Arial", Font.PLAIN, 20));

        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Set up keyboard shortcuts
        tabButton.addActionListener(this);
        emojiButton.addActionListener(this);
        spaceButton.addActionListener(this);
        backspaceButton.addActionListener(this);
        capsLockButton.addActionListener(this);
        shiftButton.addActionListener(this);
        symbolButton.addActionListener(this);
        numberButton.addActionListener(this);
        enterButton.addActionListener(this);

        frame.getRootPane().setDefaultButton(null);
        frame.setFocusTraversalKeysEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        String buttonText = sourceButton.getText();


        if (sourceButton == emojiButton) {

        } else if (sourceButton == tabButton) {
            textArea.append("     ");
        } else if (sourceButton == spaceButton) {
            textArea.append(" ");
        } else if (sourceButton == backspaceButton) {
            String text = textArea.getText();
            if (text.length() > 0) {
                text = text.substring(0, text.length() - 1);
                textArea.setText(text);
            }
        } else if (sourceButton == capsLockButton) {
            capsLockEnabled = !capsLockEnabled;
            updateButtonText();
        }  else if (sourceButton == shiftButton) {
            shiftEnabled = !shiftEnabled;
            updateButtonText();
        } else if (sourceButton == symbolButton){

        }else if(sourceButton == numberButton) {

        }else if (sourceButton == enterButton) {
            textArea.append("\n");
        } else {
            String text = buttonText;
            if (!capsLockEnabled && !shiftEnabled) {
                text = text.toLowerCase();
            }
            textArea.append(text);
        }
    }

    private void updateButtonText() {
        if (capsLockEnabled) {
            capsLockButton.setBackground(Color.GREEN);
            for (JButton button : charButtons) {
                button.setText(button.getText().toUpperCase());
            }
        } else {
            capsLockButton.setBackground(null);
            for (JButton button : charButtons) {
                if (!shiftEnabled) {
                    button.setText(button.getText().toLowerCase());
                } else {
                    button.setText(button.getText().toUpperCase());
                }
            }
        }

        if (shiftEnabled) {
            shiftButton.setBackground(Color.GREEN);
        } else {
            shiftButton.setBackground(null);
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(VirtualKeyboardWithEmojis::new);
    }
}