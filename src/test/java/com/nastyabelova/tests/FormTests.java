package com.nastyabelova.tests;

import com.nastyabelova.helpers.TestDataHelper;
import com.nastyabelova.pages.RegistrationPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

public class FormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Execution(value = ExecutionMode.SAME_THREAD)
    @Test()
    public void fillRegistrationFormTests() {
        registrationPage.openPage();
        registrationPage.typeFirstName(TestDataHelper.firstName).typeLastName(TestDataHelper.lastName).typeEmail(TestDataHelper.email).typeGender("2").typePhoneNumber(TestDataHelper.phone).typeSubjects().typeHobbies("2").typeHobbies("3").typeUploadPicture(TestDataHelper.PICTURE).typeAddress(TestDataHelper.address).calender.setDate(TestDataHelper.DAY, TestDataHelper.MONTH, TestDataHelper.YEAR);
        registrationPage.submitFormRegistration();

        registrationPage.checkResultsData(TestDataHelper.expectedData);
        System.out.println(Thread.currentThread().getName() + " => formTests в одном потоке");
    }
}
