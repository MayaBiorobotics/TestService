package com.requestdesign.testingservice.entity.phrase;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class DictionaryPhrase {
    private Long id;
    private Long directionId;
    private String phrase;
}
