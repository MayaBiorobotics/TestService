package com.requestdesign.testingservice.dto.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.requestdesign.testingservice.entity.test.QuestionBlock;
import com.requestdesign.testingservice.entity.test.TaskBlock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCreateDto {
    private String title;
    private String region;
    private String url;
    private String comment;
    @JsonProperty("question_block_ids")
    private ArrayList<Long> questionBlockIds;
    @JsonProperty("task_block_numbers")
    private ArrayList<TaskBlockNumberDto> taskBlockNumberDtos;
}
