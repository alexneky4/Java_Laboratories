package uaic.info.employee;

import java.util.Objects;

public class Designer extends Person{
     private String typeOfDesigner;

    public Designer(String name, String birthdate, String typeOfDesigner) {
        super(name, birthdate);
        this.typeOfDesigner = typeOfDesigner;
    }

    public String getTypeOfDesigner() {
        return typeOfDesigner;
    }

    public void setTypeOfDesigner(String typeOfDesigner) {
        this.typeOfDesigner = typeOfDesigner;
    }

}
