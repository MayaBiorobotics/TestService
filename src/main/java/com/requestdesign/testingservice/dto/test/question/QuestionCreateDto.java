package com.requestdesign.testingservice.dto.test.question;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionCreateDto {
    private Long id;
    private String text;
    private String picture;
}
