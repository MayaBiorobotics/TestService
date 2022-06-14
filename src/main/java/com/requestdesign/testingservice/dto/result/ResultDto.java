package com.requestdesign.testingservice.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultDto {
    @JsonProperty("test_id")
    private Long testId;
    @JsonProperty("respondent_id")
    private Long respondentId;
    @JsonProperty("creation_time")
    private String creationTime;
    @JsonProperty("respondent_type")
    private String respondentType;
}
