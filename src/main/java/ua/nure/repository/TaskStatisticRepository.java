package ua.nure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.model.Task;
import ua.nure.model.TaskStatistic;
import ua.nure.model.User;

@Repository
public interface TaskStatisticRepository extends CrudRepository<TaskStatistic, Long> {
    TaskStatistic findByUserAndTask(User user, Task task);
}
