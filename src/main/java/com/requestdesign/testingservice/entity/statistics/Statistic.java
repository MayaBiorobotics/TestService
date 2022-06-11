package com.requestdesign.testingservice.entity.statistics;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Statistic {
    private Long id;
    private Long testId;
    private Long phraseId;
    private Date date;
    private Integer clickAmount;
}
