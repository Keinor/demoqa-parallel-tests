package com.nastyabelova.helpers;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class TestDataHelper {

    public static final String FORM_TITLE = "Student Registration Form";
    private static final Faker faker = new Faker();
    public static String firstName = faker.name().firstName();
    public static String lastName = faker.name().lastName();
    public static String phone = faker.phoneNumber().subscriberNumber(10);
    public static Map<String, String> expectedDataMS1 = new HashMap<>() {
        {
            put("Student Name", TestDataHelper.firstName + " " + TestDataHelper.lastName);
            put("Mobile", TestDataHelper.phone);
        }
    };

}
