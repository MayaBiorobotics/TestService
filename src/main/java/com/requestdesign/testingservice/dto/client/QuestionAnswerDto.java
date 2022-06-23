package com.requestdesign.testingservice.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionAnswerDto {
    @JsonProperty("question_id")
    private Long questionId;
    @JsonProperty("variant_id")
    private Long variantId;
}
