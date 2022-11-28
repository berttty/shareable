package io.bertty.sharable.backend.web.controller;

import io.bertty.sharable.backend.persistence.model.Memory;
import io.bertty.sharable.backend.persistence.repository.MemoryRepository;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoriesController {

  Logger logger = LoggerFactory.getLogger(MemoriesController.class);

  @Autowired
  MemoryRepository repository;

  @Autowired
  private RedisTemplate<String, Memory> redisTemplate;

  @PostMapping(value = "/memory")
  public ResponseEntity<Memory> addMemory(@RequestBody Memory memory2add, final HttpServletResponse response){
    Date now = Date.from(Instant.now());
    memory2add = memory2add.setId("myide_"+now.toString())
                    .setTimestamp(now);

    repository.save(memory2add);

    return ResponseEntity.ok().body(memory2add);
  }

  @GetMapping(value = "/memories")
  public ResponseEntity<List<Memory>> allMemory(final HttpServletResponse response){

    List<Memory> list = StreamSupport.stream(repository.findAll().spliterator(),
            false)
        .collect(Collectors.toList());


    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/memories/count")
  public ResponseEntity<String> countMemory(final HttpServletResponse response){
    int size = this.redisTemplate.keys("Memory:*").size();

    this.logger.info(String.valueOf(size));
    return ResponseEntity.ok().body(""+size);

  }


  @GetMapping(value = "/memory")
  public ResponseEntity<Memory> getRandomMemory(final HttpServletResponse response){
    String key = getRandomKey();
    Optional<Memory> memory = this.repository.findById(key);

    return ResponseEntity.ok().body(memory.get());
  }

  private String getRandomKey(){
    String key;
    do {
      key = this.redisTemplate.randomKey();
      if(key.length() < 7) {
        this.logger.info("searching for:" + key);
      }
    }while(key.length() < 7);
    key = key.substring(7);
    return key;
  }
//
//
//  @GetMapping(value = "/{id}/custom-etag")
//  public ResponseEntity<Memory> get(@PathVariable("id") final Long id,
//      final HttpServletResponse response) {
//    final Foo foo = RestPreconditions.checkFound(service.findById(id));
//
//    eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
//    return ResponseEntity.ok()
//        .eTag(Long.toString(foo.getVersion()))
//        .body(foo);
//  }
}
