package uaic.info.employee;

import java.util.ArrayList;
import java.util.List;

public class Programmer extends Person{

    private List<String> knownProgrammingLanguages = new ArrayList<>();

    public Programmer(String name, String birthdate, List<String> knownProgrammingLanguages) {
        super(name, birthdate);
        this.knownProgrammingLanguages = knownProgrammingLanguages;
    }

    public List<String> getKnownProgrammingLanguages() {
        return knownProgrammingLanguages;
    }

    public void setKnownProgrammingLanguages(List<String> knownProgrammingLanguages) {
        this.knownProgrammingLanguages = knownProgrammingLanguages;
    }
}
