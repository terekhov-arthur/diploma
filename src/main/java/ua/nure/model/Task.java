package ua.nure.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Entity
public class Task<T> {

    @Id @GeneratedValue
    private long id;

    private String title;
    private String description;
    private InputParameter.Type returnValueType;

    @OneToMany private List<InputParameter> params;
    //@OneToMany private List<TaskValidator<T>> validators;
    private static final String METHOD_TEMPLATE = "public %s %s";

    public Task() {
        params = new ArrayList<>();
        //validators = new ArrayList<>();
    }

    public String getMethodSignature(){
        String collect = params.stream().map(p -> p.getType() + " " + p.getName())
                               .collect(Collectors.joining(", ", "(", ")"));
        return String.format(METHOD_TEMPLATE, returnValueType, collect);
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

//    public List<TaskValidator<T>> getValidators() {
//        return validators;
//    }
//    public void setValidators(List<TaskValidator<T>> validators) {
//        this.validators = validators;
//    }

    public List<InputParameter> getParams() {
        return params;
    }
    public void setParams(List<InputParameter> params) {
        this.params = params;
    }

    public InputParameter.Type getReturnValueType() {
        return returnValueType;
    }
    public void setReturnValueType(InputParameter.Type returnValueType) {
        this.returnValueType = returnValueType;
    }
}
