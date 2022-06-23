package com.requestdesign.testingservice.service.client;

import com.requestdesign.testingservice.dto.client.ClientTestResultDto;
import com.requestdesign.testingservice.entity.phrase.Code;
import com.requestdesign.testingservice.exceptions.code.CodeNotFoundException;
import com.requestdesign.testingservice.exceptions.code.CodeUsedException;
import com.requestdesign.testingservice.repository.result.ResultRepository;
import com.requestdesign.testingservice.service.code.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientService {
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private CodeService codeService;

    public void saveResult(ClientTestResultDto result) throws CodeNotFoundException, CodeUsedException {
        Code code = codeService.getCode(result.getCode());
        if(Objects.equals(code.getStatus(), "used")) {
            throw new CodeUsedException("Код использован");
        }
        resultRepository.saveResult(code.getResultId(), result);
        codeService.setAsUsed(result.getCode());
    }
}
