import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

class MainAdminWindow extends JFrame {
    private JButton addStudentButton;
    private JButton updateStudentButton;
    private JButton deleteStudentButton;

    public MainAdminWindow() {
        super("Administration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        addStudentButton = new JButton("Add Student");
        updateStudentButton = new JButton("Update Student");
        deleteStudentButton = new JButton("Delete Student");

        addStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the add student window
                AddStudentWindow addStudentWindow = new AddStudentWindow();
                addStudentWindow.setVisible(true);
            }
        });

        updateStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the update student window
                UpdateStudentWindow updateStudentWindow = new UpdateStudentWindow();
                updateStudentWindow.setVisible(true);
            }
        });

        deleteStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the delete student window
                DeleteStudentWindow deleteStudentWindow = new DeleteStudentWindow();
                deleteStudentWindow.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.add(addStudentButton);
        buttonPanel.add(updateStudentButton);
        buttonPanel.add(deleteStudentButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String arg[]) {
        UpdateStudentWindow updateStudentWindow = new UpdateStudentWindow();
        AddStudentWindow addstudentwindow = new AddStudentWindow();
    }
}

class AddStudentWindow extends JFrame {
    private JTextField nameField;
    private JTextField regField;
    private JTextField rollNumberField;
    private JTextField departmentField;
    private JTextField fNameField;
    private JTextField addField;
    private JTextField mobNumberField;
    private JTextField sectionField;
    private JButton addButton;

    public AddStudentWindow() {
        super("Add Student");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(9, 2));
        // row one
        panel.add(new JLabel("Name: "));
        panel.add(new JLabel("Department: "));
        // row two
        nameField = new JTextField();
        panel.add(nameField);
        departmentField = new JTextField();
        panel.add(departmentField);
        // row three
        panel.add(new JLabel("Reg Number: "));
        panel.add(new JLabel("Roll Number: "));
        // row four
        regField = new JTextField();
        panel.add(regField);
        rollNumberField = new JTextField();
        panel.add(rollNumberField);
        // row five
        panel.add(new JLabel("Father's Name: "));
        panel.add(new JLabel("Address: "));
        // row six
        fNameField = new JTextField();
        panel.add(fNameField);
        addField = new JTextField();
        panel.add(addField);
        // row seven
        panel.add(new JLabel("Mobile Number: "));
        panel.add(new JLabel("Section: "));
        // row eight
        mobNumberField = new JTextField();
        panel.add(mobNumberField);
        sectionField = new JTextField();
        panel.add(sectionField);

        // row nine
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the values from the text fields
                String name = nameField.getText();
                String department = departmentField.getText();
                String regNumber = regField.getText();
                String rollNumber = rollNumberField.getText();
                String fatherName = fNameField.getText();
                String address = addField.getText();
                String mobNumber = mobNumberField.getText();
                String section = sectionField.getText();

                // Add the student to the database or file

                Database db = new Database();
                if (db.InsertStudentData(name, department, regNumber, rollNumber, section, fatherName, address,
                        mobNumber) > 0)
                    dispose();

                // Close the window
            }
        });
        panel.add(addButton);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
}

class UpdateStudentWindow extends JFrame {
    private JTextField regField;
    private JButton findButton;

    public UpdateStudentWindow() {
        super("Update Student");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("Reg Number: "));
        regField = new JTextField();
        panel.add(regField);
        findButton = new JButton("Find");
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the values from the text fields
                String regNumber = regField.getText();
                System.out.println(regNumber);

                // Update the records to the database or file
                // ...

                try {
                    ShowDetailsToUpdate sdtu = new ShowDetailsToUpdate(regNumber);
                    sdtu.setVisible(true);
                    // Close the window
                    dispose();

                } catch (Exception er) {
                    System.out.println(e);
                    ErrorMessage errorMessage = new ErrorMessage(er);
                }

            }
        });
        panel.add(findButton);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}

class DeleteStudentWindow extends JFrame {
    private JTextField regField;
    private JButton deleteButton;

    public DeleteStudentWindow() {
        super("Delete Student");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("Reg Number: "));
        regField = new JTextField();
        panel.add(regField);
        deleteButton = new JButton("Delete Record");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the values from the text fields
                String regNumber = regField.getText();

                // Update the records to the database or file
                // ...

                Database db = new Database();
                if (db.DeleteStudentRecord(regNumber) > 0)
                    // Close the window
                    dispose();
                else
                    System.out.println("No Record Found");
            }
        });
        panel.add(deleteButton);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}

class ShowDetailsToUpdate extends JFrame {
    private JTextField nameField;
    private JTextField rollNumberField;
    private JTextField departmentField;
    private JTextField fNameField;
    private JTextField addField;
    private JTextField mobNumberField;
    private JTextField sectionField;
    private JButton updateButton;

    public ShowDetailsToUpdate(String Reg_no) throws SQLException {
        super("Update Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 500);

        Database db = new Database();
        ResultSet rs = db.RetrieveRecord(Reg_no, 0);

        JPanel panel = new JPanel(new GridLayout(8, 2));
        // row one
        panel.add(new JLabel("Name"));
        panel.add(new JLabel("Department"));
        nameField = new JTextField(rs.getString("name"));
        panel.add(nameField);
        departmentField = new JTextField(rs.getString("Department"));
        panel.add(departmentField);
        // row two
        panel.add(new JLabel("Roll no"));
        panel.add(new JLabel("Father's Name"));
        rollNumberField = new JTextField(rs.getString("Roll_no"));
        panel.add(rollNumberField);
        fNameField = new JTextField(rs.getString("Fathers_name"));
        panel.add(fNameField);
        // row three
        panel.add(new JLabel("Address"));
        panel.add(new JLabel("Mobile"));
        addField = new JTextField(rs.getString("Address"));
        panel.add(addField);
        mobNumberField = new JTextField(rs.getString("Mobile"));
        panel.add(mobNumberField);
        // row four
        panel.add(new JLabel("Section"));
        panel.add(new JLabel(""));
        sectionField = new JTextField(rs.getString("Section"));
        panel.add(sectionField);
        updateButton = new JButton("Update");
        System.out.println("Hello World");
        updateButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Get the values from the text fields
                String name = nameField.getText();
                String department = departmentField.getText();
                String rollNumber = rollNumberField.getText();
                String fatherName = fNameField.getText();
                String address = addField.getText();
                String mobNumber = mobNumberField.getText();
                String section = sectionField.getText();

                // Update the student details to the database

                Database db = new Database();
                if (db.UpdateStudentData(Reg_no, name, department, rollNumber, section, fatherName, address,
                        mobNumber) > 0)
                    dispose();

                // Close the window
            }
        });
        panel.add(updateButton);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
}
