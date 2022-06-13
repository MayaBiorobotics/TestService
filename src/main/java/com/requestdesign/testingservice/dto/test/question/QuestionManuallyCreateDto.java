package com.requestdesign.testingservice.dto.test.question;

import com.requestdesign.testingservice.entity.test.Question;

import java.util.ArrayList;

public class QuestionManuallyCreateDto {
    private String text;
    private String picture;
    private ArrayList<QuestionVariantAddDto> questionVariants;
}
