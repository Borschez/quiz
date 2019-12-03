package ru.borsch.quizserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.borsch.quizserver.model.Question;
import ru.borsch.quizserver.model.Subject;
import ru.borsch.quizserver.model.User;
import ru.borsch.quizserver.service.CustomError;
import ru.borsch.quizserver.service.QuestionService;
import ru.borsch.quizserver.service.SubjectService;
import ru.borsch.quizserver.service.UserService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RestApiController {
    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/user-info")
    public ResponseEntity<User> getUserInfo(){
        logger.info("Fetching current user info");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(auth.getName());
        if (user == null) {
            logger.error("User with name {} not found.", auth.getName());
            return new ResponseEntity(new CustomError("User with name " + auth.getName()
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") Long id) {
        logger.info("Fetching Question with id {}", id);
        Question question = questionService.findById(id);
        if (question == null) {
            logger.error("Question with id {} not found.", id);
            return new ResponseEntity(new CustomError("Question with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Question>(question, HttpStatus.OK);
    }

    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET)
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id") Long id) {
        logger.info("Fetching Subject with id {}", id);
        Subject subject = subjectService.findById(id);
        if (subject == null) {
            logger.error("Subject with id {} not found.", id);
            return new ResponseEntity(new CustomError("Subject with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }
}
