package com.buddysearch.android.data.entity.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RealmUserEntity extends RealmObject {

    @PrimaryKey
    private String id;

    private String firstName;

    private String lastName;

    private int age;

    private String gender;

    private float latitude;

    private float longitude;
}
