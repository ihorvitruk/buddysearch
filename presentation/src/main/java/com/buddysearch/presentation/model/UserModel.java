package com.buddysearch.presentation.model;

import com.buddysearch.presentation.domain.dto.Gender;

import lombok.Data;

@Data
public class UserModel {

    private String id;

    private String firstName;

    private String lastName;

    private int age;

    private Gender gender;

    private float latitude;

    private float longitude;
}
