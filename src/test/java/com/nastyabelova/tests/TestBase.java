package com.nastyabelova.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.zoom;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy="eager";
    }
}
