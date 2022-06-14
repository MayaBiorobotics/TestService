package com.requestdesign.testingservice.controller.code;

import com.requestdesign.testingservice.dto.code.CodeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CodeControllerInterface {
    @GetMapping("/")
    public ResponseEntity getAllCodes();

    @PostMapping("/")
    public ResponseEntity createCode(@RequestBody CodeDto codeDto);
}
