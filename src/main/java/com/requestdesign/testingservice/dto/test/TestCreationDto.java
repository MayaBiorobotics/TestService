package com.requestdesign.testingservice.dto.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCreationDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("region")
    private String region;
    @JsonProperty("url")
    private String url;
    @JsonProperty("comment")
    private String comment;
    @JsonProperty("question_block_ids")
    private List<Long> questionBlockIds;
    @JsonProperty("task_block_numbers")
    private List<TaskBlockNumberDto> taskBlockNumberDtos;
}
