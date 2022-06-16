package com.requestdesign.testingservice.entity.test;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TaskBlock {
    private Long id;
    private String title;
    private Integer number;
    private Set<Task> tasks;
}
