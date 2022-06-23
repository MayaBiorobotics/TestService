package com.requestdesign.testingservice.service.code;

import com.requestdesign.testingservice.dto.code.CodeDto;
import com.requestdesign.testingservice.entity.phrase.Code;
import com.requestdesign.testingservice.exceptions.code.CodeNotFoundException;
import com.requestdesign.testingservice.repository.code.CodeRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CodeService {
    private static int STANDART_CODE_LENGTH = 7;
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public List<Code> findAllCodes() {
        List<Code> codes = codeRepository.findAllCodes();
        return codes;
    }

    public String createCode(CodeDto codeDto) {
        String code = RandomString.make(STANDART_CODE_LENGTH);
        codeRepository.generateCode(code, codeDto);
        return code;
    }

    public Code getCode(String codeString) throws CodeNotFoundException {
        Code code = codeRepository.findCode(codeString);
        return code;
    }

    public void setAsUsed(String code) {
        codeRepository.setAsUsed(code);
    }
}
