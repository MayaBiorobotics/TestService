package com.requestdesign.testingservice;

import com.requestdesign.testingservice.controller.test.TestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestControllerTests {
    @Autowired
    private TestController testController;
    @Test
    public void createTest() {
        assertThat(testController).isNotNull();
    }

    @Test
    public void createTestManually() {

    }

    @Test
    public void incorrectCreateTest() {

    }
}
