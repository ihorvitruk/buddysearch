package com.buddysearch.android.domain.dto;

import lombok.Data;

@Data
public class User {

    private String id;

    private String firstName;

    private String lastName;

    private int age;

    private Gender gender;

    private float latitude;

    private float longitude;
}
