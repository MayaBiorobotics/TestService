package com.requestdesign.testingservice.entity.phrase;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DictionaryPhrase {
    private Long id;
    private Long directionId;
    private String phrase;
}
