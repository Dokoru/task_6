package Marks;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        StudentsMarksMap studentsMarksMap = new StudentsMarksMap();
        studentsMarksMap.readFromCSV("info.csv");
        SimpleHashMap<StudentMarks, ArrayList<String>> hm = studentsMarksMap.uniteStudentsByMarks();
        String s = studentsMarksMap.toPrintStudentUnits(hm);
        System.out.println(s);
    }
}
