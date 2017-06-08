package ua.nure.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TaskStatistic {

    @Id @GeneratedValue
    private long id;

    private int fails;
    private boolean completed;
    @Lob private String solution;

    @OneToOne   private Task task;
    @ManyToOne  private User user;

    public long getId()
    {
        return id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public Task getTask()
    {
        return task;
    }
    public void setTask(Task task)
    {
        this.task = task;
    }

    public User getUser()
    {
        return user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }

    public int getFails()
    {
        return fails;
    }
    public void setFails(int attempts)
    {
        this.fails = attempts;
    }
    public void failed() {
        fails++;
    }

    public boolean isCompleted()
    {
        return completed;
    }
    public void setCompleted(boolean completed)
    {
        this.completed = completed;
    }

    public String getSolution() {
        return solution;
    }
    public void setSolution(String solution) {
        this.solution = solution;
    }
    
    public static TaskStatistic create(User user, Task task){
        TaskStatistic taskStatistic = new TaskStatistic();

        taskStatistic.setUser(user);
        taskStatistic.setTask(task);

        return taskStatistic;
    }
}
