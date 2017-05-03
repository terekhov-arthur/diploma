package ua.nure.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InputParameter {

    @Id @GeneratedValue
    private long id;

    private String name;
    private Type type;

    public InputParameter() {
    }

    public InputParameter(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {

        INT("int"), DOUBLE("double"), STRING("String"), BOOLEAN("boolean"),
        INT_ARRAY("int[]"), DOUBLE_ARRAY("double[]"), STRING_ARRAY("String[]"), BOOLEAN_ARRAY("boolean[]"),
        INT_LIST("List<Integer>"), DOUBLE_LIST("List<Double>"), STRING_LIST("List<String>"), BOOLEAN_LIST("List<Boolean>");

        private String value;

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
