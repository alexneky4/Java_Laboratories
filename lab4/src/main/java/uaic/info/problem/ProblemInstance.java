package uaic.info.problem;

import uaic.info.projects.Project;
import uaic.info.student.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public void generateRandomInstance()
    {
        int nrOfStudents = (int)Math.random() * 1000 + 1000;
        int nrOfProjects = (int)Math.random() * 1000 + 1000;
        int nrOfEdges = (int)Math.random() * (nrOfStudents * nrOfProjects) + 1;
        this.students = IntStream.rangeClosed(0,nrOfStudents).mapToObj(i -> new Student("S" + String.valueOf(i)))
                .sorted().collect(Collectors.toList());
        this.projects = IntStream.rangeClosed(0,nrOfProjects).mapToObj(i -> new Project("P" + String.valueOf(i)))
                .sorted().collect(Collectors.toList());
        for(Student student : this.students)
        {
            List<Project> projectList = new ArrayList<>();
            for(Project project : this.projects)
            {
                double decision = Math.random();
                if(decision < 0.5)
                    continue;
                else
                    projectList.add(project);
            }

            preferences.put(student,projectList);
        }

    }
}
