import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentReader {
    
    // Student class to represent individual students
    public static class Student {
        private String name;
        private int age;
        private String grade;

        public Student(String name, int age, String grade) {
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", grade='" + grade + '\'' +
                    '}';
        }
    }

    // Method to read students from a file
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

    // Method to create random groups of students
    public List<List<Student>> createRandomGroups(List<Student> students, int groupSize) {
        List<List<Student>> groups = new ArrayList<>();
        Collections.shuffle(students); // Shuffle the list to randomize order
        for (int i = 0; i < students.size(); i += groupSize) {
            int end = Math.min(i + groupSize, students.size());
            List<Student> group = new ArrayList<>(students.subList(i, end));
            groups.add(group);
        }
        return groups;
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        
        // Read students from a file
        List<Student> students = manager.readStudents("students.txt");
        
        // Display the list of students
        System.out.println("List of Students:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Create random groups
        List<List<Student>> randomGroups = manager.createRandomGroups(students, 2); // Change group size as needed

        // Print the random groups
        System.out.println("\nRandom Groups:");
        for (int i = 0; i < randomGroups.size(); i++) {
            System.out.println("Group " + (i + 1) + ": " + randomGroups.get(i));
        }
    }
}