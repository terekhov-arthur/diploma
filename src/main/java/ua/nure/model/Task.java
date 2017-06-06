package ua.nure.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Label> labels;

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

    public List<Label> getLabels()
    {
        return labels;
    }
    public void setLabels(List<Label> labels)
    {
        this.labels = labels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Task task = (Task) o;

        if (id != task.id)
            return false;
        if (name != null ? !name.equals(task.name) : task.name != null)
            return false;
        if (description != null ? !description.equals(task.description) : task.description != null)
            return false;
        if (source != null ? !source.equals(task.source) : task.source != null)
            return false;
        if (test != null ? !test.equals(task.test) : task.test != null)
            return false;
        if (owner != null ? !owner.equals(task.owner) : task.owner != null)
            return false;
        if (level != null ? !level.equals(task.level) : task.level != null)
            return false;
        return labels != null ? labels.equals(task.labels) : task.labels == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (test != null ? test.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (labels != null ? labels.hashCode() : 0);
        return result;
    }
}
