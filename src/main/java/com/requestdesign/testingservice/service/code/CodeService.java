package com.requestdesign.testingservice.service.code;

import com.requestdesign.testingservice.entity.phrase.Code;
import com.requestdesign.testingservice.repository.code.CodeRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CodeService {
    private static int STANDART_CODE_LENGTH = 8;
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
        codeRepository.addCode(code, codeDto);
        return code;
    }
}
