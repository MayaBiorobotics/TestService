package com.requestdesign.testingservice.service.test;

import com.requestdesign.testingservice.entity.test.Test;
import com.requestdesign.testingservice.repository.test.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {
    @Autowired
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test findById(Long id) {
        Test test = testRepository.findById(id);
        if(account == null) {
            throw new TestNotFoundException("Id = " + id + " аккаунта с таким id нет в БД");
        }
        return test;
    }
}
