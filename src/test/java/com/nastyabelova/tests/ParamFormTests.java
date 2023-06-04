package com.nastyabelova.tests;

import com.nastyabelova.helpers.StateNames;
import com.nastyabelova.helpers.TestDataHelper;
import com.nastyabelova.pages.RegistrationPage;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;


public class ParamFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    static Stream<Arguments> checkFields() {
        return Stream.of(Arguments.of(TestDataHelper.firstName, TestDataHelper.lastName, TestDataHelper.phone, "3", "Other", "simple.jpg"), Arguments.of(TestDataHelper.firstName, TestDataHelper.lastName, TestDataHelper.phone, "2", "Female", "gory.jpg"));
    }

    @MethodSource("checkFields")
    @ParameterizedTest(name = "Проверка отправки необходимых полей с картинкой")
    void checkFields(String firstname, String lastname, String phone, String gender, String expectedGender, String picture) {
        registrationPage.openPage();
        registrationPage.typeFirstName(firstname).typeLastName(lastname).typePhoneNumber(phone).typeGender(gender).typeUploadPicture(picture);
        registrationPage.submitFormRegistration();

        registrationPage.checkResultsMS(firstname, lastname, phone, gender, expectedGender, picture);
        System.out.println(Thread.currentThread().getName() + " => Param checkFields() в другом потоке");
    }

    @EnumSource(value = StateNames.class, names = {"HARYANA", "RAJASTHAN"})
    @ParameterizedTest(name = "Проверка выбора штата и города")
    void checkStateCities(StateNames stateNames) {
        registrationPage.openPage();
        registrationPage.typeFirstName(TestDataHelper.firstName).typeLastName(TestDataHelper.lastName).typeGender("2").typePhoneNumber(TestDataHelper.phone).typeStateCity(stateNames);
        registrationPage.submitFormRegistration();

        registrationPage.checkStateCity(stateNames.getDesc());
        System.out.println(Thread.currentThread().getName() + " => Param checkStateCities() в другом потоке");
    }


    @ValueSource(strings = {"1", "2", "3"})
    @ParameterizedTest(name = "Проверка отправки разных ключей для гендера: {0}")
    void checkGender(String keyGender) {
        registrationPage.openPage();
        registrationPage.typeFirstName(TestDataHelper.firstName).typeLastName(TestDataHelper.lastName).typeGender(keyGender).typePhoneNumber(TestDataHelper.phone);
        registrationPage.submitFormRegistration();

        registrationPage.checkGenderFields(keyGender);
        System.out.println(Thread.currentThread().getName() + " => Param checkGender() в другом потоке");
    }

    @Execution(value = ExecutionMode.SAME_THREAD)
    @CsvSource({"19,September,1998"})
    @ParameterizedTest(name = "Проверка отправки даты рождения: {0} {1} {2}")
    void checkDateBirth(String day, String month, String year) {
        registrationPage.openPage();
        registrationPage.typeFirstName(TestDataHelper.firstName).typeLastName(TestDataHelper.lastName).typePhoneNumber(TestDataHelper.phone).typeGender("2").calender.setDate(day, month, year);
        registrationPage.submitFormRegistration();

        registrationPage.checkDateBirth(day, month, year);
        System.out.println(Thread.currentThread().getName() + " => Param checkDateBirth в одном потоке");
    }
}
