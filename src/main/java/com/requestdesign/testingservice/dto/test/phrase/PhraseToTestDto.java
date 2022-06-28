package com.requestdesign.testingservice.dto.test.phrase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhraseToTestDto {
    @JsonProperty("phrase_id")
    private Long phraseId;
    private Integer intensivity;
}
