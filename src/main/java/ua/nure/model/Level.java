package ua.nure.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Level {

    @Id @GeneratedValue private long id;

    @OneToMany(mappedBy = "level", fetch = FetchType.EAGER)
    private Set<Task> tasks;

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public Set<Task> getTasks()
    {
        return tasks;
    }
    public void setTasks(Set<Task> tasks)
    {
        this.tasks = tasks;
    }
}
