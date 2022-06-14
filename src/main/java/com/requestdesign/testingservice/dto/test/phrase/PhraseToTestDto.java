package com.requestdesign.testingservice.dto.test.phrase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhraseToTestDto {
    @JsonProperty("phrase_id")
    private String phraseId;
    private Integer intensivity;
}
