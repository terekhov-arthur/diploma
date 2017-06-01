package ua.nure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.model.Level;

import java.util.List;

@Repository
public interface LevelRepository extends CrudRepository<Level, Long> {
    List<Level> findAll();
}
