package io.bertty.sharable.backend.web.controller;

import io.bertty.sharable.backend.IDGenerator;
import io.bertty.sharable.backend.persistence.model.QRLocation;
import io.bertty.sharable.backend.persistence.repository.MemoryRepository;
import io.bertty.sharable.backend.persistence.repository.QRLocationRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "qr")
@RequestMapping("/qr/")
public class QRController {

  @Autowired
  QRLocationRepository repository;

  @PostMapping(value = "/")
  public ResponseEntity<String> addLocation(@RequestBody QRLocation qrLocation){
    qrLocation.id = IDGenerator.generate();
    repository.save(qrLocation);
    return ResponseEntity.ok(qrLocation.id);
  }

  @GetMapping(value ="/")
  public ResponseEntity<List<QRLocation>> allLocations(){
    return ResponseEntity.ok(
        StreamSupport.stream(
            this.repository.findAll().spliterator(),
            false
        ).collect(Collectors.toList())
    );
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Boolean> deleteLocation(@PathVariable String qrId){
    this.repository.deleteById(qrId);
    return ResponseEntity.ok(true);
  }
}
