package com.nastyabelova.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.nastyabelova.helpers.StateNames;
import com.nastyabelova.helpers.TestDataHelper;
import com.nastyabelova.pages.components.CalenderComponent;
import org.assertj.core.api.SoftAssertions;

import java.io.File;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;


public class RegistrationPage {

    public static String typeCity = "";
    private final SelenideElement formTitle = $(".practice-form-wrapper"), firstNameInput = $("#firstName"), lastNameInput = $("#lastName"), emailInput = $("#userEmail"), userNumberInput = $("#userNumber"), subjectsInput = $("#subjectsInput"), uploadPicture = $("#uploadPicture"), addressArea = $("#currentAddress"), stateOption = $("#react-select-3-input"), cityOption = $("#react-select-4-input"), submitForm = $("#submit");
    public CalenderComponent calender = new CalenderComponent();

    public static int random(int max) {
        return (int) (Math.random() * max);
    }

    public void openPage() {
        open("/automation-practice-form");
        zoom(0.8);
        formTitle.shouldHave(text(TestDataHelper.FORM_TITLE));
    }

    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeGender(String key) {
        $x("//label[@for='gender-radio-" + key + "']").click();
        return this;
    }

    public RegistrationPage typePhoneNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage typeUploadPicture(String value) {
        uploadPicture.uploadFile(new File("src/test/resources/img/" + value));
        return this;
    }

    public RegistrationPage typeStateCity(StateNames stateNames) {
        stateOption.setValue(stateNames.getDesc()).pressEnter();

        final int max = stateNames.namesCities().size();
        final int rnd = random(max);

        //Для переданного штата получаем рандомный город из enum
        typeCity = (String) stateNames.namesCities().get(rnd);
        cityOption.setValue(typeCity).pressEnter();
        return this;
    }

    public void checkStateCity(String state) {
        $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + typeCity));
    }

    public RegistrationPage submitFormRegistration() {
        submitForm.click();
        return this;
    }

    public void checkGenderFields(String key) {
        switch (key) {
            case "1" -> $x("//td[text()='Gender']").parent().shouldHave(text("Male"));
            case "2" -> $x("//td[text()='Gender']").parent().shouldHave(text("Female"));
            case "3" -> $x("//td[text()='Gender']").parent().shouldHave(text("Other"));
        }
    }

    public void checkDateBirth(String day, String month, String year) {
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(day + " " + month + "," + year));
    }

    public void checkResultsData(Map<String, String> expectedData) {
        ElementsCollection lines = $$(".table-responsive tbody tr").snapshot();

        SoftAssertions softly = new SoftAssertions();

        for (SelenideElement line : lines) {
            String keyTd = line.$("td").text();
            if (keyTd.equals("Student Name") || keyTd.equals("Mobile")) {
                String expectedValue = expectedData.get(keyTd);
                String actualValueTd = line.$("td", 1).text();

                softly.assertThat(actualValueTd).as(format("\nTable: %s contains %s, but expected %s", keyTd, expectedValue, actualValueTd)).isEqualTo(expectedValue);
            }
            softly.assertAll();
        }
    }
}
