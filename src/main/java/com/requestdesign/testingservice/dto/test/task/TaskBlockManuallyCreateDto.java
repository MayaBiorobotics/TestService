package com.requestdesign.testingservice.dto.test.task;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class TaskBlockManuallyCreateDto {
    private ArrayList<TaskDto> tasks;
}
