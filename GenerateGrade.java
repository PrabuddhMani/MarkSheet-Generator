import java.util.Arrays;

public class GenerateGrade {
    
    String grade(int CA1, int CA2, int CA3, int MTE, int ETE){
        int CA[] = {CA1, CA2, CA3};
        Arrays.sort(CA);
        int total = (CA[0] + CA[1] + MTE + ETE);
        if (total>=90)
            return "A+";
        else if (total>=80)
            return "A";
        else if (total>=70)
            return "B+";
        else if (total>=60)
            return "B";
        else if (total>=50)
            return "C+";
        else if (total>=40)
            return "C";
        else
            return "F";    
    }
}
