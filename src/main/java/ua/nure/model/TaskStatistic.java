package ua.nure.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@ToString(exclude = {"task", "user"})
@Entity
public class TaskStatistic {

    @Id @GeneratedValue
    private long id;

    private int fails;
    private boolean completed;
    @Lob private String solution;

    @OneToOne   private Task task;
    @ManyToOne  private User user;

    public void failed() {
        fails++;
    }

    public static TaskStatistic create(User user, Task task){
        TaskStatistic taskStatistic = new TaskStatistic();

        taskStatistic.setUser(user);
        taskStatistic.setTask(task);

        return taskStatistic;
    }
}
