package com.requestdesign.testingservice.dto.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskAnswerDto {
    @JsonProperty("task_id")
    private Long taskId;
    private String text;
}
