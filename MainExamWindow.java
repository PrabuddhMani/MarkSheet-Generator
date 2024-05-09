import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import javax.swing.text.View;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

class MainExamWindow extends JFrame {
    private JTextField regField;
    private JButton feedMarskButton;
    private JButton viewMarksButton;

    public MainExamWindow() {
        super("Examinations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        feedMarskButton = new JButton("Feed Marks");
        viewMarksButton = new JButton("View Marks ");

        feedMarskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the add student window
                String Reg_no = regField.getText();
                SelectCourseExamWindow selectCourseExamWindow = new SelectCourseExamWindow(Reg_no);
                selectCourseExamWindow.setVisible(true);
            }
        });

        viewMarksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the update student window
                String Reg_no = regField.getText();
                ViewMarksWindow viewMarksWindow = new ViewMarksWindow(Reg_no);
                viewMarksWindow.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        JLabel nameLabel = new JLabel("Enter Registration Number: ");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(nameLabel);
        regField = new JTextField();
        regField.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(regField);
        buttonPanel.add(feedMarskButton);
        buttonPanel.add(viewMarksButton);

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String str[]) {
       
        ViewMarksWindow viewMarksWindow = new ViewMarksWindow("ME202109");
       

    }
}

class SelectCourseExamWindow extends JFrame {
    private JTextField courseCodeField;
    private JButton continueButton;

    public SelectCourseExamWindow(String Reg_no) {
        super("Enter Course Code");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        continueButton = new JButton("Continue");
        Database db = new Database();
        ResultSet rs = db.RetrieveRecord(Reg_no, 0);
        String dept = "";
        try {
            dept = rs.getString("Department");
            System.out.println(dept);
        } catch (Exception e) {
            System.out.println(e);
        }

        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the add student window
                String courseCode = courseCodeField.getText();
                System.out.println("Course Code: " + courseCode);

                FeedMarksWindow feedMarksWindow = new FeedMarksWindow(Reg_no, courseCode);
                feedMarksWindow.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(7, 2));

        buttonPanel.add(new JLabel("Course Code"));
        buttonPanel.add(new JLabel("Course Name"));

        ResultSet rs1 = db.RetrieveRecord(Reg_no, dept);

        try {
            do {
                String course_code = rs1.getString("course_code");
                String course_name = rs1.getString("course_name");
                buttonPanel.add(new JLabel(course_code));
                buttonPanel.add(new JLabel(course_name));
            } while (rs1.next());
        } catch (Exception e) {
            System.out.println(e);
        }

        courseCodeField = new JTextField();

        buttonPanel.add(courseCodeField);

        buttonPanel.add(continueButton);
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}

class FeedMarksWindow extends JFrame {

    private JTextField CA1Field;
    private JTextField CA2Field;
    private JTextField CA3Field;
    private JTextField MTEField;
    private JTextField ETEField;

    private JButton addButton;

    public FeedMarksWindow(String Reg_no, String course_code) {
        super("Reg_no " + Reg_no);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(9, 2));
        Database db = new Database();
        ResultSet rs = db.RetrieveRecord(Reg_no, 0);
        String name = "";
        String dept = "";
        String roll_no = "";
        String section = "";

        try {
            name = rs.getString("name");
            dept = rs.getString("department");
            roll_no = rs.getString("roll_no");
            section = rs.getString("Section");
        } catch (Exception e) {
            System.out.println(e);
        }
        // row one
        panel.add(new JLabel("                  Name: " + name));
        panel.add(new JLabel("                  Dept: " + dept));
        // row two
        panel.add(new JLabel("                  Roll_no: " + roll_no));
        panel.add(new JLabel("                  Section: " + section));

        // row three
        panel.add(new JLabel("                           MTE"));
        panel.add(new JLabel("                           ETE"));
        // row four
        MTEField = new JTextField();
        panel.add(MTEField);
        ETEField = new JTextField();
        panel.add(ETEField);

        // row five
        panel.add(new JLabel("                           CA1"));
        panel.add(new JLabel("                           CA2"));
        // row six
        CA1Field = new JTextField();
        panel.add(CA1Field);
        CA2Field = new JTextField();
        panel.add(CA2Field);

        // row seven
        panel.add(new JLabel("                           CA3"));
        panel.add(new JLabel("          Course Code: " + course_code));
        // row eight
        CA3Field = new JTextField();
        panel.add(CA3Field);
        addButton = new JButton("Feed Marks");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the values from the text fields
                String CA1 = CA1Field.getText();
                String CA2 = CA2Field.getText();
                String CA3 = CA3Field.getText();
                String MTE = MTEField.getText();
                String ETE = ETEField.getText();

                // Add the student to the database or file

                Database db = new Database();
                if (db.feedMarks(Reg_no, course_code, CA1, CA2, CA3, MTE, ETE) > 0)
                    dispose();

                // Close the window
            }
        });
        panel.add(addButton);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
}

class ViewMarksWindow extends JFrame {

    private JButton downloadButton;

    public ViewMarksWindow(String Reg_no) {
        super("View Marks");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1300, 400);

        JPanel panel = new JPanel(new GridLayout(15, 9));

        Database db = new Database();
        ResultSet rs = db.RetrieveRecord(Reg_no, 0);
        String name = "";
        String Department = "";
        String Roll_no = "";
        String Section = "";
        String Fathers_name = "";
        String Address = "";
        String Mobile = "";

        try {
            name = rs.getString("Name");
            Department = rs.getString("Department");
            Roll_no = rs.getString("Roll_no");
            Section = rs.getString("Section");
            Fathers_name = rs.getString("Fathers_name");
            Address = rs.getString("Address");
            Mobile = rs.getString("Mobile");
        } catch (Exception e) {
            System.out.println(e);
        }

        // row one

        panel.add(new JLabel(""));
        panel.add(new JLabel("Name "));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Reg_no "));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Department "));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        panel.add(new JLabel(""));
        panel.add(new JLabel(name));
        panel.add(new JLabel(""));
        panel.add(new JLabel(Reg_no));
        panel.add(new JLabel(""));
        panel.add(new JLabel(Department));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        panel.add(new JLabel(""));
        panel.add(new JLabel("Fathers Name"));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Mobile "));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Address "));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        panel.add(new JLabel(""));
        panel.add(new JLabel(Fathers_name));
        panel.add(new JLabel(""));
        panel.add(new JLabel(Mobile));
        panel.add(new JLabel(""));
        panel.add(new JLabel(Address));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        panel.add(new JLabel(""));
        panel.add(new JLabel("Roll_no "));
        panel.add(new JLabel(""));
        panel.add(new JLabel("Section "));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        panel.add(new JLabel(""));
        panel.add(new JLabel(Roll_no));
        panel.add(new JLabel(""));
        panel.add(new JLabel(Section));
        panel.add(new JLabel(""));
        downloadButton = new JButton("Download Report");
        panel.add(downloadButton);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action to be performed
                GenerateReportPDF generateReportPDF = new GenerateReportPDF(Reg_no);
                // Close the window
                dispose();
            }
        });

        panel.add(new JLabel("-------------------------------------"));
        panel.add(new JLabel("-------------------------------------"));
        panel.add(new JLabel("-------------------------------------"));
        panel.add(new JLabel("-------------------------------------"));
        panel.add(new JLabel("-------------------------------------"));
        panel.add(new JLabel("-------------------------------------"));
        panel.add(new JLabel("-------------------------------------"));
        panel.add(new JLabel("-------------------------------------"));

        panel.add(new JLabel("Course Code"));
        panel.add(new JLabel("Course Name"));
        panel.add(new JLabel("CA1"));
        panel.add(new JLabel("CA2"));
        panel.add(new JLabel("CA3"));
        panel.add(new JLabel("MTE"));
        panel.add(new JLabel("ETE"));
        panel.add(new JLabel("Grade"));
        GenerateGrade generateGrade = new GenerateGrade();

        ResultSet rs1 = db.RetrieveRecord(Reg_no, 1);
        try {
            do {
                // System.out.println(rs1.getString());
                panel.add(new JLabel(rs1.getString("Course_code")));
                panel.add(new JLabel(rs1.getString("Course")));
                int CA1 = rs1.getInt("CA1");
                panel.add(new JLabel(Integer.toString(CA1)));
                int CA2 = rs1.getInt("CA2");
                panel.add(new JLabel(Integer.toString(CA2)));
                int CA3 = rs1.getInt("CA3");
                panel.add(new JLabel(Integer.toString(CA3)));
                int MTE = rs1.getInt("MTE");
                panel.add(new JLabel(Integer.toString(MTE)));
                int ETE = rs1.getInt("ETE");
                panel.add(new JLabel(Integer.toString(ETE)));
                String grade = generateGrade.grade(CA1, CA2, CA3, MTE, ETE);
                panel.add(new JLabel(grade));
            } while (rs1.next());

        } catch (Exception e) {
            System.out.println(e);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }
}
