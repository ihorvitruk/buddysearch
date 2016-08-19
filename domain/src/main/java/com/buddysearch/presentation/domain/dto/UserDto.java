package com.buddysearch.presentation.domain.dto;

import lombok.Data;

@Data
public class UserDto {

    private String id;

    private String firstName;

    private String lastName;

    private int age;

    private Gender gender;

    private float latitude;

    private float longitude;
}
