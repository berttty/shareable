package io.bertty.sharable.backend.web.controller;

import io.bertty.sharable.backend.IDGenerator;
import io.bertty.sharable.backend.persistence.model.QRLocation;
import io.bertty.sharable.backend.persistence.model.Question;
import io.bertty.sharable.backend.persistence.repository.QRLocationRepository;
import io.bertty.sharable.backend.persistence.repository.QuestionRepository;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "Question")
@RequestMapping("/question/")
public class QuestionController {

  Logger logger = LoggerFactory.getLogger(QuestionController.class);

  List<Question> questions = null;

  LocalDateTime last_update = LocalDateTime.now();
  @Autowired
  QuestionRepository repository;

  @PostMapping(value = "/")
  public ResponseEntity<Question> addLocation(@RequestBody Question question){
    question.setId(IDGenerator.generate());
    repository.save(question);
    return ResponseEntity.ok(question);
  }

  @GetMapping(value ="/all")
  public ResponseEntity<List<Question>> allQuestions(){
    return ResponseEntity.ok(
        StreamSupport.stream(
            this.repository.findAll().spliterator(),
            false
        ).collect(Collectors.toList())
    );
  }

  @GetMapping(value ="/")
  public ResponseEntity<Question> getRandomQuestion(){
    LocalDateTime now = LocalDateTime.now();

    if(this.last_update.isBefore(now)){
      this.logger.info("recalculating the questions");
      if(this.questions != null) {
        this.questions.clear();
      }
      this.questions = null;
      this.last_update = LocalDateTime.now().plusSeconds(30);
    }

    if(this.questions == null) {
      this.questions = StreamSupport.stream(
          this.repository.findAll().spliterator(),
          false
      ).collect(Collectors.toList());
    }

    return ResponseEntity.ok(
        this.questions.stream()
            .skip((int) (this.questions.size() * Math.random()))
            .findFirst()
            .get()
    );
  }

  @DeleteMapping(value = "/{questionId}")
  public ResponseEntity<Boolean> deleteQuestion(@PathVariable String questionId){
    this.repository.deleteById(questionId);
    return ResponseEntity.ok(true);
  }
}
