package ru.xcart;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    @Step("Необходимые предустановки")
    protected void createWebDriver() {
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        DesiredCapabilities dCapabilities = new DesiredCapabilities();
        dCapabilities.setBrowserName("chrome");

        Configuration.browserCapabilities = dCapabilities;
    }
}
