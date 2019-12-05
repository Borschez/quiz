package ru.borsch.quizserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.borsch.quizserver.model.Answer;
import ru.borsch.quizserver.model.Question;
import ru.borsch.quizserver.model.Subject;
import ru.borsch.quizserver.model.User;
import ru.borsch.quizserver.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

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
    private AnswerService answerService;

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

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public ResponseEntity<?> createQuestion(@RequestBody Question question, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Question : {}", question);

        Set<Answer> answerOptions = question.getAnswerOptions();
        if (!answerOptions.isEmpty()) {
            answerOptions.stream()
                    .forEach(answer -> answerService.saveAnswer(answer));
        }

        Set<Answer> correctAnswers = question.getCorrectAnswers();
        if (!correctAnswers.isEmpty()) {
            correctAnswers.stream()
                    .forEach(answer -> answerService.saveAnswer(answer));
        }

        questionService.saveQuestion(question);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/question/{id}").buildAndExpand(question.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
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

    @RequestMapping(value = "/question/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Question> updateQuestionById(@PathVariable("id") Long id, @RequestBody Question questionReq) {
        logger.info("Fetching Question with id {}", id);
        Question question = questionService.findById(id);
        if (question == null) {
            logger.error("Unable to update. Question with id {} not found.", id);
            return new ResponseEntity(new CustomError("Unable to update. Question with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        Set<Answer> answerOptions = questionReq.getAnswerOptions();
        if (!answerOptions.isEmpty()) {
            answerOptions.stream()
                    .forEach(answer -> answerService.saveAnswer(answer));
        }

        Set<Answer> correctAnswers = questionReq.getCorrectAnswers();
        if (!correctAnswers.isEmpty()) {
            correctAnswers.stream()
                    .forEach(answer -> answerService.saveAnswer(answer));
        }

        question = questionService.saveQuestion(questionReq);

        return new ResponseEntity<Question>(question, HttpStatus.OK);
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> getAllQuestions() {
        logger.info("Fetching All Questions");
        List<Question> questions = questionService.findAll();
        if (questions.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
    }

    @RequestMapping(value = "/answer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Answer> deleteAnswer(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Answer with id {}", id);

        Answer answer = answerService.findById(id);
        if (answer == null) {
            logger.error("Unable to delete. Answer with id {} not found.", id);
            return new ResponseEntity(new CustomError("Unable to delete. Answer with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        Question question = questionService.findByAnswer(answer);
        if (question != null) {
            question.getAnswerOptions().remove(answer);
            questionService.saveQuestion(question);

            //answerService.deleteAnswer(answer);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
