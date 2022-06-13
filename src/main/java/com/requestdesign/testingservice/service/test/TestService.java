package com.requestdesign.testingservice.service.test;

import com.requestdesign.testingservice.dto.test.TestCreateDto;
import com.requestdesign.testingservice.entity.test.Question;
import com.requestdesign.testingservice.entity.test.Task;
import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.exceptions.test.QuestionNotFoundException;
import com.requestdesign.testingservice.exceptions.test.TaskNotFoundException;
import com.requestdesign.testingservice.repository.test.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class TestService {
    @Autowired
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test findTestById(Long id) {
        Test test = testRepository.findById(id);
        return null;
    }

    public Question findQuestionById(Long id) throws QuestionNotFoundException {
        Question question = testRepository.findQuestionById(id);
        return question;
    }

    public Task findTaskById(Long id) throws TaskNotFoundException {
        Task task = testRepository.findTaskById(id);
        return task;
    }

    public Set<Task> findTasksByTestId(Long id) {
        Set<Task> task = testRepository.findTasksByTestId(id);
        return task;
    }

    public List<Test> findAllTests() {
        List<Test> tests = testRepository.findAllTests();
        return tests;
    }

    public List<Task> findAllTasks() {
        List<Task> tasks = testRepository.findAllTasks();
        return tasks;
    }

    public List<Question> findAllQuestions() {
        List<Question> questions = testRepository.findAllQuestions();
        return questions;
    }

    public void saveTest(TestCreateDto test) {
        testRepository.saveTest(test);
    }
}
