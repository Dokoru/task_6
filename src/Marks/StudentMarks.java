package Marks;

import java.util.HashMap;

public class StudentMarks implements Comparable<StudentMarks> {

    private SimpleHashMap<String, String> subjectsAndMarks;

    public StudentMarks() {
        this.subjectsAndMarks = new SimpleHashMap<>();
    }

    public SimpleHashMap<String, String> getSubjectAndMarks() {
        return subjectsAndMarks;
    }

    protected void addSubjectAndMark(String subject, String mark) {
        subjectsAndMarks.put(subject, mark);
    }

    public int compareTo(StudentMarks studentMarks) {
        int temp = 0, subjectMatch = 0;
        SimpleHashMap<String, String> subjectsAndMarks = studentMarks.getSubjectAndMarks();
        for (HashMap.Entry<String, String> entry1: this.subjectsAndMarks.entrySet()) {
            for (HashMap.Entry<String, String> entry2: subjectsAndMarks.entrySet()) {
                if (temp == 0) {
                    if (entry1.getKey().equals(entry2.getKey())) {
                        temp++;
                        subjectMatch++;
                        if (!entry1.getValue().equals(entry2.getValue())) {
                            return 1;
                        }
                    }
                }
            }
            temp = 0;
        }
        return subjectMatch == this.subjectsAndMarks.size ? 0 : 1;
    }
}
