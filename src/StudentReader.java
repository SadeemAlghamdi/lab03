import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentReader {
    public List<Student> readStudents(String filename) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    int age = Integer.parseInt(parts[1].trim());
                    String grade = parts[2].trim();
                    students.add(new Student(name, age, grade));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing age. Make sure it's a number.");
        }

        return students;
    }
}

public class Main {
    public static void main(String[] args) {
        StudentReader studentReader = new StudentReader();
        List<Student> students = studentReader.readStudents("students.txt");

        for (Student student : students) {
            System.out.println(student);
        }
    }
}