package io.bertty.sharable.backend.persistence.repository;

import io.bertty.sharable.backend.persistence.model.QRLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRLocationRepository extends CrudRepository<QRLocation, String> {

}
