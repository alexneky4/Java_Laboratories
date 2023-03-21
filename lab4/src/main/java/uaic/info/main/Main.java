package uaic.info.main;

import com.github.javafaker.Faker;
import uaic.info.problem.Algorithm;
import uaic.info.problem.ProblemInstance;
import uaic.info.projects.Project;
import uaic.info.student.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        List<Student> studentList = new LinkedList<>();
        Set<Project> projectSet = new TreeSet<>();

        studentList = IntStream.rangeClosed(0,2).mapToObj(i -> new Student("S" + String.valueOf(i)))
                            .sorted().collect(Collectors.toList());
        projectSet = IntStream.rangeClosed(0,2).mapToObj(i -> new Project("P" + String.valueOf(i)))
                        .sorted().collect(Collectors.toSet());


       /* Faker faker = new Faker();
        studentList.stream().forEach(s -> s.setName(faker.name().firstName()));
        projectSet.stream().forEach(p -> p.setName(faker.name().title()));

        System.out.println(studentList);
        System.out.println(projectSet);
        */

        List<Project> projectList = new ArrayList<>(projectSet);
        Collections.sort(projectList);

        ProblemInstance instance = new ProblemInstance();
        instance.setProjects(projectList);
        instance.setStudents(studentList);
        Map<Student,List<Project>> preferences = new HashMap<>();

        preferences.put(studentList.get(0),
                Arrays.asList(projectList.get(0), projectList.get(1), projectList.get(2)));
        preferences.put(studentList.get(1),
                Arrays.asList(projectList.get(0), projectList.get(1)));
        preferences.put(studentList.get(2),
                Arrays.asList(projectList.get(0)));

        instance.setPreferences(preferences);
        System.out.println(instance.getPreferences());
        System.out.println(Algorithm.maximumMatching(instance));
    }
}