package com.requestdesign.testingservice.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class QuestionAnswer {
    private Long id;
    private Long testResultId;
    private Long questionId;
    private Long variantId;
}
