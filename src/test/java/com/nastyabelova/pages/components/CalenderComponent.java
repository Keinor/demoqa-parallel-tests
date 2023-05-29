package com.nastyabelova.pages.components;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CalenderComponent {

    public void setDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        $x("//div[contains(@aria-label, \""+month+" "+day+"th, "+year+"\")]").click();
    }
}
