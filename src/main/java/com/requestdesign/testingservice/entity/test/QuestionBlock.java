package com.requestdesign.testingservice.entity.test;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class QuestionBlock {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
}
