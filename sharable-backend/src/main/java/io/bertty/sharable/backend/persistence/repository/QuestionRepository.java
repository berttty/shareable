package io.bertty.sharable.backend.persistence.repository;

import io.bertty.sharable.backend.persistence.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, String> {

}
