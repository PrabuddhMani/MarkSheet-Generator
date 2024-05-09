import java.sql.*;

import javax.lang.model.util.ElementScanner6;

public class Database {
    static final String DB_URL = "jdbc:mysql://localhost:3306/University";
    static final String USER = "root";
    static final String PASS = "";

    int InsertStudentData(String Name, String Department, String Reg_no, String Roll_no, String Section,
            String Fathers_name, String Address, String Mobile) {
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "Insert Into Student (Name, Department, Reg_no, Roll_no, Section, Fathers_name, Address, Mobile) VALUES ( '"
                    + Name + "', '" + Department + "', '" + Reg_no + "', '" + Roll_no + "', '" + Section + "', '"
                    + Fathers_name + "', '" + Address + "', '" + Mobile + "')";
            ResultSet rs = new Database().RetrieveRecord(Reg_no, Department);
            if (rs.getString("course_name") == null) {
                NullPointerException nullPointer = new NullPointerException();
                throw nullPointer;
            }
            i = stmt.executeUpdate(sql);
            do {
                String course_code = rs.getString("course_code");
                String course_name = rs.getString("course_name");
                String sql1 = "Insert Into marks (Reg_no, Course, Course_code, CA1, CA2, CA3, MTE, ETE) values ( '"
                        + Reg_no + "', '"
                        + course_name + "', '" + course_code + "', 0, 0, 0, 0, 0)";
                i = stmt.executeUpdate(sql1);
            } while (rs.next());
            System.out.println("Inserted Student Details in given databaase...");
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e);
        }

        return i;
    }

    ResultSet RetrieveRecord(String Reg_no, int q) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "";
            if (q == 0)
                query = "SELECT * FROM Student WHERE Reg_no = '" + Reg_no + "'";
            else if (q == 1)
                query = "SELECT * FROM marks WHERE REG_no = '" + Reg_no + "'";

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return resultSet;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    ResultSet RetrieveRecord(String Reg_no, String dept) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "SELECT * FROM course_details WHERE department = '" + dept + "'";

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return resultSet;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    int UpdateStudentData(String Reg_no, String Name, String Department, String Roll_no, String Section,
            String Fathers_name, String Address, String Mobile) {
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE Student SET Name = '" + Name + "', Department = '" + Department
                    + "', Roll_no = '" + Roll_no + "', Section = '" + Section + "', Fathers_name = '" + Fathers_name
                    + "', Address = '" + Address + "', Mobile = '" + Mobile + "' WHERE Reg_no = '" + Reg_no + "'";

            i = stmt.executeUpdate(sql);

            System.out.println("Updated the details in the table...");
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }

    int DeleteStudentRecord(String Reg_no) {
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM marks WHERE Reg_no = '" + Reg_no + "'";
            String sql = "DELETE FROM Student WHERE Reg_no = '" + Reg_no + "' ";

            i = stmt.executeUpdate(query);
            i = stmt.executeUpdate(sql);
            if (i > 0)
                System.out.println("Deleted Record in from databaase...");
        } catch (Exception e) {
            System.out.println(e);
        }

        return i;
    }

    int feedMarks(String Reg_no, String courseCode, String CA1, String CA2, String CA3, String MTE, String ETE) {

        int ca1 = Integer.parseInt(CA1);
        int ca2 = Integer.parseInt(CA2);
        int ca3 = Integer.parseInt(CA3);
        int mte = Integer.parseInt(MTE);
        int ete = Integer.parseInt(ETE);

        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            // String sql = "Insert Into marks (CA1, CA2, CA3, MTE, ETE) values ( '" + ca1 +
            // "', '" + ca2 + "', '"
            // + ca3 + "', '" + mte + "', '" + ete + "') WHERE Reg_no = '" + Reg_no + "'";

            String sql = "UPDATE marks SET CA1 = '" + ca1 + "', CA2 = '" + ca2 + "', CA3 = '" + ca3 + "', MTE = '" + mte
                    + "', ETE = '" + ete + "' WHERE Reg_no = '" + Reg_no + "' AND Course_code = '" + courseCode + "'";

            i = stmt.executeUpdate(sql);

            System.out.println("Inserted Student marks in given databaase...");
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }

    public static void main(String str[]) throws SQLException {
        Database db = new Database();
    }
}
