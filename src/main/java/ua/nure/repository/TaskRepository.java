package ua.nure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{
    List<Task> findAll();
}
