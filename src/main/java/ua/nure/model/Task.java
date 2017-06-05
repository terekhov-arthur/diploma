package ua.nure.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Task {

    @Id @GeneratedValue
    private long id;

    private String name;
    private String description;

    @Lob private String source;
    @Lob private String test;

    @ManyToOne
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    private Level level;

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

    public String getSource()
    {
        return source;
    }
    public void setSource(String source)
    {
        this.source = source;
    }

    public String getTest()
    {
        return test;
    }
    public void setTest(String test)
    {
        this.test = test;
    }

    public Level getLevel()
    {
        return level;
    }
    public void setLevel(Level level)
    {
        this.level = level;
    }
}
