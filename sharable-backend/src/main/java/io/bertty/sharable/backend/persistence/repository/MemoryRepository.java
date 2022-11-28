package io.bertty.sharable.backend.persistence.repository;

import io.bertty.sharable.backend.persistence.model.Memory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends CrudRepository<Memory, String> {

}
