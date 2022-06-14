package com.requestdesign.testingservice.entity.phrase;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Access;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TestPhrase {
    private Long id;
    private Long phraseId;
    private Integer intensivity;
    private Long testId;
}
