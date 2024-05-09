import java.io.FileOutputStream;
import java.sql.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.File;

import com.itextpdf.io.font.FontConstants; 
import com.itextpdf.kernel.color.Color; 
import com.itextpdf.kernel.font.PdfFontFactory; 
import com.itextpdf.kernel.font.PdfFont; 
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 

import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Paragraph; 
import com.itextpdf.layout.element.Text;  


public class GenerateReportPDF {

    GenerateReportPDF (String Reg_no) {
        
        Database db = new Database();
        ResultSet rs = db.RetrieveRecord(Reg_no, 0);

        try {

            String name = rs.getString("Name");
            String dept = rs.getString("Department");
            String fathers_name = rs.getString("Fathers_name");
            String reg_no = rs.getString("Reg_no");
            String roll_no = rs.getString("Roll_no");
            String section = rs.getString("section");
            String address = rs.getString("Address");
            String mobile = rs.getString("mobile");
            // Create PDF document
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(Reg_no+".pdf"));
            document.open();
            
            Font fontname_Helvetica =  FontFactory.getFont(FontFactory.HELVETICA, 26f, Font.BOLD);
           
            Paragraph header = new Paragraph("Lovely Professional University", fontname_Helvetica); 
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            
            PdfPTable table0 = new PdfPTable(2);
            table0.setWidthPercentage(80);
            table0.setSpacingBefore(50);
            
     

            PdfPCell cell;
            
            cell = new PdfPCell(new Phrase("Name:  " + name));
            cell.setBorder(Rectangle.NO_BORDER);
            table0.addCell(cell);
            cell = new PdfPCell(new Phrase("Department:  " + dept));
            cell.setBorder(Rectangle.NO_BORDER);
            table0.addCell(cell);
            
            PdfPCell blankRow = new PdfPCell(new Phrase("\n"));
            blankRow.setFixedHeight(10f);
            blankRow.setColspan(2);
            blankRow.setBorder(Rectangle.NO_BORDER);
            
            cell = new PdfPCell(new Phrase("Father's Name:  " + fathers_name));
            cell.setBorder(Rectangle.NO_BORDER);
            table0.addCell(cell);
            cell = new PdfPCell(new Phrase("Reg no:  " + reg_no));
            cell.setBorder(Rectangle.NO_BORDER);
            table0.addCell(cell);
            
            blankRow.setFixedHeight(10f);
            blankRow.setColspan(2);
            blankRow.setBorder(Rectangle.NO_BORDER);
            
            cell = new PdfPCell(new Phrase("Roll no:  " + roll_no));
            cell.setBorder(Rectangle.NO_BORDER);
            table0.addCell(cell);
            cell = new PdfPCell(new Phrase("Department:  " + section));
            cell.setBorder(Rectangle.NO_BORDER);
            table0.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Mobile:  " + mobile));
            cell.setBorder(Rectangle.NO_BORDER);
            table0.addCell(cell);
            cell = new PdfPCell(new Phrase("Address:  " + address));
            cell.setBorder(Rectangle.NO_BORDER);
            table0.addCell(cell);
            table0.setSpacingAfter(30);
            document.add(table0);

            // Add table to the document
            float[] widths = { 3, 5, 1, 1, 1, 1, 1, 1};
            PdfPTable table = new PdfPTable(widths); // 3 columns for student info + 5 columns for marks
            table.setWidthPercentage(100);
            
            // Add table headers
            table.addCell(new PdfPCell(new Paragraph("Course Code")));
            table.addCell(new PdfPCell(new Paragraph("Course Title")));
            table.addCell(new PdfPCell(new Paragraph("CA1")));
            table.addCell(new PdfPCell(new Paragraph("CA2")));
            table.addCell(new PdfPCell(new Paragraph("CA3")));
            table.addCell(new PdfPCell(new Paragraph("MTE")));
            table.addCell(new PdfPCell(new Paragraph("ETE")));
            table.addCell(new PdfPCell(new Paragraph("Grade")));

            rs = db.RetrieveRecord(Reg_no, 1);
            GenerateGrade generateGrade = new GenerateGrade();
            do {
                String course_code = rs.getString("course_code");
                String course_name = rs.getString("course");
                int CA1 = rs.getInt("CA1");
                int CA2 = rs.getInt("CA2");
                int CA3 = rs.getInt("CA3");
                int MTE = rs.getInt("MTE");
                int ETE = rs.getInt("ETE");
                
                table.addCell(new PdfPCell(new Paragraph(course_code)));
                table.addCell(new PdfPCell(new Paragraph(course_name)));
                table.addCell(new PdfPCell(new Paragraph(Integer.toString(CA1))));
                table.addCell(new PdfPCell(new Paragraph(Integer.toString(CA2))));
                table.addCell(new PdfPCell(new Paragraph(Integer.toString(CA3))));
                table.addCell(new PdfPCell(new Paragraph(Integer.toString(MTE))));
                table.addCell(new PdfPCell(new Paragraph(Integer.toString(ETE))));
                String grade = generateGrade.grade(CA1, CA2, CA3, MTE, ETE);
                table.addCell(new PdfPCell(new Paragraph(grade)));
                
            }while(rs.next());

            

            // Add table to the document
            document.add(table);
            

            // Close the document
            document.close();
            System.out.println("PDF generated successfully.");
            Desktop.getDesktop().open(new File("E:\\Coding\\ReportGenerator"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String str[]){
        GenerateReportPDF pdf = new GenerateReportPDF("EE202108");
    }
}