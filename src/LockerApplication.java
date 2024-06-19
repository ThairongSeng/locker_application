import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LockerApplication extends JFrame {

    private String password = null; // Variable to store the password
    private JLabel statusLabel; // Label to display status messages

    public LockerApplication() {
        super("Locker Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components
        JLabel titleLabel = new JLabel("Locker Application", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        JTextField passwordField = new JTextField(20);
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        JButton enterButton = new JButton("Enter");

        // Number Buttons
        JButton[] numButtons = new JButton[10];
        JPanel numPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        for (int i = 1; i <= 9; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numPanel.add(numButtons[i]);
        }
        numButtons[0] = new JButton("0");
        numPanel.add(numButtons[0]);

        // Prompt Label
        JLabel promptLabel = new JLabel("Enter password:", JLabel.CENTER);

        // Layout
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(numPanel, BorderLayout.NORTH);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passwordPanel.add(promptLabel);
        passwordPanel.add(passwordField);
        centerPanel.add(passwordPanel, BorderLayout.CENTER);

        centerPanel.add(enterButton, BorderLayout.SOUTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        panel.add(statusLabel, BorderLayout.SOUTH);

        getContentPane().add(panel);

        // Action Listener for Number Buttons
        for (int i = 0; i < 10; i++) {
            final int num = i;
            numButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    passwordField.setText(passwordField.getText() + num);
                }
            });
        }

        // Action Listener for Enter Button
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPassword = passwordField.getText().trim();

                if (password == null) {
                    // First time setting the password
                    password = enteredPassword;
                    statusLabel.setText("Password Set Successfully!");
                } else {
                    // Verifying the password
                    if (enteredPassword.equals(password)) {
                        statusLabel.setText("Correct Password");
                    } else {
                        statusLabel.setText("Incorrect Password");
                    }
                }

                // Clear the password field after each operation
                passwordField.setText("");
            }
        });

        // Display the frame
        setSize(300, 300);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LockerApplication();
            }
        });
    }
}
