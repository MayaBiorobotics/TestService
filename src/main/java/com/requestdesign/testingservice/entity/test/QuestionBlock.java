package com.requestdesign.testingservice.entity.test;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class QuestionBlock {
    private Long id;
    private String title;
    private List<Question> questions;
}
