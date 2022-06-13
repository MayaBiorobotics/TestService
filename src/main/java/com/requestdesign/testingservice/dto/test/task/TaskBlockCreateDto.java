package com.requestdesign.testingservice.dto.test.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class TaskBlockCreateDto {
    private String title;
    @JsonProperty("task_ids")
    private ArrayList<Long> taskIds;
}
