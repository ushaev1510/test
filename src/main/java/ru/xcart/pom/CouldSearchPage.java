package ru.xcart.pom;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CouldSearchPage {

    private String pageUrl = "/ecommerce-search-engine.html";

    @Step("Проверка текущего урла страницы")
    public void checkPageUrl() {
        switchTo().window(1);
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains(pageUrl), "Неверный урл");
    }
}
