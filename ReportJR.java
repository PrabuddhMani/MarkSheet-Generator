import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JRException;

public class ReportJR {
    public static void main(String[] args) {
        String jrxmlFilePath = "C:\\Users\\prabu\\JaspersoftWorkspace\\MyReports\\reporttry1.jasper";
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFilePath);
            System.out.println("Report compiled successfully.");
        } catch (JRException ex) {
            System.err.println("Error compiling report: " + ex.getMessage());
        }
    }
}
