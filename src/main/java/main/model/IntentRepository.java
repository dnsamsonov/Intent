package main.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentRepository extends CrudRepository<Intent, Integer> {
}
