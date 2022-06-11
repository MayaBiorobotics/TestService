package com.requestdesign.testingservice.entity.phrase;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Code {
    private Long id;
    private String code;
    private Long resultId;
    private Long respondentId;
    private Date given;
    private String status;
}
