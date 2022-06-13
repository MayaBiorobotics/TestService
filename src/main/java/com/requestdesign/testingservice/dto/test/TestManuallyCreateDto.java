package com.requestdesign.testingservice.dto.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.requestdesign.testingservice.dto.test.question.QuestionBlockCreateDto;
import com.requestdesign.testingservice.dto.test.question.QuestionBlockManuallyCreateDto;
import com.requestdesign.testingservice.dto.test.task.TaskBlockManuallyCreateDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class TestManuallyCreateDto {
    private String title;
    private String region;
    private String comment;
    @JsonProperty("question_blocks")
    private ArrayList<QuestionBlockManuallyCreateDto> questionBlocks;
    @JsonProperty("task_blocks")
    private ArrayList<TaskBlockManuallyCreateDto> taskBlocks;
}
