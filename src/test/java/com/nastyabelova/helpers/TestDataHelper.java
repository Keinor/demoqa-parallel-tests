package com.nastyabelova.helpers;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class TestDataHelper {

    public static final String FORM_TITLE = "Student Registration Form",
            SUBJECTS_INPUT = "Computer Science",
            DAY = "19",
            YEAR = "1998",
            MONTH = "September",
            PICTURE = "gory.jpg",
            FEMALE_GENDER = "Female",
            HOBBIE_READING = "Reading",
            HOBBIE_MUSIC = "Music";
    private static final Faker faker = new Faker();
    public static String firstName = faker.name().firstName();
    public static String lastName = faker.name().lastName();
    public static String email = faker.internet().emailAddress();
    public static String address = faker.address().city();
    public static String phone = faker.phoneNumber().subscriberNumber(10);
    public static Map<String, String> expectedData = new HashMap<>() {
        {
            put("Student Name", TestDataHelper.firstName + " " + TestDataHelper.lastName);
            put("Student Email", TestDataHelper.email);
            put("Gender", TestDataHelper.FEMALE_GENDER);
            put("Mobile", TestDataHelper.phone);
            put("Date of Birth", TestDataHelper.DAY + " " + TestDataHelper.MONTH + "," + TestDataHelper.YEAR);
            put("Subjects", TestDataHelper.SUBJECTS_INPUT);
            put("Hobbies", TestDataHelper.HOBBIE_READING + ", " + TestDataHelper.HOBBIE_MUSIC);
            put("Picture", TestDataHelper.PICTURE);
            put("Address", TestDataHelper.address);
        }
    };
}
