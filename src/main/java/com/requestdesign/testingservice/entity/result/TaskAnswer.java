package com.requestdesign.testingservice.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskAnswer {
    private Long id;
    private Long testResultId;
    private Long taskId;
    private String text;
    private Integer number;
}
