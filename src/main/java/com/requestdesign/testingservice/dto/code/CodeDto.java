package com.requestdesign.testingservice.dto.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class CodeDto {
    @JsonProperty("test_id")
    private Long testId;
    @JsonProperty("respondent_id")
    private Long respondentId;
}
