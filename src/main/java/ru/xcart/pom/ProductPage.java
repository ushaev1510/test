package ru.xcart.pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    private SelenideElement nameProduct = $("h1.title");
    private SelenideElement rootPage = $("#main");

    public ProductPage() {
        rootPage.shouldBe(Condition.visible);
    }

    @Step("Проверить что имя продукта совпадает с '{name}'")
    public ProductPage checkProductName(String name) {
        nameProduct.shouldBe(Condition.text(name));
        return this;
    }
}
