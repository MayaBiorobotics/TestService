package com.requestdesign.testingservice.dto.test.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class QuestionBlockCreateDto {
    private String title;
    @JsonProperty("question_ids")
    private ArrayList<Long> questionIds;
}
