package com.requestdesign.testingservice.entity.test;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class QuestionVariant {
    private Long id;
    private Long questionId;
    private String text;
    private String picture;
}
