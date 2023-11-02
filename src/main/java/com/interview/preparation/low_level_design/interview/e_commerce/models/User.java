package com.interview.preparation.low_level_design.interview.e_commerce.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User {
    private String id;
    private String name;
    private String emailId;
}
