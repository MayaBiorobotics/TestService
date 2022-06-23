package com.requestdesign.testingservice.controller.client;

import com.requestdesign.testingservice.dto.client.ClientTestResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ClientControllerInterface {
    @GetMapping("/{testId}")
    public ResponseEntity getTest(@PathVariable Long testId);

    @PostMapping("/{testId}")
    public ResponseEntity sendResult(@RequestBody ClientTestResultDto clientTestResult);
}
