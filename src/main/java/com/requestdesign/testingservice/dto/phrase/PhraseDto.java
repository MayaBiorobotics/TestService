package com.requestdesign.testingservice.dto.phrase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhraseDto {
    @JsonProperty("group_id")
    private Long groupId;
    private String phrase;
}
