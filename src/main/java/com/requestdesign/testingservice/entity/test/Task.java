package com.requestdesign.testingservice.entity.test;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Task {
    private Long id;
    private String text;
}
