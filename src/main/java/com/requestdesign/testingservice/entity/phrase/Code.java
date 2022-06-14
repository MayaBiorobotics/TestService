package com.requestdesign.testingservice.entity.phrase;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Code {
    private Long id;
    private String code;
    private Long resultId;
    private Long respondentId;
    private Date given;
    private String status;
}
