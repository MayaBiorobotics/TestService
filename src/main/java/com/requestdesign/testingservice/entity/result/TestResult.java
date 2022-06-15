package com.requestdesign.testingservice.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TestResult {
    private Long id;
    private Long testId;
    private Long respondentId;
    private Date datetime;
    private Integer respondentType;
    List<QuestionAnswer> questionAnswers;
    List<TaskAnswer> taskAnswers;
}
