package com.buddysearch.android.presentation.mvp.model;

import com.buddysearch.android.domain.dto.Gender;

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
