package ua.nure.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {

    @Id @GeneratedValue
    private long id;

    private String name;
    private String description;
    private String sourceClassName;
    private String testClassName;

    @ManyToOne
    private User owner;

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

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceClassName()
    {
        return sourceClassName;
    }

    public void setSourceClassName(String sourceClassName)
    {
        this.sourceClassName = sourceClassName;
    }

    public String getTestClassName()
    {
        return testClassName;
    }

    public void setTestClassName(String testClassName)
    {
        this.testClassName = testClassName;
    }
}
