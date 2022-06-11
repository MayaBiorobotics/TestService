package com.requestdesign.testingservice.entity.test;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionVariant {
    private Long id;
    private Long questionId;
    private String text;
    private String picture;
}
