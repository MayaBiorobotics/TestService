package com.requestdesign.testingservice.controller.code;

import com.requestdesign.testingservice.dto.code.CodeDto;
import com.requestdesign.testingservice.entity.phrase.Code;
import com.requestdesign.testingservice.service.code.CodeService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/code")
public class CodeController implements CodeControllerInterface{
    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }


    @Override
    public ResponseEntity getAllCodes() {
        List<Code> codes = codeService.findAllCodes();
        return new ResponseEntity(codes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity createCode(CodeDto codeDto) {
        String code = codeService.createCode(codeDto);
        return new ResponseEntity(code, HttpStatus.OK);
    }
}
