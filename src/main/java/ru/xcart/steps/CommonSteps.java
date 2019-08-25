package ru.xcart.steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import ru.xcart.pom.SearchPage;

public class CommonSteps {

    private static String SEARCH_PAGE_URL = "/?target=search";

    @Step("Открытие страницы поиска")
    public static SearchPage openSearchPage(String url) {
        Selenide.open(url + SEARCH_PAGE_URL);
        return new SearchPage();
    }
}
