package Marks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class StudentsMarksMap {

    private ArrayList<String> studentsList;
    private SimpleHashMap<String, StudentMarks> studentsMarksMap;

    public StudentsMarksMap() {
        studentsMarksMap = new SimpleHashMap<>();
        studentsList = new ArrayList<>();
    }

    private void addStudentAndMarks (String student, StudentMarks subjectsAndMarks) {
        studentsMarksMap.put(student, subjectsAndMarks);
        studentsList.add(student);
    }

    private StudentMarks getStudentMarks(String student) {
        return studentsMarksMap.get(student);
    }

    public SimpleHashMap<String, StudentMarks> getStudentsMarksMap() {
        return studentsMarksMap;
    }

    public SimpleHashMap<StudentMarks, ArrayList<String>> uniteStudentsByMarks() {
        SimpleHashMap<StudentMarks, ArrayList<String>> studentsUnitsWithMarks = new SimpleHashMap<>();
        ArrayList<String> studentsListCopy = new ArrayList<>(studentsList);
        for (int i = 0; i < studentsListCopy.size();) {
            String student1 = studentsListCopy.get(i);
            ArrayList<String> studentUnit = new ArrayList<>();
            studentUnit.add(student1);
            for (int j = 1; j < studentsListCopy.size(); j++) {
                String student2 = studentsListCopy.get(j);
                if (studentsMarksMap.get(student1).compareTo(studentsMarksMap.get(student2)) == 0) {
                    studentUnit.add(student2);
                    studentsListCopy.remove(student2);
                    j--;
                }
            }
            studentsUnitsWithMarks.put(studentsMarksMap.get(student1), studentUnit);
            studentsListCopy.remove(student1);
        }
        return studentsUnitsWithMarks;
    }

    public String toPrintStudentUnits(SimpleHashMap<StudentMarks, ArrayList<String>> studentsUnitsWithMarks) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<StudentMarks, ArrayList<String>> entry: studentsUnitsWithMarks.entrySet()) {
            StudentMarks studentMarks = entry.getKey();
            for (Map.Entry<String, String> entryStudentMarks: studentMarks.getSubjectAndMarks().entrySet()) {
                stringBuffer.append(String.format("%s - %s, ", entryStudentMarks.getKey(), entryStudentMarks.getValue()));
            }
            stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
            stringBuffer.append(": ");

            ArrayList<String> studentsList = entry.getValue();
            for (String entryStudentsList: studentsList) {
                stringBuffer.append(String.format("%s, ", entryStudentsList));
            }
            stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public void readFromCSV(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        Scanner scanner;
        int index = 0;

        while ((line = reader.readLine()) != null) {
            scanner = new Scanner(line);
            scanner.useDelimiter(", ");
            String subject = null, student = null, mark = null;
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0)
                    subject = data;
                else if (index == 1)
                    student = data;
                else if (index == 2)
                    mark = data;
                else
                    throw new Exception("IncorrectDataInFile");
                index++;
            }
            index = 0;
            if (studentsList.contains(student)) {
                getStudentMarks(student).addSubjectAndMark(subject, mark);
            }
            else {
                StudentMarks studentMarks = new StudentMarks();
                studentMarks.addSubjectAndMark(subject, mark);
                addStudentAndMarks(student, studentMarks);
            }
        }

        reader.close();
    }
}