package com.requestdesign.testingservice.entity.test;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Test {
    private Long id;
    private String title;
    private String url;
    private String region;
    private String comment;
    private Set<QuestionBlock> questionBlocks;
    private Set<TaskBlock> taskBlocks;
    private Set<String> phrases;
}
