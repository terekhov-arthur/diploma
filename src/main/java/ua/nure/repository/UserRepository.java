package ua.nure.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
