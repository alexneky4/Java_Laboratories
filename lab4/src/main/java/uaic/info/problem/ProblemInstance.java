package uaic.info.problem;

import uaic.info.projects.Project;
import uaic.info.student.Student;

import java.util.*;

public class ProblemInstance {

    private Map<Student, List<Project>> preferences = new HashMap<>();
    private List<Student> students = new LinkedList<>();
    private List<Project> projects = new ArrayList<>();
    public ProblemInstance()
    {

    }

    public Map<Student, List<Project>> getPreferences() {
        return preferences;
    }

    public void setPreferences(Map<Student, List<Project>> preferences) {
        this.preferences = preferences;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    private double averagePreference = preferences.keySet().stream().mapToInt(k ->preferences.get(k).size()).average().orElse(0);

    public void displayUnderAverage(){
        preferences.keySet().stream().filter(k -> preferences.get(k).size() < averagePreference)
                .forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "ProblemInstance{" +
                "preferences=" + preferences +
                ", students=" + students +
                ", projects=" + projects +
                '}';
    }
}
