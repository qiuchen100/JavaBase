package com.base.mongodb;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Entity("user")
public class User {

    @Id
    ObjectId id;

    String name;

    String age;

    int sex;

    @Embedded
    List<String> hobby = new ArrayList<>();

    Date regeditTime;

    HashMap bizData;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex=" + sex +
                ", hobby=" + hobby +
                ", regeditTime=" + regeditTime +
                ", bizData=" + bizData +
                '}';
    }
}
