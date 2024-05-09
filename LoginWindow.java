import java.sql.ResultSet;

// import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
// import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginWindow extends JFrame implements ActionListener {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginWindow() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals(" ") && password.equals(" ")) {
            // Open the main window
            SubLoginWindow subLoginWindow = new SubLoginWindow();
            subLoginWindow.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        LoginWindow loginWindow = new LoginWindow();
    }
}

class SubLoginWindow extends JFrame {
    private JButton administration;
    private JButton examination;

    public SubLoginWindow() {
        super("Administration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);

        administration = new JButton("Administration");
        examination = new JButton("Examination");

        administration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the add student window
                MainAdminWindow mainAdminWindow = new MainAdminWindow();
                mainAdminWindow.setVisible(true);
            }
        });

        examination.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the update student window
                MainExamWindow mainExamWindow = new MainExamWindow();
                mainExamWindow.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(administration);
        buttonPanel.add(examination);

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}

class ErrorMessage extends JFrame {

    public ErrorMessage(Exception e) {
        JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
