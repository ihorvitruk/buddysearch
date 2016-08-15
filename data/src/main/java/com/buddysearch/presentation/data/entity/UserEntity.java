package com.buddysearch.presentation.data.entity;

import lombok.Data;

@Data
public class UserEntity {

    public static final String GENDER_MALE = "male";
    public static final String GENDER_FEMALE = "female";

    private String id;

    private String firstName;

    private String lastName;

    private int age;

    private String gender;

    private float latitude;

    private float longitude;
}
