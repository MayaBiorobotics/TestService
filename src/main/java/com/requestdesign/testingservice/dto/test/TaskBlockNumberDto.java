package com.requestdesign.testingservice.dto.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskBlockNumberDto {
    @JsonProperty("task_block_id")
    private Long taskBlockId;
    @JsonProperty("number")
    private Number Integer;
}
