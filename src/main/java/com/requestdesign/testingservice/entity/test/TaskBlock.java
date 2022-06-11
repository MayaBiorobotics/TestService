package com.requestdesign.testingservice.entity.test;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskBlock {
    private Long id;
    private Long taskId;
    private String title;
}
