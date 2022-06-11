package com.requestdesign.testingservice.entity.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
}
