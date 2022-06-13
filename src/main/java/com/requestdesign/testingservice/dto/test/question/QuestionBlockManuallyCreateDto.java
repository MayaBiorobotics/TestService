package com.requestdesign.testingservice.dto.test.question;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class QuestionBlockManuallyCreateDto {
    private ArrayList<QuestionManuallyCreateDto> questionDto;
}
