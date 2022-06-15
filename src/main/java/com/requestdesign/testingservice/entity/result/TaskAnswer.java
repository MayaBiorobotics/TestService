package com.requestdesign.testingservice.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TaskAnswer {
    private Long id;
    private Long testResultId;
    private Long taskId;
    private String text;
    private Integer number;
}
