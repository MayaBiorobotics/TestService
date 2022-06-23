package com.requestdesign.testingservice.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.requestdesign.testingservice.entity.result.QuestionAnswer;
import com.requestdesign.testingservice.entity.result.TaskAnswer;
import com.requestdesign.testingservice.entity.test.Test;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ClientTestResultDto {
    @JsonProperty("test_id")
    private Long testId;
    private String code;
    @JsonProperty("question_answers")
    private List<QuestionAnswerDto> questionAnswers;
    @JsonProperty("task_answers")
    private List<TaskAnswerDto> taskAnswers;
}
