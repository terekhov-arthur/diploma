package ua.nure.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Level {

    @Id @GeneratedValue private long id;

    @OneToMany(mappedBy = "level")
    private List<Task> tasks;

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public List<Task> getTasks()
    {
        return tasks;
    }
    public void setTasks(List<Task> tasks)
    {
        this.tasks = tasks;
    }
}
