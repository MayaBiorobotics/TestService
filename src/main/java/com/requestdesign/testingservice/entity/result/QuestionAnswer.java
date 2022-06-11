package com.requestdesign.testingservice.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionAnswer {
    private Long id;
    private Long testResultId;
    private Long questionId;
    private Long variantId;
}
