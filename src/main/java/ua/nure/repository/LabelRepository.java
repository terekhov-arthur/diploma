package ua.nure.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.model.Label;

import java.util.List;

public interface LabelRepository extends CrudRepository<Label, Long> {
    List<Label> findAllByValueIgnoreCaseContains(String value);
}
