package ru.xcart.pom;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.xcart.pom.elements.FastSearchElement;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    private SelenideElement blockProgressSearch = $(".wait-block-overlay");
    private SelenideElement blockFindProducts = $(".products");
    private SelenideElement buttonSearch = $(".search-form-submit");
    private SelenideElement buttonOpenAdvancedSearch = $(By.linkText("More search options"));
    private SelenideElement checkboxByDescription = $("#by-descr");
    private SelenideElement inputSearch = $(".search-form input[type='text']");
    private SelenideElement rootElementSearch = $("#content .content .products-search-result");
    private SelenideElement selectCategotyAdvancedSearch = $(".FixedSelect ");

    private ElementsCollection listFindProducts = $$(".products .product-cell");

    public SearchPage() {
        rootElementSearch.shouldBe(Condition.visible);
    }

    @Step("Ввести в поле поиска текст: '{text}'")
    public SearchPage fillSeatchField(String text) {
        inputSearch.setValue(text);
        return this;
    }

    @Step("Открыть форму расширенного поиска")
    public SearchPage openAdvancedSearch() {
        buttonOpenAdvancedSearch.click();
        return this;
    }

    @Step("Выбрать опцию по Описанию в расширенном поиске")
    public SearchPage selectByDescriptionInOptionsSearch() {
        checkboxByDescription.click();
        return this;
    }

    @Step("Выбрать категорию {categoty} в расширенном поиске")
    public SearchPage selectCategoryInOptionsSearch(String category) {
        selectCategotyAdvancedSearch.selectOptionContainingText(category);
        return this;
    }

    @Step("Клик по кнопке поиска")
    public SearchPage clickSearchButton() {
        buttonSearch.click();
        //честно пытался прикрутить проверку на ajax и angular, но время только потратил
        //плюс пытался отловить перерисовку блока, но тоже не всегда помогало
        blockProgressSearch.shouldBe(Condition.exist);
        blockProgressSearch.shouldNotBe(Condition.exist);
        blockFindProducts.shouldBe(Condition.enabled);
        sleep(2000);
        return new SearchPage();
    }

    @Step("Получить количество найденных продуктов")
    public int getCountFindProducts() {
        return listFindProducts.size();
    }

    public FastSearchElement getFastSearchElement() {
        return new FastSearchElement();
    }
}
