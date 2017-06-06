package ua.nure.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.model.Label;

import java.util.Set;

public interface LabelRepository extends CrudRepository<Label, Long> {
    Set<Label> findAllByValueIgnoreCaseContains(String value);
    Set<Label> findAllByValueIgnoreCaseIn(Set<String> labels);
}
