package com.requestdesign.testingservice.entity.test;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
public class TaskBlock {
    private Long id;
    private String title;
    private Set<Task> tasks;
}
