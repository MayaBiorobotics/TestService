package com.requestdesign.testingservice.entity.phrase;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestPhrase {
    private Long phraseId;
    private Integer intensivity;
    private Long testId;
}
