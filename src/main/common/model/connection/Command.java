package common.model.connection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Command implements Serializable {

    private Instruction name;

    private List<String> parameters;

    public Command(Instruction name, String... parameters) {
        this.name = name;
        this.parameters = new ArrayList<>();
        Collections.addAll(this.parameters, parameters);
    }

    public Command(Instruction name, List<String> parameters)
    {
        this.name = name;
        this.parameters = parameters;
    }

    public Instruction getName() {
        return name;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
