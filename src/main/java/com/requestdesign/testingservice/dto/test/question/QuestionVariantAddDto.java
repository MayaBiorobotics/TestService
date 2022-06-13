package com.requestdesign.testingservice.dto.test.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionVariantAddDto {
    @JsonProperty("question_id")
    private Long question_id;
    private String text;
    private String picture;
}
