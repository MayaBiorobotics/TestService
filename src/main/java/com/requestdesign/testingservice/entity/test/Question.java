package com.requestdesign.testingservice.entity.test;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Question {
    private Long id;
    private String text;
    private String picture;
    private Set<QuestionVariant> questionVariants;
}
