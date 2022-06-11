package com.requestdesign.testingservice.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class TestResult {
    private Long id;
    private Long testId;
    private Long respondentId;
    private Date datetime;
    private Integer respondentType;
}
