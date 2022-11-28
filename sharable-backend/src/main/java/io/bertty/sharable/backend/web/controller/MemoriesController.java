package io.bertty.sharable.backend.web.controller;

import io.bertty.sharable.backend.IDGenerator;
import io.bertty.sharable.backend.persistence.model.Comment;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/memory")
@RequestMapping("/memory/")
public class MemoriesController {

  Logger logger = LoggerFactory.getLogger(MemoriesController.class);

  @Autowired
  MemoryRepository repository;

  @Autowired
  private RedisTemplate<String, Memory> redisTemplate;

  @PostMapping(value = "/")
  public ResponseEntity<Memory> addMemory(@RequestBody Memory memory2add, final HttpServletResponse response){
    Date now = Date.from(Instant.now());
    memory2add = memory2add.setId(IDGenerator.generate())
                    .setTimestamp(now);
    repository.save(memory2add);
    return ResponseEntity.ok().body(memory2add);
  }

  @GetMapping(value = "/all")
  public ResponseEntity<List<Memory>> allMemory(final HttpServletResponse response){

    List<Memory> list = StreamSupport.stream(repository.findAll().spliterator(),
            false)
        .collect(Collectors.toList());


    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Memory> getMemory(@PathVariable String id){
    Optional<Memory> memory = this.repository.findById(id);
    return ResponseEntity.ok().body(memory.get());
  }

  @PostMapping(value = "/{id}/comment")
  public ResponseEntity<Memory> addComment(
      @PathVariable String id,
      @RequestBody Comment comment
  ){
    Memory memory = this.repository.findById(id).get();
    this.repository.save(
        memory.addComment(comment.setId(IDGenerator.generate()))
    );
    return ResponseEntity.ok().body(memory);
  }

  @PostMapping(value = "/{id}/counter/{name}")
  public ResponseEntity<Memory> addComment(
      @PathVariable String id,
      @PathVariable String name
  ){
    Memory memory = this.repository.findById(id).get();
    switch (name){
      case "i_like_it":
        memory.addI_like_it();
        break;
      case "make_me_meditate":
        memory.addMake_me_meditate();
        break;
      case "so_sad":
        memory.addSo_sad();
        break;
      default:
        return ResponseEntity.ok(memory);
    }
    this.repository.save(memory);
    return ResponseEntity.ok().body(memory);
  }


  @GetMapping(value = "/")
  public ResponseEntity<Memory> getRandomMemory(final HttpServletResponse response){
    String key = getRandomKey();
    Optional<Memory> memory = this.repository.findById(key);

    return ResponseEntity.ok().body(memory.get());
  }

  private String getRandomKey(){
    String key;
    do {
      key = this.redisTemplate.randomKey();
      if(!key.startsWith("Memory")){
        key="";
      }
    }while(key.length() < 7);
    key = key.substring(7);
    return key;
  }

}
