import java.util.*;
public class StudentSystem {
    private Map<String, Student> studentsRepository;
    public StudentSystem() {
        this.studentsRepository = new HashMap<>();
    }

    public Map<String, Student> getStudentsRepository() {
        return this.studentsRepository;
    }
    public void parseCommand(String[] args) {
        if ("Create".equals(args[0])) {
            this.createStudent(Arrays.stream(args).skip(1).toArray(String[]::new));
        } else if ("Show".equals(args[0])) {
            this.show(args[1]);
        }
    }
    public void createStudent(String[] args) {
        var name = args[0];
        var age = Integer.parseInt(args[1]);
        var grade = Double.parseDouble(args[2]);
        if (!studentsRepository.containsKey(name)) {
            var student = new Student(name, age, grade);
            studentsRepository.put(name, student);
        }
    }
    public void show(String name) {
        if (studentsRepository.containsKey(name)) {
            var student = studentsRepository.get(name);
            System.out.println(student);
        }
    }
}

