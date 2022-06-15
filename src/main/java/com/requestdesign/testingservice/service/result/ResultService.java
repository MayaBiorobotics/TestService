package com.requestdesign.testingservice.service.result;

import com.requestdesign.testingservice.entity.result.QuestionAnswer;
import com.requestdesign.testingservice.entity.result.TaskAnswer;
import com.requestdesign.testingservice.entity.result.TestResult;
import com.requestdesign.testingservice.exceptions.result.QuestionAnswerNotFoundException;
import com.requestdesign.testingservice.exceptions.result.ResultNotFoundException;
import com.requestdesign.testingservice.exceptions.result.TaskAnswerNotFoundException;
import com.requestdesign.testingservice.repository.result.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
    private final ResultRepository resultRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public TestResult findById(Long resultId) throws ResultNotFoundException {
        TestResult testResult = resultRepository.findResultById(resultId);
        return testResult;
    }

    public TaskAnswer findTaskFromResultById(Long resultId, Long taskId) throws TaskAnswerNotFoundException {
        TaskAnswer taskAnswer = resultRepository.findTaskFromResultById(resultId, taskId);
        return taskAnswer;
    }

    public QuestionAnswer findQuestionAnswerById(Long resultId, Long questionId) throws QuestionAnswerNotFoundException {
        QuestionAnswer questionAnswer = resultRepository.findQuestionAnswerFromResultById(resultId, questionId);
        return questionAnswer;
    }
}
